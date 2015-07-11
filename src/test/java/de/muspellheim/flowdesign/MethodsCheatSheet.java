/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Zeigt wie Functional-Units in Methoden überführt werden können.
 *
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public class MethodsCheatSheet {

    /**
     * Ein einfaches Beispiel einer Functional-Unit mit einem Input T und einem Output U.
     */
    public <U, T> U a(T input) {
        U u = null;
        // ...
        return u;
    }

    /**
     * Ein zusammengesetzten Beispiel. Die beiden Parts A und B werden zum Board X zusammengesteckt.
     */
    public <U, S, T> U x(T input) {
        S s = a(input);
        U u = b(s);
        return u;
    }

    private <U, T> U b(T input) {
        U u = null;
        // ...
        return u;
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
    public <T, U> Tuple<T, U> a() {
        Tuple<T, U> t = null;
        // ...
        return t;
    }

    /**
     * Eine Functional-Unit mit zwei Output-Pins als Output-Parameter.
     */
    public <T, U> void a(T outputT, U outputU) {
        // ...
    }

    /**
     * Split von A auf B und C sowie Join von B und C auf D.
     */
    public <T, U, S, V> void foo() {
        T t = null;
        U u = null;
        a(t, u);

        S s = b(t);
        V v = c(u);

        d(Tuple.of(s, v));
    }

    private <U, T> U c(T input) {
        U u = null;
        // ...
        return u;
    }

    private <U, T> void d(Tuple<T, U> input) {
        // ...
    }

}
