/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */
package de.muspellheim.flowdesign;

import org.junit.Test;

import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class FunctionalUnitSupportTest {

    private int result;

    @Test
    public void testInputPin() {
        FunctionalUnitSupport<String, Integer> parser = new FunctionalUnitSupport<>(this::parse);
        parser.process("42");
        assertEquals(42, result);
    }

    private void parse(String s) {
        result = Integer.valueOf(s);
    }

    @Test
    public void testOutputPin() {
        FunctionalUnitSupport<String, Integer> parser = new FunctionalUnitSupport<>();
        parser.connectResult(this::setResult);

        parser.publishResult(42);
        assertEquals(42, result);
    }

    private void setResult(int result) {
        this.result = result;
    }

    @Test
    public void testDisconnect() {
        FunctionalUnitSupport<String, Integer> parser = new FunctionalUnitSupport<>();
        Consumer<Integer> consumer = this::setResult;

        parser.connectResult(consumer);
        parser.publishResult(42);
        assertEquals(42, result);

        parser.disconnectResult(consumer);
        parser.publishResult(13);
        assertEquals(42, result);
    }

}
