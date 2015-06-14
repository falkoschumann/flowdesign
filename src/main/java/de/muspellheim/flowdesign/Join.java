/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */
package de.muspellheim.flowdesign;

import java.util.function.Consumer;

public class Join<T, S> {

    private final FunctionalUnitSupport<?, Tuple<T, S>> consumers = new FunctionalUnitSupport<>();

    private T input1;
    private S input2;

    public void input1(T input) {
        input1 = input;
        tryJoin();
    }

    public void input2(S input) {
        input2 = input;
        tryJoin();
    }

    private synchronized void tryJoin() {
        if (input1 != null && input2 != null) {
            consumers.publishResult(new Tuple<>(input1, input2));
            input1 = null;
            input2 = null;
        }
    }

    public void connectOutput(Consumer<Tuple<T, S>> c) {
        consumers.connectResult(c);
    }

    public void disconnectOutput(Consumer<Tuple<T, S>> c) {
        consumers.disconnectResult(c);
    }

}
