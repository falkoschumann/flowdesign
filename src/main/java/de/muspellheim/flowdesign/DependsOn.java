/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */
package de.muspellheim.flowdesign;

/**
 * Diese Schnittstelle sollte von Functional-Units implementiert werden, die eine Abhängigkeit besitzen.
 *
 * @param <T> der Typ der Abhängigkeit, üblicherweise die Schnittstelle eines Services.
 * @author Falko Schumann &lt;www.muspellheim.de&gt;
 */
public interface DependsOn<T> {

    /**
     * Injiziert die Abhängigkeit in die Functional-Unit.
     */
    void inject(T dependency);

}
