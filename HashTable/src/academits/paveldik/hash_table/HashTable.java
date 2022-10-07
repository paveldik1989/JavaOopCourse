package academits.paveldik.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private final ArrayList<T>[] buckets;
    private int length = 0;
    private int modCount;

    public HashTable(int bucketsAmount) {
        buckets = new ArrayList[bucketsAmount];

        for (int i = 0; i < bucketsAmount; i++) { // Почему если здесь foreach, то не работает?
            buckets[i] = new ArrayList<>();
        }
    }

    public HashTable(int bucketsAmount, Collection<? extends T> collection) {
        this(bucketsAmount);
        this.addAll(collection);
    }

    private int calculateBucketIndex(Object object) {
        return Math.abs(object.hashCode() % buckets.length);
    }

    private class MyIterator implements Iterator<T> {
        private int elementIndex = -1;
        private int bucketIndex = 0;
        private int listIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return elementIndex + 1 < length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Хэш-таблица закончилась.");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Во время обхода итератором удаляли либо добавляли элементы в хэш-таблицу.");
            }

            elementIndex++;
            listIndex++;

            if (buckets[bucketIndex].size() == 0 || listIndex >= buckets[bucketIndex].size()) {
                bucketIndex++;

                while (buckets[bucketIndex].size() == 0) {
                    bucketIndex++;
                }

                listIndex = 0;
            }

            return buckets[bucketIndex].get(listIndex);
        }
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return buckets[calculateBucketIndex(o)].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public T[] toArray() {
        T[] elements = (T[]) new Object[length];
        int i = 0;

        for (T element : this) {
            elements[i] = element;
            i++;
        }

        return elements;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < length) {
            return (T1[]) this.toArray();
        }

        int i = 0;

        for (T element : this) {
            a[i] = (T1) element;
            i++;
        }

        for (int j = length; j < a.length; j++) {
            a[j] = null;
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        buckets[calculateBucketIndex(t)].add(t);
        length++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        boolean isRemoved = buckets[calculateBucketIndex(o)].remove(o);

        if (isRemoved) {
            length--;
            modCount++;
        }

        return isRemoved;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object element : c) {
            remove(element);
        }

        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;
        HashTable<?> hashTable = new HashTable<>(buckets.length, c);

        for (int i = 0; i < buckets.length; i++) {
            int listLength = buckets[i].size();

            if (buckets[i].retainAll(hashTable.buckets[i])) {
                isChanged = true;
                length -= listLength - buckets[i].size();
            }
        }

        return isChanged;
    }

    @Override
    public void clear() {
        for (ArrayList<T> bucket : buckets) {
            bucket.clear();
            modCount++;
        }

        length = 0;
    }

    @Override
    public String toString() {
        if (length == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (T t : this) {
            stringBuilder.append(t);
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}