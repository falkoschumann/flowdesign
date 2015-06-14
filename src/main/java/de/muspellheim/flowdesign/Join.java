package de.muspellheim.flowdesign;

import java.util.function.Consumer;

public class Join<T, U> {

    private final FunctionalUnitSupport<?, Tuple<T, U>> consumers = new FunctionalUnitSupport<>();

    private T t;
    private U u;

    public void input1(T input) {
        t = input;
        tryJoin();
    }

    private synchronized void tryJoin() {
        if (t != null && u != null) {
            consumers.publishResult(new Tuple<>(t, u));
            t = null;
            u = null;
        }
    }

    public void input2(U input) {
        u = input;
        tryJoin();
    }

    public void connectOutput(Consumer<Tuple<T, U>> c) {
        consumers.connectResult(c);
    }

    public void disconnectOutput(Consumer<Tuple<T, U>> c) {
        consumers.disconnectResult(c);
    }

}
