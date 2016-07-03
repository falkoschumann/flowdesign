/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Marks a class as entry point for a flow application.
 *
 * @author Falko Schumann
 * @since 3.0
 */
public interface EntryPoint {

    /**
     * Starts the flow of an application.
     *
     * @param args the command line arguments of the application.
     */
    void run(String[] args);

}
