/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniquetrij.tf4j.objectdetection;

import com.uniquetrij.tf4j.TensorflowModel;

/**
 *
 * @author uniquetrij
 */
public class Models {

    public static final TensorflowModel MODEL_FASTER_RCNN_INCEPTION_V2_COCO_2018_01_28 = TensorflowModel.Loader.getInstance()
            .loadFromPath(Models.class.getClassLoader().getResource("tf-graphs/" + "faster_rcnn_inception_v2_coco_2018_01_28").getPath(), "serve")
            .havingInputOperations("image_tensor")
            .havingOutputOperations("num_detections", "detection_boxes", "detection_classes", "detection_scores")
            .withPrefix("tf4j/")
            .withSuffix(":0")
            .load();

    public static final TensorflowModel MODEL_SSD_MOBILENET_V1_COCO_2017_11_17 = TensorflowModel.Loader.getInstance()
            .loadFromPath(Models.class.getClassLoader().getResource("tf-graphs/" + "ssd_mobilenet_v1_coco_2017_11_17").getPath(), "serve")
            .havingInputOperations("image_tensor")
            .havingOutputOperations("num_detections", "detection_boxes", "detection_classes", "detection_scores")
            .withPrefix("tf4j/")
            .withSuffix(":0")
            .load();
    
}
