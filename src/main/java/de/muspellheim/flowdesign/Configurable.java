/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */
package de.muspellheim.flowdesign;

/**
 * Diese Schnittstelle sollte von Functional-Units implementiert werden, die konfigurierbar sind.
 *
 * @param <T> der Typ der Konfiguration, üblicherweise eine Struktur.
 * @author Falko Schumann &lt;www.muspellheim.de&gt;
 */
public interface Configurable<T> {

    /**
     * Konfiguriert die Functional-Unit mit dem übergebenen Objekt.
     */
    void configure(T configuration);

}
