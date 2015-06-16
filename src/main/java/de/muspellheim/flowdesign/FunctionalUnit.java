/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */
package de.muspellheim.flowdesign;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * Hilfsklasse für die Implementierung einer Functional-Unit mit je einem Inputpin und Outputpin. Die Klasse kann sowohl
 * erweitert als auch gewrappt werden. Beim Wrappen werden die Methoden delegiert.
 *
 * @param <IN>  der Typ des Inputpins, üblicherweise eine Struktur.
 * @param <OUT> der Typ des Outputpins, üblicherweise eine Struktur.
 * @author Falko Schumann &lt;www.muspellheim.de&gt;
 */
public class FunctionalUnit<IN, OUT> {

    private static final Consumer NOP = in -> {};

    private final List<Consumer<OUT>> consumers = new CopyOnWriteArrayList<>();
    private final Consumer<IN> process;

    /**
     * Erzeugt eine Functional-Unit, die beim Eintreffen eines Eingangswertes keine Operation ausführt.
     */
    public FunctionalUnit() {
        this(NOP);
    }

    /**
     * Erzeugt eine Functional-Unit, die beim Eintreffen eines Eingangswertes die angegebene Operation ausführt.
     */
    public FunctionalUnit(Consumer<IN> process) {
        Objects.requireNonNull(process, "process");
        this.process = process;
    }

    /**
     * Nimmt einen Eingangswert entgegen und verarbeitet ihn. Der Verarbeitungsprozess kann durch Überschreiben dieser
     * Methode oder durch Übergabe im Konstruktor festgelegt werden.
     */
    public void process(IN input) {
        process.accept(input);
    }

    /**
     * Verbindet den Outputpin dieser Functional-Unit mit dem Inputpin einer anderen Functional-Unit.
     */
    public void connectOutputPinWith(Consumer<OUT> inputPin) {
        Objects.requireNonNull(inputPin);
        consumers.add(inputPin);
    }

    /**
     * Trennt die Verbindung des Outputpins dieser Functional-Unit mit dem Inputpin einer anderen Functional-Unit.
     */
    public void disconnectOutputPinFrom(Consumer<OUT> inputPin) {
        Objects.requireNonNull(inputPin);
        consumers.remove(inputPin);
    }

    /**
     * Veröffentlicht ein Ergebnis am Outputpin dieser Functional-Unit.
     */
    public void publishResult(OUT result) {
        consumers.forEach(c -> c.accept(result));
    }

}
