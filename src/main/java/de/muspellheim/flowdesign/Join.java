/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Verbindet zwei Inputs zu einem gemeinsamen Output als Tupel. Jedesmal, wenn einer der beiden Inputs eintrifft, wird
 * der Output veröffentlicht. Wenn zweimal hintereinader der zweite Input eintriff, dabei jeweils der gleiche erste
 * Input veröffentlicht.  Für die erste Veröffentlichung müssen einmalig beide Inputs eingetroffen sein.
 *
 * @param <T> der Typ des ersten Input-Pins.
 * @param <U> der Typ des zweiten Input-Pins.
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 * @see AutoResetJoin
 */
public class Join<T, U> {

    private final OutputPin<Tuple<T, U>> output = new OutputPin<>();

    private final boolean autoReset;

    private T input1;
    private boolean input1Present;
    private U input2;
    private boolean input2Present;

    /**
     * Erzeugt einen Join.
     */
    public Join() {
        this(false);
    }

    /**
     * Initialisiert den Join.
     *
     * @param autoReset wenn das Flag gesetzt ist, wird die Präsenz aller Eingangsdaten zurückgesetzt.
     */
    protected Join(boolean autoReset) {
        this.autoReset = autoReset;
    }

    /**
     * Der erste Input-Pin.
     *
     * @param input1 ein Eingangsdatum.
     */
    public void input1(T input1) {
        this.input1 = input1;
        input1Present = true;
        if (input1Present && input2Present)
            publishResult();
    }

    /**
     * Der zweite Input-Pin.
     *
     * @param input2 ein Eingangsdatum.
     */
    public void input2(U input2) {
        this.input2 = input2;
        input2Present = true;
        if (input1Present && input2Present)
            publishResult();
    }

    private void publishResult() {
        output().publish(Tuple.of(input1, input2));
        if (autoReset)
            clearInputPresence();
    }

    private void clearInputPresence() {
        input1Present = false;
        input2Present = false;
    }

    /**
     * Der Output-Pin.
     *
     * @return der Output-Pin.
     */
    public OutputPin<Tuple<T, U>> output() {
        return output;
    }

}
