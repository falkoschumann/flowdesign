/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit-Tests für Split.
 *
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public class SplitTest {

    private Split<Integer, Integer> split;
    private Integer output1;
    private Integer output2;

    @Before
    public void setUp() {
        split = new Split<>();
        split.output1().connect(this::setOutput1);
        split.output2().connect(this::setOutput2);
    }

    private void setOutput1(Integer output1) {
        this.output1 = output1;
    }

    private void setOutput2(Integer output2) {
        this.output2 = output2;
    }

    @Test
    public void testSingleSplit() {
        split.input(Tuple.of(4, 2));

        assertEquals(4, (int) output1);
        assertEquals(2, (int) output2);
    }

    @Test
    public void testMultipleSplit() {
        split.input(Tuple.of(4, 2));

        assertEquals(4, (int) output1);
        assertEquals(2, (int) output2);

        split.input(Tuple.of(3, 1));

        assertEquals(3, (int) output1);
        assertEquals(1, (int) output2);
    }


}
