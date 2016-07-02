/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

import java.util.function.Consumer;

/**
 * Shows how to transfer a functional unit into methods with delegate parameter
 * instead of using return values.
 *
 * @author Falko Schumann
 */
public class DelegatesCheatSheet {

    /**
     * A simple example of an functional unit with one input and one output.
     */
    public <U, T> void a(T input, Consumer<U> output) {
        U u = null;
        // ...
        output.accept(u);
    }

    /**
     * A composite example. The parts A and B are put together to board X.
     */
    public <U, S, T> void x(T input, Consumer<U> output) {
        a(input, s -> b(s, output::accept));
    }

    private <U, T> void b(T input, Consumer<U> output) {
        U u = null;
        // ...
        output.accept(u);
    }

    /**
     * A functional unit with two input pins joined to a tuple.
     */
    public <T, U> void a(Tuple<T, U> input) {
        // ...
    }

    /**
     * A functional unit with two output pins joined to a tuple.
     */
    public <T, U> void a(Consumer<Tuple<T, U>> output) {
        Tuple<T, U> t = null;
        // ...
        output.accept(t);
    }

    /**
     * A functional unit with two separate output pins.
     */
    public <T, U> void a(Consumer<T> outputT, Consumer<U> outputU) {
        // ...
    }

    /**
     * Split von A auf B und C sowie Join von B und C auf D.
     */
    public <T, U, S, V> void foo() {
        T t = null;
        U u = null;

        // FIXME Functional-Unit mit zwei Input-Pins scheint schwieriger realisierbar
        //a(t -> b(t, s), u -> c(u, v));
        //d(Tuple.of(s, v));
    }

    private <U, T> void c(T input, Consumer<U> output) {
        U u = null;
        // ...
        output.accept(u);
    }

    private <U, T> void d(Tuple<T, U> input) {
        // ...
    }

}
