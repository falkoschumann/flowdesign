/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Base class or example for a functional unit with one input pin and one output pin.
 *
 * @param <I> the type of input data.
 * @param <O> the type of output data.
 * @author Falko Schumann
 * @since 3.1
 */
public abstract class BaseFunctionalUnit<I, O> {

    private final OutputPin<O> result = new OutputPin<>();

    /**
     * The input pin of this functional unit.
     * <p>
     * Override this method to receive input data and publish a result with {@link #result()}.
     *
     * @param input an input data.
     * @see #result()
     */
    public abstract void process(I input);

    /**
     * The output pin of this functional unit.
     * <p>
     * Use the output pin to publish output data.
     *
     * @return the functional units output pin.
     */
    public OutputPin<O> result() {
        return result;
    }

}
