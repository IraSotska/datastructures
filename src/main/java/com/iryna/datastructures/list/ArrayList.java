package com.iryna.datastructures.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements Iterable<Object>, List {

    private int arraySize = 5;
    private int lastIndex;
    private Object[] array = new Object[arraySize];

    @Override
    public void add(Object o) {
        checkArraySize();
        array[lastIndex] = o;
        lastIndex++;
    }

    @Override
    public void add(Object o, int index) {
        if ((index <= lastIndex) && (index >= 0)) {
            checkArraySize();

            Object elementsAfterIndex = new Object[lastIndex - index + 1];
            System.arraycopy(array, index, elementsAfterIndex, 0, lastIndex - index);
            array[index] = o;
            System.arraycopy(elementsAfterIndex, 0, array, index + 1, lastIndex - index);
            lastIndex++;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Object remove(int index) {

        if (!checkIndex(index)) {
            throw new IndexOutOfBoundsException("Index " + index + " is not exist");
        }
        Object objectForRemove = get(index);

        Object elementsAfterIndex= new Object[lastIndex - index];
        System.arraycopy(array, index + 1, elementsAfterIndex, 0, lastIndex - index);
        System.arraycopy(elementsAfterIndex, 0, array, index, lastIndex - index);
        lastIndex--;
        return objectForRemove;
    }

    @Override
    public Object get(int index) {
        if ((index <= lastIndex) && (index > -1))  {
            return array[index];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public Object set(Object o, int index) {
        if ((index <= lastIndex) && (index > -1)) {
            array[index] = o;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
        lastIndex = 0;
    }

    @Override
    public int size() {
        return lastIndex;
    }

    @Override
    public boolean isEmpty() {
        return lastIndex == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < lastIndex; i++) {
            if (array[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = lastIndex - 1; i > 0; i--) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    private void checkArraySize() {
        if (lastIndex + 1 > arraySize) {
            Object[] newArray = new Object[arraySize * 2];
            System.arraycopy(array, 0, newArray, 0, arraySize);
            arraySize *= 1.5;
            array = newArray;
        }
    }

    private boolean checkIndex(int index) {
        return (index <= lastIndex) && (index >= 0);
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
                if (hasNext()) {
                    Object currentObject = array[currentIndex];
                    currentIndex++;
                    return currentObject;
                }
                throw new NoSuchElementException();
            }
            @Override
            public void remove() {

//                Object elementsAfterIndex = new Object[lastIndex - currentIndex];
//                System.arraycopy(array, currentIndex, elementsAfterIndex, 0, lastIndex - currentIndex);
//                System.arraycopy(elementsAfterIndex, 0, array, currentIndex -1, lastIndex - currentIndex);
//                array[lastIndex - 1] = null;
//                lastIndex--;
                ArrayList.this.remove(currentIndex);
            }
        };
        return arrayIterator;
    }
}
