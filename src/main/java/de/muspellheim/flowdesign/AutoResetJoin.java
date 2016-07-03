/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Join two input data to one tuple.
 * <p>
 * Only publishes a tuple if both input data are received. So every input data
 * is published only one time.
 * <p>
 * This behaviour differs from {@link Join}.
 *
 * @param <T> the type of first input data.
 * @param <U> the type of second input data.
 * @author Falko Schumann
 * @see Join
 * @since 3.0
 */
public class AutoResetJoin<T, U> extends Join<T, U> {

    /**
     * Creates a join.
     */
    public AutoResetJoin() {
        super(true);
    }

}
