package de.muspellheim.flowdesign;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * Implementierung des Cheat-Sheets zu Flow-Design von XYZ.
 * <p>
 * <a href="http://geekswithblogs.net/theArchitectsNapkin/archive/2011/03/20/flow-design-cheat-sheet-ndash-part-ii-translation.aspx">[Quelle]</a>
 */
public class CheatSheet {

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

        d(new Tuple<>(s, v));
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
        a.connectResult(b::process);
    }

    public void wiringSplit() {
        A a = new A();
        B b = new B();
        C c = new C();

        a.connectResult(b::process);
        a.connectResult(c::process);
    }

    public <T, U> void wiringJoin() {
        Join<T, U> j = new Join<>();
        A<Void, T> a = new A();
        B<Void, U> b = new B();
        C<Tuple<T, U>, Void, Void> c = new C();

        a.connectResult(j::input1);
        b.connectResult(j::input2);

        j.connectOutput(c::process);
    }

    /**
     * Eine Functional-Unit als Klasse. Diese FU ist ein Part.
     *
     * @param <T> Typ des Inputs.
     * @param <U> Typ des Outputs.
     */
    public static class A<T, U> {

        private final List<Consumer<U>> consumers = new CopyOnWriteArrayList<>();

        public void process(T input) {
            // ...
        }

        public void connectResult(Consumer<U> c) {
            consumers.add(c);
        }

        public void disconnectResult(Consumer<U> c) {
            consumers.remove(c);
        }

        private void publishResult(U u) {
            consumers.forEach(c -> c.accept(u));
        }

    }

    /**
     * Eine Functional-Unit als Klasse. Diese FU ist ein Board.
     *
     * @param <T> Typ des Inputs.
     * @param <U> Typ des Outputs.
     */
    public static class X<T, S, U> {

        private final List<Consumer<U>> consumers = new CopyOnWriteArrayList<>();

        private Consumer<T> process;

        public X(A<T, S> a, B<S, U> b) {
            process = a::process;
            a.connectResult(b::process);
            b.connectResult(this::publishResult);
        }

        public void process(T input) {
            process.accept(input);
        }

        public void connectResult(Consumer<U> c) {
            consumers.add(c);
        }

        public void disconnectResult(Consumer<U> c) {
            consumers.remove(c);
        }

        private void publishResult(U u) {
            consumers.forEach(c -> c.accept(u));
        }

    }

    public static class B<S, U> {

        private final List<Consumer<U>> consumers = new CopyOnWriteArrayList<>();

        public void process(S input) {
            // ...
        }

        public void connectResult(Consumer<U> c) {
            consumers.add(c);
        }

        public void disconnectResult(Consumer<U> c) {
            consumers.remove(c);
        }

        private void publishResult(U u) {
            consumers.forEach(c -> c.accept(u));
        }

    }

    public static class C<T, U, S> implements DependsOn<S> {

        private final List<Consumer<U>> consumers = new CopyOnWriteArrayList<>();

        private S s;

        public void process(T input) {
            // ...
        }

        public void connectResult(Consumer<U> c) {
            consumers.add(c);
        }

        public void disconnectResult(Consumer<U> c) {
            consumers.remove(c);
        }

        private void publishResult(U u) {
            consumers.forEach(c -> c.accept(u));
        }

        @Override
        public void inject(S object) {
            s  = object;
        }

    }

    /**
     * Eine Functional-Unit mit mehreren Input-Pins und Output-Pins.
     */
    public static class File<T, S, U, V> {

        private final List<Consumer<U>> errorConsumers = new CopyOnWriteArrayList<>();
        private final List<Consumer<V>> dataLoadedConsumers = new CopyOnWriteArrayList<>();

        public void load(T input) {
            // ...
        }

        public void store(S input) {
            // ...
        }

        public void connectError(Consumer<U> c) {
            errorConsumers.add(c);
        }

        public void disconnectError(Consumer<U> c) {
            errorConsumers.remove(c);
        }

        private void publishError(U u) {
            errorConsumers.forEach(c -> c.accept(u));
        }

        public void connectDataLoaded(Consumer<V> c) {
            dataLoadedConsumers.add(c);
        }

        public void disconnectDataLoaded(Consumer<V> c) {
            dataLoadedConsumers.remove(c);
        }

        private void publishDataLoaded(V u) {
            dataLoadedConsumers.forEach(c -> c.accept(u));
        }

    }

    public static class SplitLineIntoWords {

        private final List<Consumer<String>> consumers = new CopyOnWriteArrayList<>();

        public void process(String line) {
            for (String word : line.split(" "))
                publishResult(word);
        }

        public void connectResult(Consumer<String> c) {
            consumers.add(c);
        }

        public void disconnectResult(Consumer<String> c) {
            consumers.remove(c);
        }

        private void publishResult(String s) {
            consumers.forEach(c -> c.accept(s));
        }

    }

    public static class SplitLineIntoWordsVariant {

        private final List<Consumer<Iterable<String>>> consumers = new CopyOnWriteArrayList<>();

        public void process(String line) {
            publishResult(Arrays.asList(line.split(" ")));
        }

        public void connectResult(Consumer<Iterable<String>> c) {
            consumers.add(c);
        }

        public void disconnectResult(Consumer<Iterable<String>> c) {
            consumers.remove(c);
        }

        private void publishResult(Iterable<String> s) {
            consumers.forEach(c -> c.accept(s));
        }

    }

}
