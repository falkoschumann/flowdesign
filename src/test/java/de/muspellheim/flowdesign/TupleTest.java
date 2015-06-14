package de.muspellheim.flowdesign;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TupleTest {

    @Test
    public void testToString() {
        Tuple<String, Integer> foo = new Tuple<>("Foo", 42);

        assertEquals("(Foo, 42)", foo.toString());
    }

    @Test
    public void testEquality() {
        Tuple<String, Integer> foo1 = new Tuple<>("Foo", 42);
        Tuple<String, Integer> foo2 = new Tuple<>("Foo", 42);
        Tuple<String, Integer> foo3 = new Tuple<>("Foo", 42);
        Tuple<String, Integer> bar1 = new Tuple<>("Bar", 42);

        assertEquals(foo1, foo1);
        assertEquals(foo1, foo2);
        assertEquals(foo2, foo1);
        assertEquals(foo2, foo3);

        assertNotEquals(foo1, bar1);
    }

    @Test
    public void testHashCode() {
        Tuple<String, Integer> foo1 = new Tuple<>("Foo", 42);
        Tuple<String, Integer> foo2 = new Tuple<>("Foo", 42);

        int hash1 = foo1.hashCode();
        int hash2 = foo2.hashCode();

        assertEquals(hash1, foo1.hashCode());
        assertEquals(hash1, hash2);
    }

}
