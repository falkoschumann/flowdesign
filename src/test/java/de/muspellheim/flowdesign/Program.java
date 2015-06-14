package de.muspellheim.flowdesign;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
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
        gui.connectQuery(x::process);
        x.connectResult(gui::display);

        // (3) Inject
        b.inject(r);

        // (4) Configure
        a.configure(args);

        // (5) Run
        gui.run(args);
    }

    private static class Gui<U, T> implements EntryPoint {

        private List<Consumer<T>> queryConsumer = new CopyOnWriteArrayList<>();

        @Override
        public void run(String[] args) {
            // ...
        }

        public void display(U u) {
            // ...
        }

        public void connectQuery(Consumer<T> c) {
            queryConsumer.add(c);
        }

        public void disconnectQuery(Consumer<T> c) {
            queryConsumer.remove(c);
        }

    }

    private static class R {

    }

    private static class A<T, S> extends Fu<T, S> implements Configurable {

        @Override
        public void process(T t) {
            // ...
        }

        @Override
        public void configure(String[] args) {
            // ...
        }

    }

    private static class B<S, U> extends Fu<S, U> implements DependsOn<R> {

        @Override
        public void process(S s) {
            // ...
        }

        @Override
        public void inject(R object) {
            // ...
        }

    }

    private static class X<T, U> extends Fu<T, U> {

        private List<Consumer<U>> consumers = new CopyOnWriteArrayList<>();

        private Consumer<T> process;

        public <S> X(A<T, S> a, B<S, U> b) {
            process = a::process;
            a.connectResult(b::process);
            b.connectResult(this::publishResult);
        }

        public void process(T t) {
            process.accept(t);
        }

    }

    private static abstract class Fu<T, U> {

        private List<Consumer<U>> consumers = new CopyOnWriteArrayList<>();

        public abstract void process(T t);

        public void connectResult(Consumer<U> c) {
            consumers.add(c);
        }

        public void disconnectResult(Consumer<U> c) {
            consumers.remove(c);
        }

        protected void publishResult(U r) {
            consumers.forEach(c -> c.accept(r));
        }

    }


}