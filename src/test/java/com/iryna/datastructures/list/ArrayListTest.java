package com.iryna.datastructures.list;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    @Test
    void arraySizeTest() {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Elem 1");
        arrayList.add("Elem 2");
        arrayList.add("Elem 3");
        assertEquals(3, arrayList.size());
    }

    @Test
    void isEmptyTest() {
        ArrayList<String> arrayList = new ArrayList<>();
        assertTrue(arrayList.isEmpty());
    }

    @Test
    void contains() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("c");
        arrayList.add("b");
        assertTrue(arrayList.contains("c"));
    }

    @Test
    void addElementsTest() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(13);
        arrayList.add(12);
        arrayList.add(0);
        arrayList.add(34);
        assertEquals(13, arrayList.get(0));
        assertEquals(12, arrayList.get(1));
        assertEquals(0, arrayList.get(2));
        assertEquals(34, arrayList.get(3));
    }

    @Test
    void removeElementTest() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(8);
        arrayList.add(13);
        arrayList.remove(2);
        assertFalse(arrayList.contains(2));
        arrayList.remove(13);
        assertFalse(arrayList.contains(13));
    }

    @Test
    void clearArrayTest() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("t");
        arrayList.add("c");
        arrayList.clear();
        assertTrue(arrayList.isEmpty());
    }

    @Test
    void iterateEachElementTest() {

        ArrayList<Integer> arrayList = new ArrayList<>();
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

        ArrayList<Integer> arrayList = new ArrayList<>();
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
        ArrayList<String> arrayList = new ArrayList();
        assertThrows(NoSuchElementException.class, () -> {
            arrayList.iterator().next();
        });
    }

    @Test
    void checkNextElementAtEmptyCollectionTest() {
        ArrayList<String> arrayList = new ArrayList<>();
        assertFalse(arrayList.iterator().hasNext());
    }

    @Test
    void getElementFromListTest() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("097");
        arrayList.add("");
        arrayList.add("k");
        assertEquals("097", arrayList.get(0));
        assertEquals("", arrayList.get(1));
        assertEquals("k", arrayList.get(2));
    }

    @Test
    void setElementAtListTest() {
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.add(12);
        arrayList.add(123);
        arrayList.set(1, 1234);
        assertEquals(1234, arrayList.get(1));
    }

    @Test
    void indexOfElementTest() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("3");
        arrayList.add("34");
        assertEquals(2, arrayList.indexOf("34"));
    }

    @Test
    void lastIndexOfTest() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(1);
        arrayList.add(1);
        assertEquals(3, arrayList.lastIndexOf(1));
    }

    @Test
    void addElementForIndex() {
        ArrayList<Integer> arrayList = new ArrayList<>();
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
        ArrayList<Integer> arrayList = new ArrayList();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            arrayList.add(2, 1);
        });
    }
}