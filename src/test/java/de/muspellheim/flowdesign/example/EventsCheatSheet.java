/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign.example;

import de.muspellheim.flowdesign.InputPin;
import de.muspellheim.flowdesign.Join;
import de.muspellheim.flowdesign.OutputPin;
import de.muspellheim.flowdesign.Tuple;

import java.util.Iterator;

/**
 * Shows how to transfer a functional unit into classes with events.
 *
 * @author Falko Schumann
 */
public class EventsCheatSheet {

    /**
     * A simple example of a functional unit with one input pin T and one output pin U.
     */
    public static class A<T, U> {

        public final OutputPin<U> result = new OutputPin<>();

        public void process(T input) {
            // ...
        }

    }

    public static class A1<T, S, U, V> {

        public final OutputPin<U> error = new OutputPin<>();
        public final OutputPin<V> dataLoaded = new OutputPin<>();

        public void load(T input) {
            // ...
        }

        public void store(S input) {
            // ...
        }

    }

    /**
     * A compound example. The parts A and B are compounded to board X.
     */
    public static class X<T, S, U> {

        public OutputPin<U> result;
        private InputPin<T> process;

        public X(A<T, S> a, B<S, U> b) {
            process = a::process;
            a.result.connect(b::process);
            b.result.connect(result::publish);
        }

        public void process(T input) {
            process.accept(input);
        }

    }

    public static class B<T, U> {

        public final OutputPin<U> result = new OutputPin<>();

        public void process(T input) {
            // ...
        }

    }

    /**
     * Join two input data into a tuple.
     */
    public <T, U> void join() {
        A<?, T> a = new A<>();
        B<?, U> b = new B<>();
        C<Tuple<T, U>, ?> c = new C<>();

        Join<T, U> j = new Join<>();

        a.result.connect(j::input1);
        b.result.connect(j::input2);

        j.output.connect(c::process);
    }

    public static class C<T, U> {

        public final OutputPin<U> result = new OutputPin<>();

        public void process(T input) {
            // ...
        }

    }

    /**
     * Publish a sequence of elements as individual output data.
     */
    public static class SplitLineIntoWords {

        public final OutputPin<String> result = new OutputPin<>();

        public void process(String line) {
            for (String word : line.split(" "))
                result.publish(word);
        }

    }

    /**
     * Publish a sequence of elements as iterator.
     */
    public static class SplitLineIntoWordsWithIterator {

        public final OutputPin<Iterator<String>> result = new OutputPin<>();

        public void process(String line) {
            // ...
        }

    }

}
