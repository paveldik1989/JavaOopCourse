package ru.academits.paveldik.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    public int capacity = DEFAULT_CAPACITY;
    private E[] elements;
    private int length;
    private int modCount;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        if (capacity > DEFAULT_CAPACITY) {
            this.capacity = capacity;
        }

        elements = (E[]) new Object[this.capacity];
    }

    public ArrayList(Collection<? extends E> collection) {
        elements = (E[]) collection.toArray();
        length = elements.length;
        if (elements.length < DEFAULT_CAPACITY) {
            ensureCapacity(DEFAULT_CAPACITY);
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
    public boolean contains(Object object) {
        for (E element : elements) {
            if (Objects.equals(object, element)) {
                return true;
            }
        }

        return false;
    }


    private class myIterator implements Iterator<E> {
        int currentIndex = -1;
        int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Лист закончился.");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Во время обхода итератором удаляли либо добавляли элементы в лист.");
            }

            currentIndex++;
            return elements[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new myIterator();
    }

    @Override
    public E[] toArray() {
        return Arrays.copyOf(elements, length);
    }

    private void enlargeCapacity() {
        capacity *= 2;
        elements = Arrays.copyOf(elements, capacity);
    }

    public void ensureCapacity(int minCapacity) {
        if (capacity < minCapacity) {
            elements = Arrays.copyOf(elements, minCapacity);
            capacity = minCapacity;
        }
    }

    public void trimToSize() {
        if (capacity > size()) {
            elements = Arrays.copyOf(elements, size());
            capacity = size();
        }
    }

    @Override
    public boolean add(E element) {
        if (length == capacity) {
            enlargeCapacity();
        }

        elements[length] = element;
        length++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object object) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(elements[i], object)) {
                System.arraycopy(elements, i + 1, elements, i, length - i - 1);
                elements[length - 1] = null;
                length--;
                modCount++;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addAll(size(), c);
        modCount++;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        ensureCapacity(size() + c.size());
        E[] tempElements = Arrays.copyOfRange(elements, index, size());
        E[] collectionElements = (E[]) c.toArray();
        System.arraycopy(collectionElements, 0, elements, index, c.size());
        System.arraycopy(tempElements, 0, elements, index + c.size(), tempElements.length);
        length += c.size();
        modCount++;
        return true;
    }

    @Override
    public void clear() {
        for (int i = length; i > 0; i--) {
            elements[i] = null;
            length--;
            modCount++;
        }
    }

    @Override
    public E get(int index) {
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        E toReturnElement = elements[index];
        elements[index] = element;

        return toReturnElement;
    }

    @Override
    public void add(int index, E element) {
        if (size() == capacity) {
            enlargeCapacity();
        }

        E[] tempElements = Arrays.copyOfRange(elements, index, size());
        elements[index] = element;
        System.arraycopy(tempElements, 0, elements, index + 1, tempElements.length);
        length++;
        modCount++;
    }

    @Override
    public E remove(int index) {
        E removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, length - index - 1);
        elements[length - 1] = null;
        length--;
        modCount++;
        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;

        for (int i = 0; i < length; i++) {
            if (Objects.equals(elements[i], o)) {
                lastIndex = i;
            }
        }

        return lastIndex;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean isChanged = false;

        for (int i = length - 1; i >= 0; i--) {
            boolean isEqual = false;

            for (Object object : c) {
                if (Objects.equals(elements[i], object)) {
                    isEqual = true;
                    break;
                }
            }

            if (!isEqual) {
                remove(i);
                modCount++;
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean isChanged = false;

        for (int i = length - 1; i >= 0; i--) {
            for (Object object : c) {
                if (Objects.equals(elements[i], object)) {
                    remove(i);
                    modCount++;
                    isChanged = true;
                    break;
                }
            }
        }

        return isChanged;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object object : c) {
            boolean hasElement = false;

            for (E element : elements) {
                if (Objects.equals(element, object)) {
                    hasElement = true;
                    break;
                }
            }

            if (!hasElement) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size()) {
            return (T[]) Arrays.copyOf(elements, size(), a.getClass());
        }

        System.arraycopy(elements, 0, a, 0, size());
        if (a.length > size()) {
            for (int i = size(); i < a.length; i++) {
                a[i] = null;
            }
        }

        return a;
    }

    @Override
    public String toString() {
        if (length == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');


        for (int i = 0; i < length; i++) {
            stringBuilder.append(elements[i]);
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}