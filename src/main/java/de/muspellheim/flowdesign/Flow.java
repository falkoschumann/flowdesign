/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Basisklasse oder Beispielklasse für die Initialisierung eines Flows für eine Applikation. Die
 * {@code protected}-Methoden werden überschrieben, um die entsprechende Phase zu realisiereren.
 *
 * <ol>
 * <li>{@link #build()}</li>
 * <li>{@link #bind()}</li>
 * <li>{@link #inject()}</li>
 * <li>{@link #configure(String[])}</li>
 * <li>{@link #run(String[])}</li>
 * </ol>
 *
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public abstract class Flow {

    /**
     * Schablonenmethode zur Initialisierung und zum Start des Flows.
     *
     * @param args die Kommandozeilenoptionen der Applikation.
     */
    public final void initializeAndStart(String[] args) {
        build();
        bind();
        inject();
        configure(args);
        run(args);
    }

    /**
     * Diese Methode überschreiben, um in ihr die Functional-Units anzulegen.
     */
    protected void build() {
    }

    /**
     * Diese Methode überschreiben, um in ihr die Output-Pins und Input-Pins der Functional-Units miteinander zu
     * verbinden.
     */
    protected void bind() {
    }

    /**
     * Diese Methode überschreiben, um die Abhängigkeiten der Functional-Units in diese zu injizieren. Wenn
     * Abhängigkeiten per Konstruktor injiziert werden, geschieht dies bereits in {@link #build()}.
     */
    protected void inject() {
    }

    /**
     * Diese Methode überschreiben, um die Functional-Units zu konfigurieren. Die Kommandozeilenargumente der
     * Applikation werden an die Functional-Units weitergereicht, die sie benötigen, damit diese ihre Konfiguration
     * davon lesen können.
     *
     * @param args die Kommandozeilenoptionen der Applikation.
     */
    protected void configure(String[] args) {
    }

    /**
     * Diese Methode überschreiben, um die Functional-Units die den Entry-Point des Flows darstellt zu starten.
     * Der Entry-Point bekommt ebenfalls die Kommandozeilenargumente der Applikation übergeben.
     *
     * @param args die Kommandozeilenoptionen der Applikation.
     */
    protected void run(String[] args) {
    }

}
