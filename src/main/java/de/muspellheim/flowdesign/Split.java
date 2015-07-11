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
 * @param <T> der Typ des ersten Output-Pins.
 * @param <U> der Typ des zweiten Output-Pins.
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public class Split<T, U> {

    private final OutputPin<T> output1 = new OutputPin<>();
    private final OutputPin<U> output2 = new OutputPin<>();

    /**
     * Der Input-Pin.
     *
     * @param input ein Eingangsdatum.
     */
    public void input(Tuple<T, U> input) {
        output1().publish(input.getFirst());
        output2().publish(input.getSecond());
    }

    /**
     * Der erste Output-Pin.
     *
     * @return der erste Output-Pin.
     */
    public OutputPin<T> output1() {
        return output1;
    }

    /**
     * Der zweite Output-Pin.
     *
     * @return der zweite Output-Pin.
     */
    public OutputPin<U> output2() {
        return output2;
    }

}
