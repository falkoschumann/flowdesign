/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit-Tests für Map.
 *
 * @author Falko Schumann &lt;falko.schumann@muspellheim.de&gt;
 */
public class MapTest {

    private Map<String, Integer> map;
    private Integer result;

    @Before
    public void setUp() {
        map = new Map<>(Integer::parseInt);
        map.result().connect(this::setResult);
    }

    private void setResult(Integer result) {
        this.result = result;
    }

    @Test
    public void testConvert() {
        map.process("42");

        assertEquals(42, (int) result);
    }

}