/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniquetrij.tf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tensorflow.Tensor;

/**
 *
 * @author uniquetrij
 */
public class TensorflowService extends Thread {

    static {
        nu.pattern.OpenCV.loadShared();
    }

    private final TensorflowModel model;
    private boolean isTerminated;
    private final List<TensorflowClient> subscribers;
    private final List<TensorflowClient> active;

    public TensorflowService(TensorflowModel model) {
        this.model = model;
        this.subscribers = Collections.synchronizedList(new ArrayList<>());
        this.active = Collections.synchronizedList(new ArrayList<>());
    }

    public void subscribe(TensorflowClient subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
        synchronized (active) {
            active.add(subscriber);
            active.notify();
        }
    }

    public void unsubscribe(TensorflowClient subscriber) {
        if (subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
        }
    }

    @Override
    public void run() {
        while (!isTerminated) {
            if (!active.isEmpty()) {
                active.forEach((TensorflowClient subscriber) -> {
                    Map<String, Tensor<?>> inputForModelFeed;
                    if ((inputForModelFeed = subscriber.getInput()) != null) {
                        subscriber.onModelResult(model.infer(inputForModelFeed));
                    } else {
                        new Thread(() -> {
                            active.remove(subscriber);
                            subscriber.waitUntilInputAvailable();
                            if (subscribers.contains(subscriber)) {
                                synchronized (active) {
                                    active.add(subscriber);
                                    active.notify();
                                }
                            }
                        }).start();
                    }
//                    try {
//                        Thread.sleep(0, 1);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(ModelRunner.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                });
            } else {
                try {
                    synchronized (active) {
                        active.wait();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(TensorflowService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void terminate() {
        this.isTerminated = true;
    }

}
