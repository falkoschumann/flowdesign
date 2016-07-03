/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Marks a functional unit to be configured.
 *
 * @author Falko Schumann
 * @since 3.0
 */
public interface Configurable {

    /**
     * Configure the functional unit with the applications command line arguments.
     *
     * @param args the command line arguments of the application.
     */
    void configure(String[] args);

}
