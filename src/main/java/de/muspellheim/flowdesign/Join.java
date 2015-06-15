/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */
package de.muspellheim.flowdesign;

import java.util.function.Consumer;

/**
 * Implementiert einen Join zweier Eingangswerte zu einen Tupel. Bei jeden eingetroffenen Eingangswert wird das Tupel
 * als Ergebnis veröffentlicht. Um das erste Tupel zu veröffentlichen, müssen einmalig beide Eingangswerte eingetroffen
 * sein.
 *
 * @param <IN1> der Typ des ersten Inputpins, üblicherweise eine Struktur.
 * @param <IN2> der Typ des zweiten Outputpins, üblicherweise eine Struktur.
 * @author Falko Schumann &lt;www.muspellheim.de&gt;
 */
public class Join<IN1, IN2> {

    private final FunctionalUnitSupport<?, Tuple<IN1, IN2>> consumers = new FunctionalUnitSupport<>();

    private IN1 input1;
    private IN2 input2;

    /**
     * Nimmt den ersten Eingangswert entgegen und versucht ein Tupel zu veröffentlichen.
     */
    public void input1(IN1 input) {
        input1 = input;
        tryJoin();
    }

    /**
     * Nimmt den zweiten Eingangswert entgegen und versucht ein Tupel zu veröffentlichen.
     */
    public void input2(IN2 input) {
        input2 = input;
        tryJoin();
    }

    private synchronized void tryJoin() {
        if (input1 != null && input2 != null) {
            consumers.publishResult(Tuple.of(input1, input2));
        }
    }


    /**
     * Verbinden den Outputpin dieser Functional-Unit mit dem Inputpin einer anderen Functional-Unit.
     */
    public void connectOutput(Consumer<Tuple<IN1, IN2>> consumer) {
        consumers.connectOutput(consumer);
    }

    /**
     * Trennt die Verbindung des Outputpins dieser Functional-Unit mit dem Inputpin einer anderen Functional-Unit.
     */
    public void disconnectOutput(Consumer<Tuple<IN1, IN2>> consumer) {
        consumers.disconnectOutput(consumer);
    }

}
