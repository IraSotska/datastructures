package com.iryna.datastructures.list;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    private ArrayList arrayList;

    @Test
    void arraySizeTest() {

        arrayList = new ArrayList();
        arrayList.add("Elem 1");
        arrayList.add("Elem 2");
        arrayList.add("Elem 3");
        assertEquals(3, arrayList.size());
    }

    @Test
    void isEmptyTest() {
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
    void addElementsTest() {
        arrayList = new ArrayList();
        arrayList.add("a");
        arrayList.add(12);
        arrayList.add('c');
        arrayList.add(true);
        assertEquals("a", arrayList.get(0));
        assertEquals(12, arrayList.get(1));
        assertEquals('c', arrayList.get(2));
        assertEquals(true, arrayList.get(3));
    }

    @Test
    void removeElementTest() {
        arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(8);
        arrayList.add("A");
        arrayList.remove(2);
        assertFalse(arrayList.contains(2));
        arrayList.remove("A");
        assertFalse(arrayList.contains("A"));
    }

    @Test
    void clearArrayTest() {
        arrayList = new ArrayList();
        arrayList.add("t");
        arrayList.add("c");
        arrayList.clear();
        assertTrue(arrayList.isEmpty());
    }

    @Test
    void iterateEachElementTest() {

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
    void removeElementWhileIteratingTest() {

        arrayList = new ArrayList();
        arrayList.add(8);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(0);

        Iterator iterator = arrayList.iterator();

        Object next = iterator.next();
        while (iterator.hasNext()) {
            if (next.equals(8)) {
                iterator.remove();
            }
            next = iterator.next();
        }
        assertFalse(arrayList.contains(8));
    }

    @Test
    void getElementsFromEmptyCollectionTest() {
        arrayList = new ArrayList();
        assertThrows(NoSuchElementException.class, () -> {
            arrayList.iterator().next();
        });
    }

    @Test
    void checkNextElementAtEmptyCollectionTest() {
        arrayList = new ArrayList();
        assertFalse(arrayList.iterator().hasNext());
    }

    @Test
    void getElementFromListTest() {
        arrayList = new ArrayList();
        arrayList.add("097");
        arrayList.add(true);
        arrayList.add('h');
        assertEquals("097", arrayList.get(0));
        assertEquals(true, arrayList.get(1));
        assertEquals('h', arrayList.get(2));
    }

    @Test
    void setElementAtListTest() {
        arrayList = new ArrayList();
        arrayList.add("123");
        arrayList.add(123);
        arrayList.set(1, 1234);
        assertEquals(1234, arrayList.get(1));
    }

    @Test
    void indexOfElementTest() {
        arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add(1);
        arrayList.add('1');
        assertEquals(2, arrayList.indexOf('1'));
    }

    @Test
    void lastIndexOfTest() {
        arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add("1");
        arrayList.add(1);
        arrayList.add(1);
        assertEquals(3, arrayList.lastIndexOf(1));
    }

    @Test
    void addElementForIndex() {
        arrayList = new ArrayList();
        arrayList.add(0);
        arrayList.add(2);
        arrayList.add(4);
        arrayList.add(8);
        arrayList.add(3, 6);
        assertEquals(6, arrayList.get(3));
        assertEquals(5, arrayList.size());
    }

    @Test
    void addElementForIndexIndexOutOfRangeException() {
        arrayList = new ArrayList();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            arrayList.add(2, 1);
        });
    }
}