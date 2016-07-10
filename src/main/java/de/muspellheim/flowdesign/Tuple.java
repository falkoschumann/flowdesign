/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * A tuple hold two data objects as one data object.
 *
 * @param <T> the type of the first element.
 * @param <U> the type of the second element.
 * @author Falko Schumann
 * @since 3.0
 */
public final class Tuple<T, U> {

    private final T first;
    private final U second;

    private Tuple(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Creates a new tuple of two given data objects.
     *
     * @param first  the tuples first element.
     * @param second the tuples second element.
     * @param <T>    the type of the first element.
     * @param <U>    the type of the second element.
     * @return a new tuple with the two elements.
     */
    public static <T, U> Tuple<T, U> of(T first, U second) {
        return new Tuple<>(first, second);
    }

    /**
     * Returns the first element.
     *
     * @return the first element.
     */
    public T getFirst() {
        return first;
    }

    /**
     * Return the second element.
     *
     * @return the second element.
     */
    public U getSecond() {
        return second;
    }

    /**
     * Compares the two elements with {@link Object#equals(Object)}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(first, tuple.first) &&
                Objects.equals(second, tuple.second);
    }

    /**
     * Hashes the two elements.
     */
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    /**
     * Returns a human readable representation of this tuple.
     */
    @Override
    public String toString() {
        return MessageFormat.format("({0}, {1})", first, second);
    }

}
