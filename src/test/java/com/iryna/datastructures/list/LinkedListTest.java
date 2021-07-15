package com.iryna.datastructures.list;

import  org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    private LinkedList linkedList;

    @Test
    void arraySizeTest() {

        linkedList = new LinkedList();
        linkedList.add("Elem 1");
        linkedList.add("Elem 2");
        linkedList.add("Elem 3");
        assertEquals(3, linkedList.size());
    }

    @Test
    void isEmptyTest() {
        linkedList = new LinkedList();
        assertTrue(linkedList.isEmpty());
    }

    @Test
    void contains() {
        linkedList = new LinkedList();
        linkedList.add("a");
        linkedList.add("c");
        linkedList.add("b");
        assertTrue(linkedList.contains("c"));
        assertFalse(linkedList.contains("d"));
    }

    @Test
    void addElementsTest() {
        linkedList = new LinkedList();
        linkedList.add("a");
        linkedList.add(12);
        linkedList.add('c');
        linkedList.add(true);
        assertEquals("a", linkedList.get(0));
        assertEquals(12, linkedList.get(1));
        assertEquals('c', linkedList.get(2));
        assertEquals(true, linkedList.get(3));
    }

    @Test
    void removeElementTest() {
        linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(8);
        linkedList.add("A");
        linkedList.remove(3);
        assertFalse(linkedList.contains("A"));
        linkedList.remove(0);
        assertFalse(linkedList.contains(1));
    }

    @Test
    void clearArrayTest() {
        linkedList = new LinkedList();
        linkedList.add("t");
        linkedList.add("c");
        linkedList.clear();
        assertTrue(linkedList.isEmpty());
    }

    @Test
    void iterateEachElementTest() {

        linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        int ind = 0;
        for(Object element: linkedList) {
            assertEquals(linkedList.get(ind), element);
            ind++;
        }
    }

    @Test
    void removeElementWhileIteratingTest() {

        linkedList = new LinkedList();
        linkedList.add(8);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(0);

        Iterator iterator = linkedList.iterator();

        Object next = iterator.next();
        while (iterator.hasNext()) {
            if (next.equals(8)) {
                iterator.remove();
            }
            next = iterator.next();
        }
        assertFalse(linkedList.contains(8));
    }

    @Test
    void getElementsFromEmptyCollectionTest() {
        linkedList = new LinkedList();
        assertThrows(NoSuchElementException.class, () -> {
            linkedList.iterator().next();
        });
    }

    @Test
    void checkNextElementAtEmptyCollectionTest() {
        linkedList = new LinkedList();
        assertFalse(linkedList.iterator().hasNext());
    }

    @Test
    void getElementFromListTest() {
        linkedList = new LinkedList();
        linkedList.add("097");
        linkedList.add(true);
        linkedList.add('h');
        assertEquals("097", linkedList.get(0));
        assertEquals(true, linkedList.get(1));
        assertEquals('h', linkedList.get(2));
    }

    @Test
    void setElementAtListTest() {
        linkedList = new LinkedList();
        linkedList.add("123");
        linkedList.add(123);
        linkedList.set(1234, 1);
        assertEquals(1234, linkedList.get(1));
    }

    @Test
    void indexOfElementTest() {
        linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add(1);
        linkedList.add('1');
        assertEquals(2, linkedList.indexOf('1'));
    }

    @Test
    void lastIndexOfTest() {
        linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add("1");
        linkedList.add(1);
        linkedList.add(1);
        assertEquals(3, linkedList.lastIndexOf(1));
    }

    @Test
    void addElementForIndex() {
        linkedList = new LinkedList();
        linkedList.add(0);
        linkedList.add(2);
        linkedList.add(4);
        linkedList.add(8);
        linkedList.add(6, 3);
        assertEquals(6, linkedList.get(3));
        assertEquals(5, linkedList.size());
    }

    @Test
    void addElementForIndexIndexOutOfRangeException() {
        linkedList = new LinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.add(2, 1);
        });
    }
}