/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */
package de.muspellheim.flowdesign.bowling;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class BowlingGameTest {

    @Test
    public void testCalcBasicScore() {
        List<Frame> frames = Arrays.asList(new Frame(1, 2), new Frame(3, 5));
        frames = BowlingGame.addPinsInFrame(frames);
        assertArrayEquals(new int[]{3, 8}, frames.stream().mapToInt(Frame::getScore).toArray());
    }

    @Test
    public void testToFrames() {
        Collection<Frame> frames = BowlingGame.toFrames(1, 2, 3, 4);
        assertEquals(Arrays.asList(new Frame(1, 2), new Frame(3, 4)), frames);
    }

    @Test
    public void testToFrames_Spare() {
        Collection<Frame> frames = BowlingGame.toFrames(3, 7, 5, 5);
        assertEquals(Arrays.asList(new Frame(3, 7), new Frame(5, 5)), frames);
    }

    @Test
    public void testToFrames_Strike() {
        Collection<Frame> frames = BowlingGame.toFrames(10, 1, 2);
        assertEquals(Arrays.asList(Frame.STRIKE, new Frame(1, 2)), frames);
    }

    @Test
    public void testCalcTotal() {
        Frame frame1 = new Frame(0);
        frame1.setScore(4);
        Frame frame2 = new Frame(0);
        frame2.setScore(5);
        Frame frame3 = new Frame(0);
        frame3.setScore(6);

        int total = BowlingGame.calcTotal(Arrays.asList(frame1, frame2, frame3));
        assertEquals(15, total);
    }

    @Test
    public void testAddBonusForSpares() {
        Frame frame1 = new Frame(4, 6);
        frame1.setScore(10);
        Frame frame2 = new Frame(3, 4);
        frame2.setScore(7);
        List<Frame> frames = new ArrayList<>();
        frames.add(frame1);
        frames.add(frame2);

        frames = BowlingGame.addBonusForSpares(frames);
        assertEquals(2, frames.size());

        assertSame(frame1, frames.get(0));
        assertEquals(13, frame1.getScore());

        assertSame(frame2, frames.get(1));
        assertEquals(7, frame2.getScore());
    }

    @Test
    public void testAddBonusForStrikes() {
        Frame frame1 = new Frame(10);
        frame1.setScore(10);
        Frame frame2 = new Frame(3, 4);
        frame2.setScore(7);
        List<Frame> frames = new ArrayList<>();
        frames.add(frame1);
        frames.add(frame2);

        frames = BowlingGame.addBonusForStrikes(frames);
        assertEquals(2, frames.size());

        assertSame(frame1, frames.get(0));
        assertEquals(17, frame1.getScore());

        assertSame(frame2, frames.get(1));
        assertEquals(7, frame2.getScore());
    }

}
