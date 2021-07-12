package com.iryna.datastructures.list;
import java.util.Iterator;

public class LinkedList implements Iterable {

    private Node firstNode;
    private Node lastNode;
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

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

    public void remove(Object o) {
        if (size > 0) {

            if (firstNode.data.equals(o)) {
                firstNode = firstNode.nextElement;
                size--;
            }
            else if (lastNode.data.equals(o)) {
                lastNode = lastNode.previousElement;
                size--;
            }
            else {
                Node currentNode = firstNode;

                while (currentNode.nextElement != null) {
                    if (currentNode.data.equals(o)) {
                        Node nodeBeforeForRemove = currentNode.previousElement;
                        currentNode.previousElement.nextElement = currentNode.nextElement;
                        currentNode.nextElement.previousElement = nodeBeforeForRemove;
                        size--;
                        return;
                    }
                    currentNode = currentNode.nextElement;
                }
            }
        }
    }

    public void clear() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    public Object get(int index) {
        if ((index < size) && (index > -1)) {
            return getNodeByIndex(index).data;
        }
        throw new IndexOutOfBoundsException();
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

    public void set(int index, Object element) {
        if ((index <= size - 1) && (index > -1)) {

            Node newNode = new Node(element);
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
        }
        new IndexOutOfBoundsException();
    }

    public void add(int index, Object element) {

        if ((index < size) && (index > -1)) {

            Node newNode = new Node(element);
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

    public int indexOf(Object o) {

        Node currentNode = firstNode;
        for (int i = 0; i < size; i++) {
            if (currentNode.data.equals(o)) {
                return i;
            }
            currentNode = currentNode.nextElement;
        }
        throw new NullPointerException();
    }

    public int lastIndexOf(Object o) {

        Node currentNode = lastNode;
        for (int i = size - 1; i > 0; i--) {
            if (currentNode.data.equals(o)) {
                return i;
            }
            currentNode = currentNode.previousElement;
        }
        throw new NullPointerException();
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
                Object data = currentNode.data;
                currentNode = currentNode.nextElement;
                return data;
            }
            @Override
            public void remove() {
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

        public Node(Object data) {
            this.data = data;
        }
    }
}
