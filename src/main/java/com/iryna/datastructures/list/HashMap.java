package com.iryna.datastructures.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMap<K, V> implements Map<K, V>, Iterable{

    private ArrayList<Entry<K, V>>[] buckets = new ArrayList[5];
    private int size = 0;

    public HashMap() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
//        for (ArrayList<Entry> entryArrayList: backets) {
//            entryArrayList = new ArrayList<>();
//        }
    }

    @Override
    public V put(K key, V value) {
        Entry<K, V> newEntry = new Entry<>(key, value);
        getBucketByKey(key).add(newEntry);
        size++;
        return value;
    }

    @Override
    public V get(K key) {
        ArrayList<Entry<K, V>> arr = getBucketByKey(key);

        for (int i = 0; i < arr.size(); i++) {
            if (((Entry)arr.get(i)).key.equals(key)) {
                return (V) ((Entry) arr.get(i)).value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V remove(K key) {
        ArrayList<Entry<K, V>> e = getBucketByKey(key);
        for (int i = 0; i < e.size(); i++) {
            if (((Entry)e.get(i)).key.equals(key)) {
                V v = (V) ((Entry<?, ?>) e.get(i)).value;
                e.remove(i);
                size--;
                return v;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private ArrayList<Entry<K, V>> getBucketByKey(K key) {
        return buckets[key.hashCode() % buckets.length];
    }

    @Override
    public Iterator iterator() {

        Iterator hashMapIterator = new Iterator() {

            private int currentBucket = 0;
            private int currentEntry = 0;
            private int currentElement = 0;

            @Override
            public boolean hasNext() {
                return currentElement < size;
            }

            @Override
            public V next() {
                if (hasNext()) {
                    if (buckets[currentBucket].size() == 0) {
                        currentBucket++;
                        currentEntry = 0;
                    }
                    V currentValue = (buckets[currentBucket].get(currentEntry)).value;
                    if(currentEntry == buckets[currentBucket].size() - 1) {
                        if (currentBucket < buckets.length - 1) {
                            currentBucket++;
                            currentEntry = 0;
                        }
                    }
                    else {
                        currentEntry++;
                    }
                    currentElement++;
                    return currentValue;
                }
                 throw new NoSuchElementException();
            }
            @Override
            public void remove() {
                Entry<K, V> entry = (Entry<K,V>)buckets[currentBucket].get(currentEntry);
                HashMap.this.remove(entry.key);
            }
        };
        return hashMapIterator;
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
