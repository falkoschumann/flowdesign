/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * With an input pin a functional unit consumes input data.
 *
 * @param <T> the type of input data.
 * @author Falko Schumann
 * @since 3.0
 */
@FunctionalInterface
public interface InputPin<T> {

    /**
     * Consume an input data.
     *
     * @param input an input data.
     */
    void accept(T input);

}
