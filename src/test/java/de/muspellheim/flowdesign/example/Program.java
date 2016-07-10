/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign.example;

import de.muspellheim.flowdesign.*;

/**
 * A complete example flow application.
 *
 * @author Falko Schumann
 */
public class Program<T, S, U> extends Flow {

    private Gui<U, T> gui;
    private R r;
    private A<T, S> a;
    private B<S, U> b;
    private X<T, S, U> x;

    public static void main(String[] args) {
        new Program().initializeAndStart(args);
    }

    @Override
    protected void build() {
        gui = new Gui<>();
        r = new R();

        a = new A<>();
        b = new B<>();
        x = new X<>(a, b);
    }

    @Override
    protected void bind() {
        gui.query().connect(x::process);
        x.result().connect(gui::display);
    }

    @Override
    protected void inject() {
        b.inject(r);
    }

    @Override
    protected void configure(String[] args) {
        a.configure(args);
    }

    @Override
    protected void run(String[] args) {
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

    private static class A<T, S> extends BaseFunctionalUnit<T, S> implements Configurable {

        @Override
        public void process(T input) {
            // ...
        }

        @Override
        public void configure(String[] args) {
            // ...
        }

    }

    private static class B<S, U> extends BaseFunctionalUnit<S, U> implements DependsOn<R> {

        @Override
        public void process(S input) {
            // ...
        }

        @Override
        public void inject(R object) {
            // ...
        }

    }

    private static class X<T, S, U> extends BaseFunctionalUnit<T, U> {

        private final InputPin<T> process;

        X(A<T, S> a, B<S, U> b) {
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
