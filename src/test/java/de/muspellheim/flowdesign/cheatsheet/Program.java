package de.muspellheim.flowdesign.cheatsheet;

import de.muspellheim.flowdesign.Configure;
import de.muspellheim.flowdesign.EntryPoint;
import de.muspellheim.flowdesign.FunctionalUnit;

import javax.inject.Inject;
import java.util.function.Consumer;

/**
 * Implementation for the example program flow.
 *
 * <p><img src="doc-files/programm.png" alt="Flow Design of Program"><br>Flow Design of Program</p>
 */
public class Program {

    public static <T, S, U> void main(String[] args) {
        // (1) Build
        Gui<U, T> gui = new Gui<>();
        R r = new R();
        A<T, S> a = new A<>();
        B<S, U> b = new B<>();
        X<T, U> x = new X<>(a, b);

        // (2) Bind
        gui.connectWithQuery(x::process);
        x.connectWithResult(gui::display);

        // (3) Inject
        b.inject(r);

        // (4) Configure
        a.configure(args);

        // (5) Run
        gui.run(args);
    }

    private static class Gui<U, T> implements EntryPoint {

        private FunctionalUnit<U, T> delegate = new FunctionalUnit<>();

        @Override
        public void run(String[] args) {
            // ...
        }

        public void display(U u) {
            // ...
        }

        public void connectWithQuery(Consumer<T> inputPin) {
            delegate.connectWithResult(inputPin);
        }

        public void disconnectFromQuery(Consumer<T> inputPin) {
            delegate.disconnectFromResult(inputPin);
        }

    }

    private static class R {

    }

    private static class A<T, S> extends FunctionalUnit<T, S> {

        @Override
        public void process(T input) {
            // ...
        }

        @Configure
        public void configure(String[] args) {
            // ...
        }

    }

    private static class B<S, U> extends FunctionalUnit<S, U> {

        @Override
        public void process(S input) {
            // ...
        }

        @Inject
        public void inject(R object) {
            // ...
        }

    }

    private static class X<T, U> extends FunctionalUnit<T, U> {

        private Consumer<T> process;

        public <S> X(A<T, S> a, B<S, U> b) {
            process = a::process;
            a.connectWithResult(b::process);
            b.connectWithResult(this::publishResult);
        }

        public void process(T input) {
            process.accept(input);
        }

    }


}
