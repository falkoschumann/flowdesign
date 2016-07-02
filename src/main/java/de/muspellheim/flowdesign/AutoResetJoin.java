/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Verbindet zwei Inputs zu einem gemeinsamen Output als Tupel. Wenn beide Inputs eingetroffen sind, werden diese als
 * Tupel veröffentlicht und anschließend zurückgesetzt. Das bedeutet, jeder Input der eintriff, wird immer nur einmal
 * publiziert.
 *
 * @param <T> der Typ des ersten Input-Pins.
 * @param <U> der Typ des zweiten Input-Pins..
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 * @see Join
 */
public class AutoResetJoin<T, U> extends Join<T, U> {

    /**
     * Erzeugt einen Auto-Reset-Join.
     */
    public AutoResetJoin() {
        super(true);
    }

}
