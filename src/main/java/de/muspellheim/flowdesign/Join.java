/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Join two input data to one tuple.
 * <p>
 * Every time an input data is received a tuple of the two current objects is
 * published. If an input element is received twice the published tuple holds
 * the same other element. The first tuple is published if both input data are
 * received.
 * <p>
 * This behaviour differs from {@link AutoResetJoin}.
 *
 * @param <T> the type of first input data.
 * @param <U> the type of second input data.
 * @author Falko Schumann
 * @see AutoResetJoin
 * @since 3.0
 */
public class Join<T, U> {

    public final OutputPin<Tuple<T, U>> output = new OutputPin<>();

    private final boolean autoReset;

    private T input1;
    private boolean input1Present;
    private U input2;
    private boolean input2Present;

    /**
     * Creates a join.
     */
    public Join() {
        this(false);
    }

    Join(boolean autoReset) {
        this.autoReset = autoReset;
    }

    /**
     * The first input pin.
     *
     * @param input1 a input data for the first input pin.
     */
    public void input1(T input1) {
        this.input1 = input1;
        input1Present = true;
        if (input1Present && input2Present)
            publishResult();
    }

    /**
     * The Second input pin.
     *
     * @param input2 a input data for the second input pin.
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
     * The output pin publishes data from the two input pins as tuple.
     *
     * @return the output pin.
     */
    public OutputPin<Tuple<T, U>> output() {
        return output;
    }

}
