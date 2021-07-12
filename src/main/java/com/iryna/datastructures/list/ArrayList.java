package com.iryna.datastructures.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList implements Iterable<Object> {

    private int arraySize = 5;
    private int lastIndex;
    private Object[] array = new Object[arraySize];

    public int size() {
        return lastIndex;
    }

    public boolean isEmpty() {
        return lastIndex == 0;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < lastIndex; i++) {
            if (array[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    public void add(Object o) {
        checkArraySize();
        array[lastIndex] = o;
        lastIndex++;
    }

    private void checkArraySize() {
        if (lastIndex + 1 > arraySize) {
            Object[] newArray = new Object[arraySize * 2];
            System.arraycopy(array, 0, newArray, 0, arraySize);
            arraySize *= 1.5;
            array = newArray;
        }
    }

    public void remove(Object o) {
        int indexForRemove = indexOf(o);

        Object[] elementsAfterIndex= new Object[lastIndex - indexForRemove];
        System.arraycopy(array, indexForRemove + 1, elementsAfterIndex, 0, lastIndex - indexForRemove);
        System.arraycopy(elementsAfterIndex, 0, array, indexForRemove, lastIndex - indexForRemove);
        lastIndex--;
    }

    public void clear() {
        Arrays.fill(array, null);
        lastIndex = 0;
    }

    public Object get(int index) {
        if ((index <= lastIndex) && (index > -1))  {
            return array[index];
        }
        throw new IndexOutOfBoundsException();
    }

    public void set(int index, Object element) {
        if ((index <= lastIndex) && (index > -1)) {
            array[index] = element;
        }
        new IndexOutOfBoundsException();
    }

    public void add(int index, Object element) {

        if ((index <= lastIndex) && (index > -1)) {
            checkArraySize();

            Object[] elementsAfterIndex= new Object[lastIndex - index + 1];
            System.arraycopy(array, index, elementsAfterIndex, 0, lastIndex - index);
            array[index] = element;
            System.arraycopy(elementsAfterIndex, 0, array, index + 1, lastIndex - index);
            lastIndex++;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int indexOf(Object o) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        throw new NullPointerException();
    }

    public int lastIndexOf(Object o) {
        for (int i = lastIndex - 1; i > 0; i--) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        throw new NullPointerException();
    }

    @Override
    public Iterator<Object> iterator() {

        Iterator<Object> arrayIterator = new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < lastIndex;
            }

            @Override
            public Object next() {
                if (currentIndex < lastIndex) {
                    Object currentObject = array[currentIndex];
                    currentIndex++;
                    return currentObject;
                }
                throw new NoSuchElementException();
            }
            @Override
            public void remove() {

                Object[] elementsAfterIndex= new Object[lastIndex - currentIndex];
                System.arraycopy(array, currentIndex, elementsAfterIndex, 0, lastIndex - currentIndex);
                System.arraycopy(elementsAfterIndex, 0, array, currentIndex -1, lastIndex - currentIndex);
                array[lastIndex - 1] = null;
                lastIndex--;
            }
        };
        return arrayIterator;
    }
}
