/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Verbindet zwei Inputs zu einem gemeinsamen Output als Tupel. Wenn beide Inputs eingetroffen sind, werden diese als
 * Tupel veröffentlicht und anschließend zurückgesetzt. Das bedeutet, jeder Input der eintriff, wird immer nur einmal
 * publiziert.
 *
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 * @see Join
 */
public class AutoResetJoin<T, U> extends Join<T, U> {

    public AutoResetJoin() {
        super(true);
    }

}
