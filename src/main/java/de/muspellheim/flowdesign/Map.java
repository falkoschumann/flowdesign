/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

import java.util.function.Function;

/**
 * Konvertiert einen Wert in einen anderen.
 *
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public class Map<T, U> extends FunctionaUnit<T, U> {

    private final Function<T, U> converter;

    public Map(Function<T, U> converter) {
        this.converter = converter;
    }

    @Override
    public void process(T input) {
        result().publish(converter.apply(input));
    }

}
