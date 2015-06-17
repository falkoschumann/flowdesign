/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */
package de.muspellheim.flowdesign;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * Hilfsklasse für die Implementierung von Functional-Units mit mehreren Outputpins. Die verschiedenen Outputpins werden
 * über ihren Typ unterschieden.
 *
 * <p>Die Klasse sollte gewrappt werden, kann aber auch erweitert werden, um eine Functional-Unit zu implementieren.</p>
 *
 * @author Falko Schumann &lt;www.muspellheim.de&gt;
 */
public class FunctionalUnitSupport {

    private final Map<Class<?>, List<Consumer>> consumers = new LinkedHashMap<>();

    /**
     * Verbindet den Outputpin des angegebenen Typs dieser Functional-Unit mit dem Inputpin einer anderen
     * Functional-Unit.
     */
    public <T> void connectOutputPinWith(Class<? extends T> outputType, Consumer<? extends T> inputPin) {
        Objects.requireNonNull(inputPin);
        synchronized (this) {
            if (!consumers.containsKey(outputType))
                consumers.put(outputType, new CopyOnWriteArrayList<>());
            consumers.get(outputType).add(inputPin);
        }
    }

    /**
     * Trennt die Verbindung des Outputpins des angegebenen Typs dieser Functional-Unit mit dem Inputpin einer anderen
     * Functional-Unit.
     */
    public <T> void disconnectOutputPinFrom(Class<? extends T> outputType, Consumer<? extends T> inputPin) {
        Objects.requireNonNull(inputPin);
        synchronized (this) {
            if (consumers.containsKey(outputType))
                consumers.get(outputType).remove(inputPin);
        }
    }

    /**
     * Veröffentlicht ein Ergebnis am Outputpin mit dem angegebenen Typ dieser Functional-Unit.
     */
    public <T> void publishResultFor(Class<? extends T> outputType, T result) {
        synchronized (this) {
            if (consumers.containsKey(outputType))
                consumers.get(outputType).forEach(c -> c.accept(result));
        }
    }

}
