/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Verbindet zwei Inputs zu einem gemeinsamen Output als Tupel.
 *
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public class Join<T, U> {

    private final OutputPin<Tuple<T, U>> output = new OutputPin<>();
    private T input1;
    private U input2;

    public void input1(T input1) {
        this.input1 = input1;
        publishJoined();
    }

    public void input2(U input2) {
        this.input2 = input2;
        publishJoined();
    }

    private void publishJoined() {
        output().publish(Tuple.of(input1, input2));
    }

    public OutputPin<Tuple<T, U>> output() {
        return output;
    }

}
