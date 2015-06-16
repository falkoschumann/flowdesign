/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */
package de.muspellheim.flowdesign.bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    // TODO Code vereinfachen !!

    public static int calculateTotal(int... rolls) {
        List<Frame> frames = toFrames(rolls);
        frames = enrichFramesWithScore(frames);
        return calcTotal(frames);
    }

    static List<Frame> toFrames(int... rolls) {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < rolls.length; i++) {
            int roll1 = rolls[i];

            if (frames.size() < 9) {
                if (rolls[i] == 10) {
                    frames.add(new Frame(roll1));
                } else {
                    i++;
                    int roll2 = rolls[i];
                    frames.add(new Frame(roll1, roll2));
                }
            } else {
                if (rolls[i] + rolls[i + 1] < 10) {
                    i++;
                    int roll2 = rolls[i];
                    frames.add(new Frame(roll1, roll2));
                } else {
                    i++;
                    int roll2 = rolls[i];
                    i++;
                    int roll3 = rolls[i];
                    frames.add(new Frame(roll1, roll2, roll3));
                }
            }
        }
        return frames;
    }

    static List<Frame> enrichFramesWithScore(List<Frame> frames) {
        frames = addPinsInFrame(frames);
        frames = addBonusForSpares(frames);
        frames = addBonusForStrikes(frames);
        return frames;
    }

    static List<Frame> addPinsInFrame(List<Frame> frames) {
        frames.forEach(f -> f.setScore(f.getRolls()[0] + f.getRolls()[1]));
        return frames;
    }

    static List<Frame> addBonusForSpares(List<Frame> frames) {
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            if (frame.getRolls()[0] > 0 && frame.getRolls()[1] > 0 && frame.getRolls()[0] + frame.getRolls()[1] == 10) {
                int score = frame.getScore();
                if (i < 9) {
                    score += frames.get(i + 1).getRolls()[0];
                } else {
                    score += frame.getRolls()[2];
                }
                frame.setScore(score);
            }
        }
        return frames;
    }

    static List<Frame> addBonusForStrikes(List<Frame> frames) {
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            if (frame.getRolls()[0] == 10) {
                int score = frame.getScore();
                if (i < 9) {
                    score += frames.get(i + 1).getRolls()[0];
                    if (frames.get(i + 1).getRolls()[0] < 10) {
                        score += frames.get(i + 1).getRolls()[1];
                    } else if (i < 8) {
                        score += frames.get(i + 2).getRolls()[0];
                    }
                } else {
                    score += frame.getRolls()[1];
                    score += frame.getRolls()[2];
                }
                frame.setScore(score);
            }
        }
        return frames;
    }

    static int calcTotal(List<Frame> frames) {
        return frames.stream().mapToInt(Frame::getScore).sum();
    }

}
