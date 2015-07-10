/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Ein Output-Pin wird für ereignisbasierte Verbindungen zwischen Functional-Units verwendet.
 *
 * @param <T> der Typ des Output-Pins.
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public class OutputPin<T> {

    private final List<InputPin<T>> wires = new CopyOnWriteArrayList<>();

    /**
     * Verbindet den Output-Pin mit einem Input-Pin.
     *
     * @param inputPin der zu verbindende Input-Pin.
     */
    public void connect(InputPin<T> inputPin) {
        wires.add(inputPin);
    }

    /**
     * Trennt die Verbindung des Output-Pis mit einem Input-Pin.
     *
     * @param inputPin der zu trennende Input-Pin.
     */
    public void disconnect(InputPin<T> inputPin) {
        wires.remove(inputPin);
    }

    /**
     * Veröffentlicht einen Wert für diesen Output-Pin als Ereignis.
     *
     * @param output der zu veröffentlichende Wert.
     */
    public void publish(T output) {
        wires.stream().forEach(in -> in.accept(output));
    }

}
