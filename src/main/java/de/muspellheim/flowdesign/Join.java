package de.muspellheim.flowdesign;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class Join<T, U> {

    private final List<Consumer<Tuple<T, U>>> consumers = new CopyOnWriteArrayList<>();

    private T t;
    private U u;

    public void input1(T input) {
        t = input;
        tryJoin();
    }

    private synchronized void tryJoin() {
        if (t != null && u != null) {
            publishResult(new Tuple<>(t, u));
            t = null;
            u = null;
        }
    }

    public void input2(U input) {
        u = input;
        tryJoin();
    }

    public void connectOutput(Consumer<Tuple<T, U>> c) {
        consumers.add(c);
    }

    public void disconnectOutput(Consumer<Tuple<T, U>> c) {
        consumers.remove(c);
    }

    private void publishResult(Tuple<T, U> t) {
        consumers.forEach(c -> c.accept(t));
    }

}
