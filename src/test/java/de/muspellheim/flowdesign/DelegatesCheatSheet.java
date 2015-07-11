/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

import java.util.function.Consumer;

/**
 * Zeigt wie Functional-Units in Methoden mit Delegate-Parameter anstelle Rückgabewerten überführt werden können.
 *
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public class DelegatesCheatSheet {

    /**
     * Ein einfaches Beispiel einer Functional-Unit mit einem Input T und einem Output U.
     */
    public <U, T> void a(T input, Consumer<U> output) {
        U u = null;
        // ...
        output.accept(u);
    }

    /**
     * Ein zusammengesetzten Beispiel. Die beiden Parts A und B werden zum Board X zusammengesteckt.
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
     * Eine Functional-Unit mit zwei Input-Pins zu einem Tuple vereint.
     */
    public <T, U> void a(Tuple<T, U> input) {
        // ...
    }

    /**
     * Eine Functional-Unit mit zwei Output-Pins zu einem Tuple vereint.
     */
    public <T, U> void a(Consumer<Tuple<T, U>> output) {
        Tuple<T, U> t = null;
        // ...
        output.accept(t);
    }

    /**
     * Eine Functional-Unit mit zwei Output-Pins als Output-Parameter.
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
