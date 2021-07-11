package com.iryna.datastructures.list;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    ArrayList arrayList;

    @Test
    void size() {

        arrayList = new ArrayList();
        arrayList.add("Elem 1");
        arrayList.add("Elem 2");
        arrayList.add("Elem 3");
        assertEquals(3, arrayList.size());
    }

    @Test
    void isEmpty() {
        arrayList = new ArrayList();
        assertTrue(arrayList.isEmpty());
    }

    @Test
    void contains() {
        arrayList = new ArrayList();
        arrayList.add("a");
        arrayList.add("c");
        arrayList.add("b");
        assertTrue(arrayList.contains("c"));
    }

    @Test
    void addElements() {
        arrayList = new ArrayList();
        arrayList.add("a");
        arrayList.add(12);
        arrayList.add('c');
        arrayList.add(true);
        arrayList.add(88);
        arrayList.add("l");
        assertEquals("a", arrayList.get(0));
        assertEquals(12, arrayList.get(1));
        assertEquals('c', arrayList.get(2));
        assertEquals(true, arrayList.get(3));
        assertEquals(88, arrayList.get(4));
        assertEquals("l", arrayList.get(5));
    }

    @Test
    void add() {

        arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(1, 9);

        assertTrue(Arrays.equals(new Object[]{1, 9, 8, 2, 3, 4}, arrayList.add(2, 8)));

    }

    @Test
    void remove() {
        arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.remove(2);
        assertFalse(arrayList.contains(2));
    }

    @Test
    void clear() {
        arrayList = new ArrayList();
        arrayList.add("t");
        arrayList.add("c");
        arrayList.clear();
        assertTrue(arrayList.isEmpty());
    }

    @Test
    void iterateEachElement() {

        arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        int ind = 0;

        for(Object element: arrayList) {
            assertEquals(arrayList.get(ind), element);
            ind++;
        }
    }

    @Test
    void removeElementWhileIterating() {

        arrayList = new ArrayList();
        arrayList.add("a");
        arrayList.add(2);
        arrayList.add("V");
        arrayList.add(0);
        arrayList.add(5);

        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
            if (iterator.next().equals("V")) {
                iterator.remove();
            }
        }
        assertFalse(arrayList.contains("V"));
    }

    @Test
    void getElementsFromEmptyCollection() {
        arrayList = new ArrayList();
        assertThrows(NoSuchElementException.class, () -> {
            arrayList.iterator().next();
        });
    }

    @Test
    void checkNextElementAtEmptyCollection() {
        arrayList = new ArrayList();
        assertFalse(arrayList.iterator().hasNext());
    }

    @Test
    void get() {
    }

    @Test
    void set() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void lastIndexOf() {
    }
}