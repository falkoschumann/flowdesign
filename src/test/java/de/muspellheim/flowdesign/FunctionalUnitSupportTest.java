/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */
package de.muspellheim.flowdesign;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class FunctionalUnitSupportTest {

    private TestingFunctionalUnit fu;
    private String textResult;
    private int numberResult;

    @Before
    public void setUp() {
        fu = new TestingFunctionalUnit();
    }

    private void processText(String text) {
        textResult = text;
    }

    private void processNumber(Integer number) {
        numberResult = number;
    }


    @Test
    public void testOutputPins() {
        fu.connectTextPinWith(this::processText);
        fu.connectNumberPinWith(this::processNumber);

        fu.processText("Foo");
        assertEquals(textResult, "Foo");

        fu.processNumber(42);
        assertEquals(42, numberResult);

        fu.processText("Bar");
        assertEquals(textResult, "Bar");
        assertEquals(42, numberResult);
    }

    @Test
    public void testDisconnect() {
        Consumer<Integer> consumer = this::processNumber;
        fu.connectTextPinWith(this::processText);
        fu.connectNumberPinWith(consumer);

        fu.processText("Foo");
        assertEquals(textResult, "Foo");

        fu.processNumber(42);
        assertEquals(42, numberResult);

        fu.disconnectNumberPinFrom(consumer);
        fu.processNumber(13);
        assertEquals(42, numberResult);
        fu.processText("Bar");
        assertEquals(textResult, "Bar");

    }

    private static final class TestingFunctionalUnit {

        private final FunctionalUnitSupport delegate = new FunctionalUnitSupport();

        public void processText(String text) {
            delegate.publishResultFor(String.class, text);
        }

        public void connectTextPinWith(Consumer<String> inputPin) {
            delegate.connectOutputPinWith(String.class, inputPin);
        }

        public void disconnectTextPinFrom(Consumer<String> inputPin) {
            delegate.disconnectOutputPinFrom(String.class, inputPin);
        }

        public void processNumber(Integer number) {
            delegate.publishResultFor(Integer.class, number);
        }

        public void connectNumberPinWith(Consumer<Integer> inputPin) {
            delegate.connectOutputPinWith(Integer.class, inputPin);
        }

        public void disconnectNumberPinFrom(Consumer<Integer> inputPin) {
            delegate.disconnectOutputPinFrom(Integer.class, inputPin);
        }

    }

    /**
     * Wird nicht zum Ausführen von Tests benötigt. Validiert, ob die API vom Java-Compiler akzeptiert wird. Wegen der
     * Typlöschung zur Laufzeit lässt sich folgendes nicht Kompilieren, wenn die Generics in der Schnittstelle von
     * {@link FunctionalUnitSupport} nicht korrekt angegeben sind.
     */
    public static final class GenericsSyntaxTest {

        private final FunctionalUnitSupport delegate = new FunctionalUnitSupport();

        public void connectListPinWith(Consumer<List<String>> inputPin) {
            delegate.connectOutputPinWith(List.class, inputPin);
        }

        public void disconnectListPinFrom(Consumer<List<String>> inputPin) {
            delegate.disconnectOutputPinFrom(List.class, inputPin);
        }

        public void publishList(List<String> result) {
            delegate.publishResultFor(List.class, result);
        }

    }

}
