/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */
package de.muspellheim.flowdesign;

/**
 * Diese Schnittstelle sollte von einer Functional-Units implementiert werden, die der Startpunkt eines Flussdiagramms
 * darstellt.
 *
 * @author Falko Schumann &lt;www.muspellheim.de&gt;
 */
public interface EntryPoint {

    /**
     * Initialisiert den Fluss mit den Argumenten der Applikation.
     */
    void run(String[] args);

}
