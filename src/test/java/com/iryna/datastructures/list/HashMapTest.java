package com.iryna.datastructures.list;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    private HashMap<Integer, String> hashMap = new HashMap<>();

    @Test
    void put() {
        assertEquals("Val", hashMap.put(1, "Val"));
        assertEquals("Val2", hashMap.put(3, "Val2"));
        assertEquals("Val3", hashMap.put(4, "Val3"));
        assertEquals("Val4", hashMap.put(5, "Val4"));
        assertEquals("Val5", hashMap.put(6, "Val5"));
        assertEquals(5, hashMap.size());
    }

    @Test
    void get() {
        hashMap.put(1, "v1");
        hashMap.put(2, "v2");
        hashMap.put(3, "v3");
        hashMap.put(4, "v5");

        assertEquals("v1", hashMap.get(1));
    }

    @Test
    void containsKey() {
        hashMap.put(1, "v1");
        assertTrue(hashMap.containsKey(1));
        assertFalse(hashMap.containsKey(2));
    }

    @Test
    void remove() {
        hashMap.put(1, "Val");
        assertEquals("Val", hashMap.remove(1));
        assertFalse(hashMap.containsKey(1));
    }

    @Test
    void size() {
        assertEquals(0, hashMap.size());
    }

    @Test
    void iteratorTest() {

        ArrayList<String> elements = new ArrayList();

        elements.add(hashMap.put(4, "Val4"));
        elements.add(hashMap.put(5, "Val5"));
        elements.add(hashMap.put(6, "Val6"));
        elements.add(hashMap.put(7, "Val5"));
        elements.add(hashMap.put(8, "Val6"));

        Iterator iterator = hashMap.iterator();

        Object next;
        while (iterator.hasNext()) {
            next = iterator.next();
        }
    }
}