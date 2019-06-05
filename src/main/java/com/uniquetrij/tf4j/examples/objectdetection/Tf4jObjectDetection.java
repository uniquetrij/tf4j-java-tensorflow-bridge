/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniquetrij.tf4j.examples.objectdetection;

import com.uniquetrij.tf4j.objectdetection.VisualizationUtils;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.stream.IntStream;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.types.UInt8;

/**
 *
 * @author uniquetrij
 */
public class Tf4jObjectDetection {

    public static void main(String[] args) {
        nu.pattern.OpenCV.loadShared();

        SavedModelBundle model = SavedModelBundle
                .loader(Tf4jObjectDetection.class.getClassLoader()
                        .getResource("tf-graphs/" + "faster_rcnn_inception_v2_coco_2018_01_28")
                        .getPath())
                .withTags("serve")
                .load();

//        VideoCapture capture = new VideoCapture(0);
//        while (true) {
//            Mat mat = new Mat();
          Mat mat = Imgcodecs.imread(Tf4jObjectDetection.class.getClassLoader().getResource("images/image2.jpg").getPath());

//            if (capture.read(mat)) {
                if (!mat.empty()) {
                    byte[] bytes = new byte[mat.height() * mat.width() * mat.channels()];
                    mat.get(0, 0, bytes);
                    ByteBuffer data = ByteBuffer.wrap(bytes);
                    Tensor<UInt8> tensor = Tensor.create(UInt8.class, new long[]{1, mat.height(), mat.width(), mat.channels()}, data);
                    Session.Runner runner = model.session().runner();
                    runner.feed("tf4j/" + "image_tensor" + ":0", tensor);
                    runner.fetch("tf4j/" + "num_detections" + ":0");
                    runner.fetch("tf4j/" + "detection_boxes" + ":0");
                    runner.fetch("tf4j/" + "detection_classes" + ":0");
                    runner.fetch("tf4j/" + "detection_scores" + ":0");

                    List<Tensor<?>> infer = runner.run();
                    float[][][] boxes = new float[1][100][4];
                    infer.get(1).expect(Float.class).copyTo(boxes);
                    float[][] classes = new float[1][100];
                    infer.get(2).expect(Float.class).copyTo(classes);
                    float[][] scores = new float[1][100];
                    infer.get(3).expect(Float.class).copyTo(scores);

                    IntStream.range(0, scores[0].length).parallel().forEach(i -> {
                        if (scores[0][i] > 0.40) {
                            VisualizationUtils.annotate(mat, boxes[0][i], classes[0][i], scores[0][i]);
                        }
                    });

                    HighGui.imshow("Object Detection", mat);
                    HighGui.waitKey(1);
                }
//            }
//        }

    }

}
