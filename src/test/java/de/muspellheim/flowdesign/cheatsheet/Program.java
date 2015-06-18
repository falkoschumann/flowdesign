package de.muspellheim.flowdesign.cheatsheet;

import de.muspellheim.flowdesign.Configure;
import de.muspellheim.flowdesign.EntryPoint;
import de.muspellheim.flowdesign.FunctionalUnit;

import javax.inject.Inject;
import java.util.function.Consumer;

public class Program {

    public static <T, S, U> void main(String[] args) {
        // (1) Build
        Gui<U, T> gui = new Gui<>();
        R r = new R();
        A<T, S> a = new A<>();
        B<S, U> b = new B<>();
        X<T, U> x = new X<>(a, b);

        // (2) Bind
        gui.connectQueryPinWith(x::process);
        x.connectOutputPinWith(gui::display);

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

        public void connectQueryPinWith(Consumer<T> inputPin) {
            delegate.connectOutputPinWith(inputPin);
        }

        public void disconnectQueryPinFrom(Consumer<T> inputPin) {
            delegate.disconnectOutputPinFrom(inputPin);
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
            a.connectOutputPinWith(b::process);
            b.connectOutputPinWith(this::publishResult);
        }

        public void process(T input) {
            process.accept(input);
        }

    }


}
