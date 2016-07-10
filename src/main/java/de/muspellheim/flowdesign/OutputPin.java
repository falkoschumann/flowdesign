/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * With an output pin a functional unit publishes output data.
 * <p>An output data can consume by zero, one or more input pins.</p>
 *
 * @param <T> the type of output data.
 * @author Falko Schumann
 * @since 3.0
 */
public class OutputPin<T> {

    private final List<InputPin<T>> wires = new CopyOnWriteArrayList<>();

    /**
     * Connect this output pin with an input pin.
     *
     * @param inputPin an input pin.
     */
    public void connect(InputPin<T> inputPin) {
        wires.add(inputPin);
    }

    /**
     * Disconnect an input pin from this output pin.
     * <p>If the input pin is not connected with this output pin, this method
     * does nothing.</p>
     *
     * @param inputPin an input pin.
     */
    public void disconnect(InputPin<T> inputPin) {
        wires.remove(inputPin);
    }

    /**
     * Publish an output data to all connected input pins.
     *
     * @param output an output data.
     */
    public void publish(T output) {
        wires.forEach(in -> {
            try {
                in.accept(output);
            } catch (Exception e) {
                Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
            }
        });
    }

}
