/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit-Tests für Join.
 *
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public class JoinTest {

    private Join<Integer, Integer> join;
    private Tuple<Integer, Integer> output;

    @Before
    public void setUp() {
        join = new Join<>();
        join.output().connect(this::setOutput);
    }

    private void setOutput(Tuple<Integer, Integer> output) {
        this.output = output;
    }

    @Test
    public void testSingleInput() {
        join.input1(4);
        join.input2(2);

        assertEquals(Tuple.of(4, 2), output);
    }

    @Test
    public void testMultipleInput2() {
        join.input1(4);
        join.input2(2);
        assertEquals(Tuple.of(4, 2), output);

        join.input2(3);
        assertEquals(Tuple.of(4, 3), output);
    }

    @Test
    public void testMultipleInput1() {
        join.input1(4);
        join.input2(2);
        assertEquals(Tuple.of(4, 2), output);

        join.input1(3);
        assertEquals(Tuple.of(3, 2), output);
    }

}
