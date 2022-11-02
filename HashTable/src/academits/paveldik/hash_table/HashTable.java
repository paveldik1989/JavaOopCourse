package academits.paveldik.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int DEFAULT_LISTS_AMOUNT = 10;

    private final ArrayList<E>[] lists;
    private int size;
    private int modCount;

    public HashTable() {
        this(DEFAULT_LISTS_AMOUNT);
    }

    public HashTable(int listsAmount) {
        if (listsAmount < 1) {
            throw new IllegalArgumentException("Количество списков должно быть >= 1. Количество списков: " + listsAmount);
        }

        //noinspection unchecked
        lists = new ArrayList[listsAmount];
    }

    public HashTable(int listsAmount, Collection<? extends E> collection) {
        this(listsAmount);
        addAll(collection);
    }

    private int getListIndex(Object object) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode() % lists.length);
    }

    private class HashTableIterator implements Iterator<E> {
        private int elementIndex = -1;
        private int listIndex = 0;
        private int elementInListIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return elementIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Хэш-таблица закончилась.");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Во время обхода итератором хэш-таблица изменилась.");
            }

            elementIndex++;
            elementInListIndex++;

            if (lists[listIndex] == null || elementInListIndex >= lists[listIndex].size()) {
                listIndex++;

                while (lists[listIndex] == null || lists[listIndex].isEmpty()) {
                    listIndex++;
                }

                elementInListIndex = 0;
            }

            return lists[listIndex].get(elementInListIndex);
        }
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
        int listIndex = getListIndex(o);
        return lists[listIndex] != null && lists[listIndex].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public E[] toArray() {
        //noinspection unchecked
        E[] elements = (E[]) new Object[size];
        int i = 0;

        for (E element : this) {
            elements[i] = element;
            i++;
        }

        return elements;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, a.getClass());
        }

        int i = 0;

        for (E element : this) {
            //noinspection unchecked
            a[i] = (T) element;
            i++;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        int listIndex = getListIndex(e);

        if (lists[listIndex] == null) {
            lists[listIndex] = new ArrayList<>();
        }

        lists[listIndex].add(e);
        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int listIndex = getListIndex(o);

        if (lists[listIndex] == null) {
            return false;
        }

        boolean isRemoved = lists[listIndex].remove(o);

        if (isRemoved) {
            size--;
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
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (E element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        boolean isChanged = false;

        for (Object element : c) {
            while (remove(element)) {
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;

        for (ArrayList<E> list : lists) {
            if (list == null) {
                continue;
            }

            int listSizeBeforeDelete = list.size();

            if (list.retainAll(c)) {
                isChanged = true;
                size -= listSizeBeforeDelete - list.size();
            }
        }

        if (isChanged) {
            modCount++;
        }

        return isChanged;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (ArrayList<E> list : lists) {
            if (list == null || list.isEmpty()) {
                continue;
            }

            list.clear();
        }

        modCount++;
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (E element : this) {
            stringBuilder.append(element);
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}