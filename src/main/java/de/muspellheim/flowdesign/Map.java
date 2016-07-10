/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

import java.util.function.Function;

/**
 * This functional unit does a type mapping.
 * <p>The mapping can be set with an mapper function in constructor.</p>
 *
 * @param <T> the type of input data.
 * @param <U> the type of output data.
 * @author Falko Schumann
 * @since 3.0
 */
public class Map<T, U> extends BaseFunctionalUnit<T, U> {

    private final Function<T, U> converter;

    /**
     * Configure this functional unit with a mapper function.
     *
     * @param converter a function to convert from input data type to output data type.
     */
    public Map(Function<T, U> converter) {
        this.converter = converter;
    }

    /**
     * Convert input data to output data.
     * <p>The output data is published on the output pin {@link #result()}.</p>
     *
     * @param input an input data.
     */
    @Override
    public void process(T input) {
        result().publish(converter.apply(input));
    }

}
