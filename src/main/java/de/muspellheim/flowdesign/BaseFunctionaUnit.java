/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Eine Functional-Unit mit einem Input-Pin und einem Output-Pin.
 *
 * @param <T> der Typ des Input-Pins.
 * @param <U> der Typ des Output-Pins.
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public abstract class BaseFunctionaUnit<T, U> {

    private final OutputPin<U> result = new OutputPin<>();

    /**
     * Der Input-Pin der Functional-Unit. Diese Methoden überschreiben und das Eingangsdatum verarbeiten, anschließend
     * das Ergebnis am Output-Pin veröffentlichen.
     *
     * @param input ein Eingangsdatum.
     * @see #result()
     */
    public abstract void process(T input);

    /**
     * Der Output-Pin der Functional-Unit. Ein Ausgangsdatum wird per Ereignis veröffentlicht.
     *
     * @return der Output-Pin.
     */
    public OutputPin<U> result() {
        return result;
    }

}
