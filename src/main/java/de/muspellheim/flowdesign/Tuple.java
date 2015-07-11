/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * Ein Tuple ist eine Struktur aus zwei Elementen. Ein Tuple ist ein Wertobjekt.
 *
 * @param <T> der Typ des ersten ELements.
 * @param <U> der Typ des zweiten Elements.
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public class Tuple<T, U> {

    private final T first;
    private final U second;

    private Tuple(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Erzeugt ein neues Tuple.
     *
     * @param first  das erste Element des neuen Tuples.
     * @param second das zweite Element des neuen Tuples.
     * @param <T>    der Typ des ersten ELements.
     * @param <U>    der Typ des zweiten Elements.
     * @return ein Tuple mit den zwei Elementen.
     */
    public static <T, U> Tuple<T, U> of(T first, U second) {
        return new Tuple(first, second);
    }

    /**
     * Gibt das erste ELement des Tuples zurück.
     *
     * @return das erste Element.
     */
    public T getFirst() {
        return first;
    }

    /**
     * Gibt das zweite ELement des Tuples zurück.
     *
     * @return das zweite Element.
     */
    public U getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(first, tuple.first) &&
                Objects.equals(second, tuple.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return MessageFormat.format("({0}, {1})", first, second);
    }

}
