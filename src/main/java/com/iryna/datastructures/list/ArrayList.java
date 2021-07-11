package com.iryna.datastructures.list;

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
            if(array[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(Object o) {
        checkArraySize();
        array[lastIndex] = o;
        lastIndex++;
        return false;
    }

    private void checkArraySize() {
        if(lastIndex + 1 > arraySize) {
            Object[] newArray = new Object[arraySize * 2];
            System.arraycopy(array, 0, newArray, 0, arraySize);
            arraySize *= 2;
            array = newArray;
        }
    }

    public Object[] remove(Object o) {
        int indexForRemove = indexOf(o);

        Object[] elementsAfterIndex= new Object[lastIndex - indexForRemove];
        System.arraycopy(array, indexForRemove + 1, elementsAfterIndex, 0, lastIndex - indexForRemove);
        System.arraycopy(elementsAfterIndex, 0, array, indexForRemove, lastIndex - indexForRemove);
        lastIndex--;
        return array;
    }

    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        lastIndex = 0;
    }

    public Object get(int index) {
        return array[index];
    }

    public void set(int index, Object element) {
        array[index] = element;
    }

    public Object[] add(int index, Object element) {

        if(index <= lastIndex) {
            checkArraySize();

            Object[] elementsAfterIndex= new Object[lastIndex - index + 1];
            System.arraycopy(array, index, elementsAfterIndex, 0, lastIndex - index);
            array[index] = element;
            System.arraycopy(elementsAfterIndex, 0, array, index + 1, lastIndex - index);
            return array;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int indexOf(Object o) {
        for (int i = 0; i < array.length; i++) {
            if(array[i].equals(o)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public Iterator<Object> iterator() {

        Iterator<Object> arrayIterator = new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                System.out.println(currentIndex<lastIndex);
                return currentIndex < lastIndex;
            }

            @Override
            public Object next() {
                if(currentIndex < lastIndex) {
                    currentIndex++;
                    return array[currentIndex];
                }
                throw new NoSuchElementException();
            }
            @Override
            public void remove() {

                Object[] elementsAfterIndex= new Object[lastIndex - currentIndex];
                System.arraycopy(array, currentIndex + 1, elementsAfterIndex, 0, lastIndex - currentIndex);
                System.arraycopy(elementsAfterIndex, 0, array, currentIndex, lastIndex - currentIndex);
                lastIndex--;

                for (int i = 0; i < array.length; i++) {
                    System.out.println(array[i]);
                }
            }
        };

        return arrayIterator;
    }
}
