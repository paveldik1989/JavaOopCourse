package ru.academits.paveldik.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] elements;
    private int size;
    private int modCount;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость не должна быть < 0. Вместимость: " + capacity);
        }

        //noinspection unchecked
        elements = (E[]) new Object[capacity];
    }

    public ArrayList(Collection<? extends E> collection) {
        //noinspection unchecked
        elements = (E[]) collection.toArray();
        size = elements.length;
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
    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Список закончился.");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Во время обхода итератором список изменился.");
            }

            currentIndex++;
            return elements[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(elements, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(elements, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    private void enlargeCapacity() {
        int newCapacity;

        if (elements.length == 0) {
            newCapacity = DEFAULT_CAPACITY;
        } else {
            newCapacity = elements.length * 2;
        }

        elements = Arrays.copyOf(elements, newCapacity);
    }

    public void ensureCapacity(int minCapacity) {
        if (elements.length < minCapacity) {
            elements = Arrays.copyOf(elements, minCapacity);
        }
    }

    public void trimToSize() {
        if (elements.length > size) {
            elements = Arrays.copyOf(elements, size);
        }
    }

    @Override
    public void add(int index, E element) {
        checkIndexForAdd(index);

        if (size == elements.length) {
            enlargeCapacity();
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;

        size++;
        modCount++;
    }

    @Override
    public boolean add(E element) {
        add(size, element);
        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        modCount++;

        return removedElement;
    }

    @Override
    public boolean remove(Object object) {
        int objectIndex = indexOf(object);

        if (objectIndex == -1) {
            return false;
        }

        remove(objectIndex);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndexForAdd(index);

        if (c.isEmpty()) {
            return false;
        }

        ensureCapacity(size + c.size());
        System.arraycopy(elements, index, elements, index + c.size(), size - index);
        int i = index;

        for (E element : c) {
            elements[i] = element;
            i++;
        }

        size += c.size();
        modCount++;

        return true;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(elements, 0, size - 1, null);
        size = 0;
        modCount++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E oldElement = elements[index];
        elements[index] = element;

        return oldElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(elements[i])) {
                remove(i);
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        boolean isChanged = false;

        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(elements[i])) {
                remove(i);
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (int i = 0; i < size; i++) {
            stringBuilder.append(elements[i]);
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть < 0. Индекс: " + index);
        }

        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен может быть < размера списка. Индекс: "
                    + index + ". Размер списка: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть < 0. Индекс: " + index);
        }

        if (index > size) {
            throw new IndexOutOfBoundsException("Индекс должен может быть < размера списка. Индекс: "
                    + index + ". Размер списка: " + size);
        }
    }
}