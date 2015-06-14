/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */
package de.muspellheim.flowdesign;

import org.junit.Before;
import org.junit.Test;

import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JoinTest {

    private Join<String, Integer> join;
    private Tuple<String, Integer> tuple;

    @Before
    public void setUp() {
        join = new Join<>();
    }

    private void setTuple(Tuple<String, Integer> tuple) {
        this.tuple = tuple;
    }

    @Test
    public void testJoinInput1ThenInput2() {
        join.connectOutput(this::setTuple);
        assertNull(tuple);

        join.input1("Foo");
        assertNull(tuple);

        join.input2(42);
        assertEquals(new Tuple<>("Foo", 42), tuple);
    }

    @Test
    public void testJoinInput2ThenInput1() {
        join.connectOutput(this::setTuple);
        assertNull(tuple);

        join.input2(42);
        assertNull(tuple);

        join.input1("Foo");
        assertEquals(new Tuple<>("Foo", 42), tuple);
    }

    @Test
    public void testJoinDisconnected() {
        Consumer<Tuple<String, Integer>> output = this::setTuple;
        join.connectOutput(output);
        assertNull(tuple);

        join.input1("Foo");
        assertNull(tuple);

        join.disconnectOutput(output);
        join.input2(42);
        assertNull(tuple);
    }

}
