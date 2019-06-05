/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniquetrij.tf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

/**
 *
 * @author uniquetrij
 */
public class TensorflowModel {

    private final SavedModelBundle bundle;
    private final String prefix;
    private final String suffix;
    private final List<String> inputs;
    private final List<String> outputs;

    public static class Loader {

        private String path;
        private String[] tags;
        private String[] inputOperations;
        private String[] outputOperations;
        private String prefix;
        private String suffix;
        private SavedModelBundle bundle;

        public static Loader getInstance() {
            return new Loader();
        }

        private Loader() {
        }

        public Loader loadFromPath(String path, String... tags) {
            assert path != null : "call to this method should not be made more than once";
            this.path = path;
            this.tags = tags;
            return this;
        }

        public Loader havingInputOperations(String... names) {
            assert path == null : "call to loadFromPath() must be made before this method invocation";
            assert inputOperations != null : "call to this method should not be made more than once";
            assert names.length > 0 : "at least one name must be supplied";
            inputOperations = names;
            return this;
        }

        public Loader havingOutputOperations(String... names) {
            assert inputOperations == null : "call to havingInputOperations() must be made before this method invocation";
            assert outputOperations != null : "call to this method should not be made more than once";
            assert names.length > 0 : "at least one name must be supplied";
            outputOperations = names;
            return this;
        }

        public Loader withPrefix(String prefix) {
            assert inputOperations == null : "call to havingOutputOperations() must be made before this method invocation";
            assert prefix != null : "call to this method should not be made more than once";
            this.prefix = prefix;
            return this;
        }

        public Loader withSuffix(String suffix) {
            assert inputOperations == null : "call to withPrefix() must be made before this method invocation";
            assert suffix != null : "call to this method should not be made more than once";
            this.suffix = suffix;
            return this;
        }

        public TensorflowModel load() {
            assert suffix == null : "call to withSuffix() must be made before this method invocation";
            bundle = SavedModelBundle.loader(path)
                    .withTags(tags)
                    .load();
            validate();
            return new TensorflowModel(bundle, prefix, suffix, inputOperations, outputOperations);
        }

        private void validate() throws IllegalArgumentException {
            StringBuilder report = new StringBuilder();
            List<String> collect = Stream.concat(Stream.of(inputOperations), Stream.of(outputOperations)).parallel().collect(Collectors.toList());
            assert collect.stream().allMatch(operation -> {
                String op = prefix + operation;
                if (bundle.graph().operation(op) == null) {
                    report.append(op).append(" ");
                    return false;
                }
                return true;
            }) : "graph doesn't contain required operations: " + Arrays.toString(report.toString().split(" "));
        }
    }

    protected TensorflowModel(SavedModelBundle bundle, String prefix, String suffix, String[] inputNames, String[] outputNames) {
        this.bundle = bundle;
        this.prefix = prefix;
        this.suffix = suffix;
        this.inputs = Collections.unmodifiableList(Arrays.asList(inputNames));
        this.outputs = Collections.unmodifiableList(Arrays.asList(outputNames));
    }

    public List<Tensor<?>> run(Tensor... tensors) {
        Session.Runner runner = bundle.session().runner();
        IntStream.range(0, inputs.size()).forEach(i -> runner.feed(prefix + inputs.get(i) + suffix, tensors[i]));
        IntStream.range(0, outputs.size()).forEach(i -> runner.fetch(prefix + outputs.get(i) + suffix));
        return runner.run();
    }

    public List<Tensor<?>> run(Map<String, Tensor<?>> tensors) {
        Session.Runner runner = bundle.session().runner();
        tensors.entrySet().forEach(e -> runner.feed(prefix + e.getKey() + suffix, e.getValue()));
        IntStream.range(0, outputs.size()).forEach(i -> runner.fetch(prefix + outputs.get(i) + suffix));
        return runner.run();        
    }

    public Map<String, Tensor<?>> infer(Map<String, Tensor<?>> tensors) {
        Session.Runner runner = bundle.session().runner();
        tensors.entrySet().forEach(e -> runner.feed(prefix + e.getKey() + suffix, e.getValue()));
        IntStream.range(0, outputs.size()).forEach(i -> runner.fetch(prefix + outputs.get(i) + suffix));
        List<Tensor<?>> run = runner.run();
        Map<String, Tensor<?>> map = new HashMap<>();
        IntStream.range(0, run.size()).forEach(i -> map.put(outputs.get(i), run.get(i)));
        return map;
    }

    public Map<String, Tensor<?>> infer(Tensor... tensors) {
        List<Tensor<?>> run = run(tensors);
        Map<String, Tensor<?>> map = new HashMap<>();
        IntStream.range(0, run.size()).forEach(i -> map.put(outputs.get(i), run.get(i)));
        return map;
    }

    public Boolean hasOperations(String... operationNames) {
        StringBuilder report = new StringBuilder();
        List<String> collect = Stream.of(operationNames).parallel().collect(Collectors.toList());
        return collect.stream().allMatch(operation -> {
            String op = prefix + operation;
            if (bundle.graph().operation(op) == null) {
                report.append(op).append(" ");
                return false;
            }
            return true;
        });
    }
}
