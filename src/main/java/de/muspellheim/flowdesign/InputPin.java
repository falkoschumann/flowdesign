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
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
@FunctionalInterface
public interface InputPin<T> {

    void accept(T input);

}
