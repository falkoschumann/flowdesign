package de.muspellheim.flowdesign;

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

        public void addResultPin(Consumer<U> c) {
            consumers.add(c);
        }

        public void removeResultPin(Consumer<U> c) {
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
            a.addResultPin(b::process);
            b.addResultPin(this::publishResult);
        }

        public void process(T input) {
            process.accept(input);
        }

        public void addResultPin(Consumer<U> c) {
            consumers.add(c);
        }

        public void removeResultPin(Consumer<U> c) {
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

        public void addResultPin(Consumer<U> c) {
            consumers.add(c);
        }

        public void removeResultPin(Consumer<U> c) {
            consumers.remove(c);
        }

        private void publishResult(U u) {
            consumers.forEach(c -> c.accept(u));
        }

    }

}
