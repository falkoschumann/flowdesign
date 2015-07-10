/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Diese Schnittstelle markiert eine Functional-Unit als Entry-Point und startet den Datenfluss.
 *
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public interface EntryPoint {

    /**
     * Startet den Fluss am Entry-Point.
     *
     * @param args die Kommandozeilenoptionen der Applikation.
     */
    void run(String[] args);

}
