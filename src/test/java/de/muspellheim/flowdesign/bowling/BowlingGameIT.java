/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */
package de.muspellheim.flowdesign.bowling;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BowlingGameIT {

    @Test
    public void testGutterGame() {
        int total = BowlingGame.calculateTotal(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(0, total);
    }

    @Test
    public void testAllFours() {
        int total = BowlingGame.calculateTotal(4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
        assertEquals(80, total);
    }

    @Test
    public void testPerfectGame() {
        int total = BowlingGame.calculateTotal(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        assertEquals(300, total);
    }

    @Test
    public void testAllSpares() {
        int total = BowlingGame.calculateTotal(1, 9, 2, 8, 3, 7, 4, 6, 5, 5, 5, 5, 6, 4, 7, 3, 8, 2, 9, 1, 5);
        assertEquals(154, total);
    }

    @Test
    public void testJustOneMoreRollAfterSpareIn10thFrame() {
        int total = BowlingGame.calculateTotal(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 9, 5);
        assertEquals(15, total);
    }

    @Test
    public void testTwoMoreRollsAfterStrikeIn10thFrame() {
        int total = BowlingGame.calculateTotal(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 3, 4);
        assertEquals(20, total);
    }

    @Test
    public void testAcceptanceTestCaseFromBlogEntry() {
        int total = BowlingGame.calculateTotal(3, 4, 5, 1, 10, 4, 5, 2, 6, 7, 3, 5, 4, 10, 10, 3, 7, 5);
        assertEquals(131, total);
    }

}
