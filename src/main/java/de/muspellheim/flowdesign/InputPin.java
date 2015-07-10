/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Ein Input-Pin wird für ereignisbasierte Verbindungen zwischen Functional-Units verwendet.
 *
 * @param <T> der Typ der Daten am Pin.
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
@FunctionalInterface
public interface InputPin<T> {

    /**
     * Ein Input-Pin ist eine Methode mit einem Parameter und ohne Rückgabewert.
     *
     * @param input ein Eingangsdatum.
     */
    void accept(T input);

}
