/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */
package de.muspellheim.flowdesign.bowling;

import java.util.Arrays;

public class Frame {

    public static final Frame STRIKE = new Frame(10);

    private final int[] rolls = new int[3];
    private int score;

    public Frame(int roll1) {
        this(roll1, 0, 0);
    }

    public Frame(int roll1, int roll2) {
        this(roll1, roll2, 0);
    }

    public Frame(int roll1, int roll2, int roll3) {
        rolls[0] = roll1;
        rolls[1] = roll2;
        rolls[2] = roll3;
    }

    public int[] getRolls() {
        return rolls;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "(" + getRolls()[0] + ", " + getRolls()[1] + ")/" + getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Arrays.equals(rolls, frame.rolls);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(rolls);
    }

}
