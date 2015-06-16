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

public class FunctionalUnitTest {

    private int result;

    @Test
    public void testInputPin() {
        FunctionalUnit<String, Integer> parser = new FunctionalUnit<>(this::parse);
        parser.process("42");
        assertEquals(42, result);
    }

    private void parse(String s) {
        result = Integer.valueOf(s);
    }

    @Test
    public void testOutputPin() {
        FunctionalUnit<String, Integer> parser = new FunctionalUnit<>();
        parser.connectOutputPinWith(this::setResult);

        parser.publishResult(42);
        assertEquals(42, result);
    }

    private void setResult(int result) {
        this.result = result;
    }

    @Test
    public void testDisconnect() {
        FunctionalUnit<String, Integer> parser = new FunctionalUnit<>();
        Consumer<Integer> inputPin = this::setResult;

        parser.connectOutputPinWith(inputPin);
        parser.publishResult(42);
        assertEquals(42, result);

        parser.disconnectOutputPinFrom(inputPin);
        parser.publishResult(13);
        assertEquals(42, result);
    }

}
