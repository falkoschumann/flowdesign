/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Acceptance tests for the bowling game.
 *
 * @author Falko Schumann
 */
@RunWith(Parameterized.class)
public class BowlingGameAcceptanceTest {

    @Parameter(0)
    public IntStream rolls;

    @Parameter(1)
    public int expectedTotal;

    @Parameter(2)
    public String testdescription;


    @Parameters(name = "{index}: {2}")
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {IntStream.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 0, "gutter game"},
                {IntStream.of(4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4), 80, "all fours"},
                {IntStream.of(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10), 300, "all strikes"},
                {IntStream.of(1, 9, 2, 8, 3, 7, 4, 6, 5, 5, 5, 5, 6, 4, 7, 3, 8, 2, 9, 1, 5), 154, "all spares"},
                {IntStream.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 9, 5), 15, "focus: just one more roll after spare in 10th frame"},
                {IntStream.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 3, 4), 17, "focus: two more rolls after strike in 10th frame"},
                {IntStream.of(3, 4, 5, 1, 10, 4, 5, 2, 6, 7, 3, 5, 4, 10, 10, 3, 7, 5), 131, "acceptance test case from blog entry"}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testGame() {
        int total = BowlingGame.calculateTotal(rolls.toArray());
        assertEquals(expectedTotal, total);
    }

}
