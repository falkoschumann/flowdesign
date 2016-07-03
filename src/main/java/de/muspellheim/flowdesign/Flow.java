/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Base class or example to implement a flow application.
 * <p>
 * An implementation must override the protected methods as described and call
 * {@link #initializeAndStart(String[])} in the main method.
 *
 * @author Falko Schumann
 * @since 3.0
 */
public abstract class Flow {

    /**
     * This template method initialize and start the application.
     * <p>
     * Calls the the following methods in the given order:
     * <ol>
     * <li>{@link #build()}</li>
     * <li>{@link #bind()}</li>
     * <li>{@link #inject()}</li>
     * <li>{@link #configure(String[])}</li>
     * <li>{@link #run(String[])}</li>
     * </ol>
     *
     * @param args the command line arguments of the application.
     */
    public final void initializeAndStart(String[] args) {
        build();
        bind();
        inject();
        configure(args);
        run(args);
    }

    /**
     * Override this method to create functional units before other methods are called.
     */
    protected void build() {
    }

    /**
     * Override this method to connect output pins with input pins of functional units.
     */
    protected void bind() {
    }

    /**
     * Override this method to inject dependencies in functional units.
     */
    protected void inject() {
    }

    /**
     * Override this method to configure functional units.
     * <p>
     * The command line arguments are passed to functional units.
     *
     * @param args the command line arguments of the application.
     */
    protected void configure(String[] args) {
    }

    /**
     * Override this method to start the application by call its entry point.
     * <p>
     * The command line arguments are passed also to the applications entry point.
     *
     * @param args the command line arguments of the application.
     */
    protected void run(String[] args) {
    }

}
