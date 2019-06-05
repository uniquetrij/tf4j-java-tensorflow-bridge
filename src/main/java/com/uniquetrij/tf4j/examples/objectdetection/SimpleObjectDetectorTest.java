/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniquetrij.tf4j.examples.objectdetection;

import com.uniquetrij.tf4j.TensorflowService;
import com.uniquetrij.tf4j.objectdetection.SimpleObjectDetector;

/**
 *
 * @author uniquetrij
 */
public class SimpleObjectDetectorTest {

    public static void main(String[] args) {

        SimpleObjectDetector simpleObjectDetector = new SimpleObjectDetector();
        TensorflowService tensorflowRunner = new TensorflowService(simpleObjectDetector.getModel());
        tensorflowRunner.subscribe(simpleObjectDetector);
        tensorflowRunner.start();
        simpleObjectDetector.start();
    }

}
