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
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public class OutputPin<T> {

    private final List<InputPin<T>> wires = new CopyOnWriteArrayList<>();

    public void connect(InputPin<T> wire) {
        wires.add(wire);
    }

    public void disconnect(InputPin<T> wire) {
        wires.remove(wire);
    }

    public void publish(T output) {
        wires.stream().forEach(in -> in.accept(output));
    }

}
