/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Diese Schnittstelle dient dem Injizieren von expliziten Abhängigkeiten in eine Functional-Unit.
 *
 * @param <T> der Typ der Abhängigkeit.
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public interface DependsOn<T> {

    /**
     * Injiziert die Abhängigkeit.
     *
     * @param object die Abhängigkeit.
     */
    void inject(T object);

}
