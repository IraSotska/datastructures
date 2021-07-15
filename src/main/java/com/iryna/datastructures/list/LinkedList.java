package com.iryna.datastructures.list;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList implements Iterable, List {

    private Node firstNode;
    private Node lastNode;
    private int size = 0;

    @Override
    public void add(Object o) {
        Node node = new Node(o);
        if (size == 0) {
            firstNode = lastNode = node;
        }
        else {
            lastNode.nextElement = node;
            node.previousElement = lastNode;
            lastNode = node;
        }
        size++;
    }

    @Override
    public void add(Object o, int index) {
        if ((index < size) && (index > -1)) {

            Node newNode = new Node(o);
            if (index == 0) {
                firstNode.previousElement = firstNode = newNode;
            }
            else if (index == size -1) {
                lastNode.nextElement = lastNode = newNode;
            }
            else {
                Node nodeBeforeSetted = getNodeByIndex(index - 1);
                newNode.nextElement = nodeBeforeSetted.nextElement;
                newNode.previousElement = nodeBeforeSetted;
                nodeBeforeSetted.nextElement = newNode;
                newNode.nextElement.previousElement = newNode;
            }
            size++;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Object remove(int index) {
        checkIndex(index);
        Node nodeForRemove;
        if (index == 0) {
            nodeForRemove = firstNode;
            firstNode = firstNode.nextElement;
        }
        else if (index == size - 1) {
            nodeForRemove = lastNode;
            lastNode = lastNode.previousElement;
        }
        else {

            nodeForRemove = getNodeByIndex(index);
            nodeForRemove.previousElement = nodeForRemove.nextElement;
            nodeForRemove.nextElement = nodeForRemove.previousElement;
        }
        size--;
        return nodeForRemove;
    }

    @Override
    public Object get(int index) {
        if ((index < size) && (index > -1)) {
            return getNodeByIndex(index).data;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public Object set(Object o, int index) {
        checkIndex(index);
        Node newNode = new Node(o);
        if (index == 0) {
            firstNode.nextElement.previousElement = firstNode = newNode;
        }
        else if (index == size - 1) {
            lastNode.previousElement.nextElement = lastNode = newNode;
        }
        else {
            Node nodeAtIndex = getNodeByIndex(index);
            newNode.previousElement = nodeAtIndex.previousElement;
            newNode.nextElement = nodeAtIndex.nextElement;
            newNode.previousElement.nextElement = newNode;
            newNode.nextElement.previousElement = newNode;
        }
        return newNode;
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (size > 0) {
            Node currentNode = firstNode;
            for (int i = 0; i < size; i++) {
                if (currentNode.data.equals(o)) {
                    return true;
                }
                currentNode = currentNode.nextElement;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        Node currentNode = firstNode;
        for (int i = 0; i < size; i++) {
            if (currentNode.data.equals(o)) {
                return i;
            }
            currentNode = currentNode.nextElement;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node currentNode = lastNode;
        for (int i = size - 1; i > 0; i--) {
            if (currentNode.data.equals(o)) {
                return i;
            }
            currentNode = currentNode.previousElement;
        }
        return -1;
    }

    private Node getNodeByIndex(int index) {
        if (index == 0) {
            return firstNode;
        }
        Node currentNode = firstNode;
        for (int i = 1; i <= index; i++) {
            currentNode = currentNode.nextElement;
        }
        return currentNode;
    }

    private void checkIndex(int index) {
        if ((index > size - 1) || (index < 0)) {
            throw new IndexOutOfBoundsException("Index " + index + " is not exist");
        }
    }

    @Override
    public Iterator iterator() {

        Iterator linkedListIterator = new Iterator() {

            private Node currentNode = firstNode;

            @Override
            public boolean hasNext() {
                return currentNode.nextElement == null;
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    Object data = currentNode.data;
                    currentNode = currentNode.nextElement;
                    return data;
                }
                throw new NoSuchElementException();
            }
            @Override
            public void remove() {
//                LinkedList.this.remove()
                Node nodeBeforeRemoved = currentNode.previousElement;
                currentNode.previousElement = currentNode.nextElement;
                currentNode.nextElement = nodeBeforeRemoved;
                size--;
            }
        };
        return linkedListIterator;
    }

    class Node {
        private Node nextElement;
        private Node previousElement;
        private Object data;

        private Node(Object data) {
            this.data = data;
        }
    }
}
