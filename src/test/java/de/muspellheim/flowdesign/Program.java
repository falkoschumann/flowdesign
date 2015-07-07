/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Ein komplettes Beispielprogramm.
 *
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public class Program<T, S, U> {

    private String[] args;
    private Gui<U, T> gui;
    private R r;
    private A<T, S> a;
    private B<S, U> b;
    private X<T, U> x;

    public Program(String[] args) {
        this.args = args;
    }

    public static void main(String[] args) {
        new Program(args).start();
    }

    public void start() {
        build();
        bind();
        inject();
        configure();
        run();
    }

    private void build() {
        gui = new Gui<>();
        r = new R();

        a = new A<>();
        b = new B<>();
        x = new X<>(a, b);
    }

    private void bind() {
        gui.query().connect(x::process);
        x.result().connect(gui::display);
    }

    private void inject() {
        b.inject(r);
    }

    private void configure() {
        a.configure(args);
    }

    private void run() {
        gui.run(args);
    }

    private static class Gui<U, T> implements EntryPoint {

        private OutputPin<T> query = new OutputPin<>();

        public void display(U input) {
            // ...
        }

        public OutputPin<T> query() {
            return query;
        }

        @Override
        public void run(String[] args) {
            // ...
        }

    }

    private static class A<T, S> extends FunctionaUnit<T, S> implements Configurable {

        @Override
        public void process(T input) {
            // ...
        }

        @Override
        public void configure(String[] args) {
            // ...
        }

    }

    private static class B<S, U> extends FunctionaUnit<S, U> implements DependsOn<R> {

        @Override
        public void process(S input) {
            // ...
        }

        @Override
        public void inject(R object) {
            // ...
        }

    }

    private static class X<T, U> extends FunctionaUnit<T, U> {

        private final InputPin<T> process;

        public X(A<T, ?> a, B<?, U> b) {
            process = a::process;
            a.result().connect(b::process);
            b.result().connect(this.result()::publish);
        }

        @Override
        public void process(T input) {
            process.accept(input);
        }

    }

    private static class R {

        // ...

    }

}
