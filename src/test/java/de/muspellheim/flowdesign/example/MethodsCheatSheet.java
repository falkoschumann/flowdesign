/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign.example;

import de.muspellheim.flowdesign.Tuple;

/**
 * Shows how to transfer functional units into simple methods.
 *
 * @author Falko Schumann
 */
public class MethodsCheatSheet {

    /**
     * A simple example of a functional unit with one input pin T and one output pin U.
     */
    public <U, T> U a(T input) {
        U u = null;
        // ...
        return u;
    }

    /**
     * A compound example. The parts A and B are compounded to board X.
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
     * A functional unit with two input data as tuple.
     */
    public <T, U> void a(Tuple<T, U> input) {
        // ...
    }

    /**
     * A functional unit with two output data as tuple.
     */
    public <T, U> Tuple<T, U> a() {
        Tuple<T, U> t = null;
        // ...
        return t;
    }

    /**
     * A functional unit with two output pins as output parameter.
     */
    public <T, U> void a(T outputT, U outputU) {
        // ...
    }

    /**
     * This functional unit splits A into B and C and joins B and C into D.
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
