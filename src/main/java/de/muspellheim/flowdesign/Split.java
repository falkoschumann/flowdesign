/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Trennt einen Tupel-Input zu zwei Outputs.
 *
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public class Split<T, U> {

    private final OutputPin<T> output1 = new OutputPin<>();
    private final OutputPin<U> output2 = new OutputPin<>();

    public void input(Tuple<T, U> input) {
        output1().publish(input.getFirst());
        output2().publish(input.getSecond());
    }

    public OutputPin<T> output1() {
        return output1;
    }

    public OutputPin<U> output2() {
        return output2;
    }

}
