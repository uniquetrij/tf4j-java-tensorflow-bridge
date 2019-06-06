/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniquetrij.tf4j;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tensorflow.Tensor;

/**
 *
 * @author uniquetrij
 */
public interface TensorflowClient {

    public Map<String, Tensor<?>> getInput();

    public void onModelResult(Map<String, Tensor<?>> tensors);

    public TensorflowModel getModel();

    public default void notifyOnInputAvailable() {
        synchronized (this) {
            this.notify();
        }
    }

    public default void waitUntilInputAvailable() {
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(TensorflowService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
