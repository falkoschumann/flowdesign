package de.muspellheim.flowdesign;

import java.util.Objects;

public class Tuple<T, U> {

    public final T t;
    public final U u;

    public Tuple(T t, U u) {
        this.t = t;
        this.u = u;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(t, tuple.t) && Objects.equals(u, tuple.u);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t, u);
    }

}
