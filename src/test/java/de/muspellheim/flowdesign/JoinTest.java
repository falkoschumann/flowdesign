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
        join.connectOutputPinWith(this::setTuple);
        assertNull(tuple);

        join.processInput1("Foo");
        assertNull(tuple);

        join.processInput2(42);
        assertEquals(Tuple.of("Foo", 42), tuple);
    }

    @Test
    public void testJoinInput2ThenInput1() {
        join.connectOutputPinWith(this::setTuple);
        assertNull(tuple);

        join.processInput2(42);
        assertNull(tuple);

        join.processInput1("Foo");
        assertEquals(Tuple.of("Foo", 42), tuple);
    }

    @Test
    public void testJoinDisconnected() {
        Consumer<Tuple<String, Integer>> output = this::setTuple;
        join.connectOutputPinWith(output);
        assertNull(tuple);

        join.processInput1("Foo");
        assertNull(tuple);

        join.disconnectOutputPinFrom(output);
        join.processInput2(42);
        assertNull(tuple);
    }

    @Test
    public void testJoinOnlyIfAllInputReceived() {
        join.connectOutputPinWith(this::setTuple);
        assertNull(tuple);

        join.processInput1("Foo");
        assertNull(tuple);

        join.processInput1("Bar");
        assertNull(tuple);

        join.processInput2(42);
        assertEquals(Tuple.of("Bar", 42), tuple);
    }

}
