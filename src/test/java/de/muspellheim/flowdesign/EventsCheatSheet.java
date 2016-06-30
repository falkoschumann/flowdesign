/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

import java.util.Iterator;

/**
 * Zeigt wie Functional-Units mit Hilfe von Ereignissen zusammengesteckt werden können.
 *
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public class EventsCheatSheet {

    /**
     * Ein einfaches Beispiel einer Functional-Unit mit einem Input T und einem Output U. Gibt es nur jeweils einen
     * Input-Pin und Output-Pin lauten die Standardnamen für den Input-Pin <em>Process</em> und für den Output-Pin
     * <em>Result</em>.
     */
    class A<T, U> {

        private final OutputPin<U> result = new OutputPin<>();

        public void process(T input) {
            // ...
        }

        public OutputPin<U> result() {
            return result;
        }

    }

    /**
     * Ein Beispiel einer Functional-Unit mit zwei Input-Pins und zwei Output-Pins.
     */
    class A1<T, S, U, V> {

        private final OutputPin<U> error = new OutputPin<>();
        private final OutputPin<V> dataLoaded = new OutputPin<>();

        public void load(T input) {
            // ...
        }

        public void store(S input) {
            // ...
        }

        public OutputPin<U> error() {
            return error;
        }

        public OutputPin<V> dataLoaded() {
            return dataLoaded;
        }

    }

    /**
     * Ein zusammengesetzten Beispiel. Die beiden Parts A und B werden zum Board X zusammengesteckt.
     */
    class X<T, S, U> {

        private InputPin<T> process;
        private OutputPin<U> result;

        public X(A<T, S> a, B<S, U> b) {
            process = a::process;
            a.result().connect(b::process);
            b.result().connect(result::publish);
        }

        public void process(T input) {
            process.accept(input);
        }

        public OutputPin<U> result() {
            return result;
        }

    }

    class B<T, U> {

        private final OutputPin<U> result = new OutputPin<>();

        public void process(T input) {
            // ...
        }

        public OutputPin<U> result() {
            return result;
        }

    }

    /**
     * Eine Functional-Unit mit zwei Output-Pins zu einem Tuple vereint.
     */
    public <T, U> void join() {
        A<?, T> a = new A<>();
        B<?, U> b = new B<>();
        C<Tuple<T, U>, ?> c = new C<>();

        Join<T, U> j = new Join<>();

        a.result().connect(j::input1);
        b.result().connect(j::input2);

        j.output().connect(c::process);
    }

    class C<T, U> {

        private final OutputPin<U> result = new OutputPin<>();

        public void process(T input) {
            // ...
        }

        public OutputPin<U> result() {
            return result;
        }

    }

    /**
     * Eine Menge von Datenelementen veröffentlichen in Form von einzelnen Datenelementen.
     */
    class SplitLineIntoWords {

        private final OutputPin<String> result = new OutputPin<>();

        public void process(String line) {
            for (String word : line.split(" "))
                result().publish(word);
        }

        public OutputPin<String> result() {
            return result;
        }

    }

    /**
     * Eine Menge von Datenelementen veröffentlichen in Form eines Iterators.
     */
    class SplitLineIntoWordsWithIterator {

        private final OutputPin<Iterator<String>> result = new OutputPin<>();

        public void process(String line) {
            // ...
        }

        public OutputPin<Iterator<String>> result() {
            return result;
        }

    }


}
