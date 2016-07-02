/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Split the elements of a tuple into two data objects.
 *
 * @param <T> the type of the first tuple element.
 * @param <U> the type of the second tuple element.
 * @author Falko Schumann
 * @since 3.0
 */
public class Split<T, U> {

    private final OutputPin<T> output1 = new OutputPin<>();
    private final OutputPin<U> output2 = new OutputPin<>();

    /**
     * The input pin receive a tuple.
     * <p>
     * If a tuple is received, first element is published on {@link #output1()}
     * and second element is published on {@link #output2()}.
     *
     * @param input a tuple.
     */
    public void input(Tuple<T, U> input) {
        output1().publish(input.getFirst());
        output2().publish(input.getSecond());
    }

    /**
     * This output pin published the first element of received tuple.
     *
     * @return the first output pin.
     */
    public OutputPin<T> output1() {
        return output1;
    }

    /**
     * This output pin published the second element of received tuple.
     *
     * @return the second output pin.
     */
    public OutputPin<U> output2() {
        return output2;
    }

}
