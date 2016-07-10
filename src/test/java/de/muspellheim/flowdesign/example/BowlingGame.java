/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Implements the bowling kata with flow design.
 *
 * @author Falko Schumann
 */
public final class BowlingGame {

    private BowlingGame() {
        // utility class
    }

    public static int calculateTotal(int[] rolls) {
        List<Frame> frames = toFrames(rolls);
        frames = enrichFramesWithScore(frames);
        int total = calcTotal(frames);
        return total;
    }

    private static List<Frame> toFrames(int[] rolls) {
        List<Frame> frames = new ArrayList<>();
        int rollIndex = 0;
        for (int frameIndex = 0; frameIndex < 10; frameIndex++) {
            if (frameIndex < 9) {
                if (rolls[rollIndex] == 10) {
                    frames.add(new Frame(rolls[rollIndex]));
                    rollIndex += 1;
                } else {
                    frames.add(new Frame(Arrays.copyOfRange(rolls, rollIndex, rollIndex + 2)));
                    rollIndex += 2;
                }
            } else {
                frames.add(new Frame(Arrays.copyOfRange(rolls, rollIndex, rolls.length)));
            }
        }
        return frames;
    }

    private static List<Frame> enrichFramesWithScore(List<Frame> frames) {
        frames = addPinsInFrame(frames);
        frames = addBonusForSpare(frames);
        frames = addBonusForStrike(frames);
        return frames;
    }

    private static int calcTotal(List<Frame> frames) {
        return frames.stream().mapToInt(f -> f.score).sum();
    }

    static List<Frame> addPinsInFrame(List<Frame> frame) {
        frame.forEach(f -> f.score = IntStream.of(f.rolls).sum());
        return frame;
    }

    private static List<Frame> addBonusForSpare(List<Frame> frames) {
        for (int frameIndex = 0; frameIndex < frames.size(); frameIndex++) {
            Frame frame = frames.get(frameIndex);
            if (frame.isSpare() && frameIndex < 9) {
                Frame nextFrame = frames.get(frameIndex + 1);
                frame.score += nextFrame.rolls[0];
            }
        }
        return frames;
    }

    private static List<Frame> addBonusForStrike(List<Frame> frames) {
        for (int frameIndex = 0; frameIndex < frames.size(); frameIndex++) {
            Frame frame = frames.get(frameIndex);
            if (frame.isStrike()) {
                if (frameIndex < 9) {
                    Frame nextFrame = frames.get(frameIndex + 1);
                    if (nextFrame.isStrike() && frameIndex + 1 < 9) {
                        Frame nextNextFrame = frames.get(frameIndex + 2);
                        frame.score += nextFrame.rolls[0] + nextNextFrame.rolls[0];
                    } else {
                        frame.score += nextFrame.rolls[0] + nextFrame.rolls[1];
                    }
                }
            }
        }
        return frames;
    }

    /**
     * A frame with one, two or three rolls.
     */
    public static class Frame {

        public int[] rolls = new int[2];
        public int score;

        public Frame(int... rolls) {
            this.rolls = rolls;
        }

        public boolean isSpare() {
            return rolls.length >= 2 && rolls[0] + rolls[1] == 10;
        }

        public boolean isStrike() {
            return rolls.length >= 1 && rolls[0] == 10;
        }

    }

}
