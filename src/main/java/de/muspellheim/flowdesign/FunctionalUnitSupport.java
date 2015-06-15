/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */
package de.muspellheim.flowdesign;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class FunctionalUnitSupport<IN, OUT> {

    private static final Consumer NOP = in -> {};

    private final List<Consumer<OUT>> consumers = new CopyOnWriteArrayList<>();
    private final Consumer<IN> process;

    public FunctionalUnitSupport() {
        this(NOP);
    }

    public FunctionalUnitSupport(Consumer<IN> process) {
        Objects.requireNonNull(process, "process");
        this.process = process;
    }

    public void process(IN input) {
        process.accept(input);
    }

    public void connectOutput(Consumer<OUT> consumer) {
        Objects.requireNonNull(consumer);
        consumers.add(consumer);
    }

    public void disconnectOutput(Consumer<OUT> consumer) {
        Objects.requireNonNull(consumer);
        consumers.remove(consumer);
    }

    public void publishResult(OUT result) {
        consumers.forEach(c -> c.accept(result));
    }

}
