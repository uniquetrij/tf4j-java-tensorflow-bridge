/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniquetrij.tf4j.objectdetection;

import com.uniquetrij.tf4j.TensorflowModel;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;
import org.tensorflow.Tensor;
import org.tensorflow.types.UInt8;
import com.uniquetrij.tf4j.TensorflowClient;

/**
 *
 * @author uniquetrij
 */
public class SimpleObjectDetector extends Thread implements TensorflowClient {

    private class QItem {

        public QItem(Mat mat, Map<String, Tensor<?>> map) {
            this.mat = mat;
            this.map = map;
        }

        public Mat mat;
        public Map<String, Tensor<?>> map;
    }

    private final TensorflowModel model;

    private final List<QItem> inputQ;
    private final List<QItem> resultQ;
    private final List<QItem> visualizeQ;

    private boolean isTerminated;

    public SimpleObjectDetector() {
        this(Models.MODEL_FASTER_RCNN_INCEPTION_V2_COCO_2018_01_28);
    }

    public SimpleObjectDetector(TensorflowModel model) {

        this.inputQ = Collections.synchronizedList(new ArrayList<>());
        this.resultQ = Collections.synchronizedList(new ArrayList<>());
        this.visualizeQ = Collections.synchronizedList(new ArrayList<>());
        this.model = model;
    }

    @Override
    public TensorflowModel getModel() {
        return model;
    }

    @Override
    public void run() {
        new Thread(() -> {
            final VideoCapture videoCapture = new VideoCapture(0);
            while (!isTerminated) {
                read(videoCapture);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SimpleObjectDetector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();

        new Thread(() -> {
            while (!isTerminated) {
                if (!visualizeQ.isEmpty()) {
                    visualize();
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SimpleObjectDetector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    private void read(VideoCapture capture) {
        Mat mat = new Mat();
        if (resultQ.isEmpty() && capture.read(mat)) {
            byte[] bytes = new byte[mat.height() * mat.width() * mat.channels()];
            mat.get(0, 0, bytes);
            ByteBuffer data = ByteBuffer.wrap(bytes);
            Map<String, Tensor<?>> map = new HashMap<>();
            map.put("image_tensor", Tensor.create(UInt8.class, new long[]{1, mat.height(), mat.width(), mat.channels()}, data));
            inputQ.add(new QItem(mat, map));
            notifyOnInputAvailable();
        }
    }

    @Override
    public Map<String, Tensor<?>> getInput() {
        if (inputQ.isEmpty()) {
            return null;
        }
        QItem remove = inputQ.remove(0);
        resultQ.add(remove);
        inputQ.clear();
        return remove.map;
    }

    @Override
    public void onModelResult(Map<String, Tensor<?>> infer) {
        QItem remove = resultQ.remove(0);
        remove.map = infer;
        visualizeQ.add(remove);
    }

    public void visualize() {
        QItem inf = visualizeQ.remove(0);
        Mat image = inf.mat;
        Map<String, Tensor<?>> infer = inf.map;
        float[][][] boxes = new float[1][100][4];
        infer.get("detection_boxes").expect(Float.class
        ).copyTo(boxes);
        float[][] classes = new float[1][100];
        infer.get("detection_classes").expect(Float.class
        ).copyTo(classes);
        float[][] scores = new float[1][100];
        infer.get("detection_scores").expect(Float.class
        ).copyTo(scores);

        IntStream.range(0, scores[0].length).parallel().forEach(i -> {
            if (scores[0][i] > 0.5) {
                VisualizationUtils.annotate(image, boxes[0][i], classes[0][i], scores[0][i]);
            }
        });
        HighGui.imshow("Simple Object Detection", image);
        HighGui.waitKey(1);
    }

}
