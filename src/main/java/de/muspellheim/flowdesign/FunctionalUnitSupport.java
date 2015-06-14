package de.muspellheim.flowdesign;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class FunctionalUnitSupport<T, U> {

    private final List<Consumer<U>> consumers = new CopyOnWriteArrayList<>();
    private final Consumer<T> process;

    public FunctionalUnitSupport() {
        this(FunctionalUnitSupport::doNothing);
    }

    public FunctionalUnitSupport(Consumer<T> process) {
        this.process = process;
    }

    private static void doNothing(Object o) {
        // do nothing
    }

    public void process(T t) {
        process.accept(t);
    }

    public void connectResult(Consumer<U> c) {
        Objects.requireNonNull(c);
        consumers.add(c);
    }

    public void disconnectResult(Consumer<U> c) {
        Objects.requireNonNull(c);
        consumers.remove(c);
    }

    public void publishResult(U r) {
        consumers.forEach(c -> c.accept(r));
    }

}
