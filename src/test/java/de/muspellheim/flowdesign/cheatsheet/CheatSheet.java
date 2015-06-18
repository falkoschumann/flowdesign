package de.muspellheim.flowdesign.cheatsheet;

import de.muspellheim.flowdesign.*;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Implementierung des Cheat-Sheets zu Flow-Design von XYZ.
 * <p>
 * <a href="http://geekswithblogs.net/theArchitectsNapkin/archive/2011/03/20/flow-design-cheat-sheet-ndash-part-ii-translation.aspx">[Quelle]</a>
 */
public class CheatSheet {

    // TODO Klasse splitten und besser organisieren

    /**
     * Eine Functional-Unit als Methode. Diese FU ist ein Part.
     *
     * @param input Input-Pin.
     * @param <T>   Typ des Inputs.
     * @param <U>   Typ des Outputs.
     * @return Output-Pin.
     */
    public <T, U> U a(T input) {
        U u = null;
        // ...
        return u;
    }

    /**
     * Eine Functional-Unit als Methode. Diese FU ist ein Board.
     *
     * @param input Input-Pin.
     * @param <T>   Typ des Inputs.
     * @param <U>   Typ des Outputs.
     * @return Output-Pin.
     */
    public <T, U> U x(T input) {
        Object s = a(input);
        U u = b(s);
        return u;
    }

    public <S, U> U b(S input) {
        U u = null;
        // ...
        return u;
    }

    /**
     * Mehrere Input-Pins werden als Tupel zusammengefasst.
     */
    public <T, U> void a(Tuple<T, U> input) {
        // ...
    }

    /**
     * Mehrere Output-Pins werden als Tupel zusammengefasst.
     */
    public <T, U> Tuple<T, U> a() {
        Tuple<T, U> t = null;
        // ...
        return t;
    }

    /**
     * Mehrere Output-Pins werden als Output-Parameter angegeben.
     */
    public <T, U> void a(T t, U u) {
        // ...
    }

    /**
     * Splits und Joins.
     */
    public <T, S, U, V> void splitAndJoin() {
        T t = null;
        U u = null;
        a(t, u);

        S s = b(t);
        V v = c(u);

        d(Tuple.of(s, v));
    }

    public <T, U> U c(T input) {
        U u = null;
        // ...
        return u;
    }

    public <S, V> void d(Tuple<S, V> input) {
        // ...
    }

    /**
     * Verbindungen zwischen Functional-Units herstellen.
     */
    public void wiring() {
        A a = new A();
        B b = new B();
        a.connectOutputPinWith(b::process);
    }

    public void wiringSplit() {
        A a = new A();
        B b = new B();
        C c = new C();

        a.connectOutputPinWith(b::process);
        a.connectOutputPinWith(c::process);
    }

    public <T, U> void wiringJoin() {
        Join<T, U> j = new Join<>();
        A<Void, T> a = new A();
        B<Void, U> b = new B();
        C<Tuple<T, U>, Void, Void> c = new C();

        a.connectOutputPinWith(j::processInput1);
        b.connectOutputPinWith(j::processInput2);

        j.connectOutputPinWith(c::process);
    }

    /**
     * Eine Functional-Unit als Klasse. Diese FU ist ein Part.
     *
     * @param <T> Typ des Inputs.
     * @param <U> Typ des Outputs.
     */
    public static class A<T, U> extends FunctionalUnit<T, U> implements EntryPoint {

        @Override
        public void process(T input) {
            // ...
        }

        @Override
        public void run(String[] args) {
            // ....
        }

        @Configure
        public void configure(String[] args) {
            // ...
        }

    }

    /**
     * Eine Functional-Unit als Klasse. Diese FU ist ein Board.
     *
     * @param <T> Typ des Inputs.
     * @param <U> Typ des Outputs.
     */
    public static class X<T, U> extends FunctionalUnit<T, U> {

        private Consumer<T> process;

        public <S> X(A<T, S> a, B<S, U> b) {
            process = a::process;
            a.connectOutputPinWith(b::process);
            b.connectOutputPinWith(this::publishResult);
        }

        public void process(T input) {
            process.accept(input);
        }

    }

    public static class B<S, U> extends FunctionalUnit<S, U> {

        public void process(S input) {
            // ...
        }

    }

    public static class C<T, U, S> extends FunctionalUnit<T, U> {

        private S s;

        public void process(T input) {
            // ...
        }

        @Inject
        public void inject(S object) {
            s = object;
        }

    }

    /**
     * Eine Functional-Unit mit mehreren Input-Pins und Output-Pins.
     */
    public static class File<T, S, U, V> {

        private final FunctionalUnit<?, U> errors = new FunctionalUnit<>();
        private final FunctionalUnit<?, V> dataLoaded = new FunctionalUnit<>();

        public void load(T input) {
            // ...
        }

        public void store(S input) {
            // ...
        }

        public void connectError(Consumer<U> c) {
            errors.connectOutputPinWith(c);
        }

        public void disconnectError(Consumer<U> c) {
            errors.disconnectOutputPinFrom(c);
        }

        private void publishError(U u) {
            errors.publishResult(u);
        }

        public void connectDataLoaded(Consumer<V> c) {
            dataLoaded.connectOutputPinWith(c);
        }

        public void disconnectDataLoaded(Consumer<V> c) {
            dataLoaded.disconnectOutputPinFrom(c);
        }

        private void publishDataLoaded(V v) {
            dataLoaded.publishResult(v);
        }

    }

    public static class SplitLineIntoWords extends FunctionalUnit<String, String> {

        public void process(String line) {
            for (String word : line.split(" "))
                publishResult(word);
        }

    }

    public static class SplitLineIntoWordsVariant extends FunctionalUnit<String, Iterable<String>> {

        public void process(String line) {
            publishResult(Arrays.asList(line.split(" ")));
        }

    }

}
