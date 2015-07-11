/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Diese Schnittstelle konfiguriert eine Functional-Unit.
 *
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public interface Configurable {

    /**
     * Als Konfiguration werden die Kommandozeilenoptionen der Applikation übergeben.
     *
     * @param args die Kommandozeilenoptionen der Applikattion.
     */
    void configure(String[] args);

}
