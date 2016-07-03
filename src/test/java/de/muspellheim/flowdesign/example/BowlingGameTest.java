/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign.example;

import de.muspellheim.flowdesign.example.BowlingGame.Frame;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

/**
 * Unit tests for the bowling game.
 *
 * @author Falko Schumann
 */
public class BowlingGameTest {

    @Test
    public void testCalcBasicStores() {
        List<Frame> frames = Arrays.asList(new Frame(1, 2), new Frame(3, 5));
        frames = BowlingGame.addPinsInFrame(frames);
        assertArrayEquals(new int[]{3, 8}, frames.stream().mapToInt(f -> f.score).toArray());
    }

}
