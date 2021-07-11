package com.iryna.datastructures.list;

import java.util.Iterator;

public class LinkedList implements Iterable{
    @Override
    public Iterator iterator() {

        Iterator linkedlistIterator = new Iterator() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                return null;
            }
            @Override
            public void remove() {

            }
        };
        return null;
    }
}
