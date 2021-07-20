package com.iryna.datastructures.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMap<K, V> implements Map<K, V> {

    private final int DEFAULT_SIZE = 5;
    private int bucketCount = DEFAULT_SIZE;
    private ArrayList<Entry<K, V>>[] buckets = new ArrayList[bucketCount];
    private int elementCounter = 0;
    private static final double loadFactor = 0.75;
    private static final int growFactor = 2;

//    public HashMap() {
//        super();
//        this(DEFAULT_SIZE);
//    }
//
    public HashMap() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    @Override
    public V put(K key, V value) {
        checkOccupancy();
        elementCounter++;
        return put(key, value, buckets);
    }

    public V put(K key, V value, ArrayList<Entry<K, V>>[] targetBucket) {
        Entry<K, V> newEntry = new Entry<>(key, value);
        getBucketByKey(key, targetBucket).add(newEntry);
        return value;
    }

    @Override
    public V get(K key) {
        ArrayList<Entry<K, V>> arr = getBucketByKey(key, buckets);

        for (Entry<K, V> kvEntry : arr) {
            if (kvEntry.key.equals(key)) {
                return kvEntry.value;
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
        ArrayList<Entry<K, V>> e = getBucketByKey(key, buckets);
        for (int i = 0; i < e.size(); i++) {
            if (e.get(i).key.equals(key)) {
                V v = e.get(i).value;
                e.remove(i);
                elementCounter--;
                return v;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return elementCounter;
    }

    private ArrayList<Entry<K, V>> getBucketByKey(K key, ArrayList<Entry<K, V>>[] targetBucket) {
        return targetBucket[Math.abs(key.hashCode()) % bucketCount];
    }

    private void checkOccupancy() {

        if (buckets.length * loadFactor < elementCounter) {
            bucketCount *= growFactor;
            ArrayList<Entry<K, V>>[] newBuckets = new ArrayList[bucketCount];
            for (int i = 0; i < bucketCount; i++) {
                newBuckets[i] = new ArrayList<>();
            }
            fillNewBuckets(newBuckets);
            buckets = newBuckets;
        }
    }

    private void fillNewBuckets(ArrayList<Entry<K, V>>[] newBuckets) {
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                put(buckets[i].get(j).key, buckets[i].get(j).value, newBuckets);
            }
        }
    }

    @Override
    public Iterator<V> iterator() {

        Iterator<V> hashMapIterator = new Iterator() {
            private int currentBucket = 0;
            private Iterator<Entry<K, V>> arrayIterator = buckets[currentBucket].iterator();

            @Override
            public boolean hasNext() {
                if (!arrayIterator.hasNext()) {
                    if (currentBucket == buckets.length - 1) {
                        return false;
                    }
                    currentBucket++;
                    if (buckets[currentBucket].size() == 0) {
                        while (buckets[currentBucket].size() == 0) {
                            currentBucket++;
                        }
                    }
                    arrayIterator = buckets[currentBucket].iterator();
                }
                return true;
            }

            @Override
            public V next() {
                if (arrayIterator.hasNext()) {
                    Entry<K, V> entry = arrayIterator.next();
                    return entry.value;
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                arrayIterator.remove();
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
