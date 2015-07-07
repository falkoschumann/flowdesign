/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Eine Functional-Unit mit einem Input-Pin und einem Output-Pin.
 *
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public abstract class FunctionaUnit<T, U> {

    private final OutputPin<U> result = new OutputPin<>();

    public abstract void process(T input);

    public OutputPin<U> result() {
        return result;
    }

}
