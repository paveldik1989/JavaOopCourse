package ru.academits.paveldik.list;

import java.util.Objects;

public class List<T> {
    private Node<T> head;
    private int size;

    public int getSize() {
        return size;
    }

    public T getFirst() {
        checkHead();

        return head.getValue();
    }

    public T get(int index) {
        checkIndex(index);
        Node<T> node = getNodeByIndex(index);

        return node.getValue();
    }

    public T set(int index, T element) {
        checkIndex(index);

        Node<T> node = getNodeByIndex(index);
        T value = node.getValue();
        node.setValue(element);

        return value;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
    }

    public void addLast(T value) {
        add(size, value);
    }

    public T remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        Node<T> previousNode = getNodeByIndex(index - 1);
        Node<T> currentNode = previousNode.getNext();

        T value = currentNode.getValue();
        previousNode.setNext(currentNode.getNext());
        size--;
        return value;
    }

    public void add(int index, T element) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index must be <= than size of the list. Size is " + size
                    + ". Index is " + index);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Index must be >= 0 than size of the list. Index is " + size);
        }

        if (index == 0) {
            addFirst(element);
            return; // Допустимо ли так делать в void методе чтобы не оборачивать в if-else весь метод?
        }

        Node<T> previousNode = getNodeByIndex(index - 1);
        Node<T> currentNode = previousNode.getNext();

        Node<T> node = new Node<>(element, currentNode);
        previousNode.setNext(node);
        size++;
    }

    public boolean remove(T element) {
        if (head == null) {
            return false;
        }

        Node<T> currentNode = head;
        Node<T> previousNode = null;

        while (!Objects.equals(currentNode.getValue(), element)) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();

            if (currentNode == null) {
                return false;
            }
        }

        if (previousNode != null) {
            previousNode.setNext(currentNode.getNext());
        } else {
            head = currentNode.getNext();
        }

        size--;
        return true;
    }

    public T removeFirst() {
        checkHead();
        T value = head.getValue();
        head = head.getNext();
        size--;
        return value;
    }

    public void reverse() {
        Node<T> previousNode = null;
        Node<T> currentNode = head;

        while (currentNode != null) {
            Node<T> nextNode = currentNode.getNext();
            currentNode.setNext(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
        }

        head = previousNode;
    }

    public List<T> copy() {
        if (head == null) {
            return null;
        }

        List<T> listCopy = new List<>();
        listCopy.head = new Node<>(head.getValue());
        listCopy.size = size;

        Node<T> currentNode = head;
        Node<T> currentNodeCopy = listCopy.head;

        while (currentNode.getNext() != null) {
            Node<T> nextNodeCopy = new Node<>(currentNode.getNext().getValue());
            currentNodeCopy.setNext(nextNodeCopy);

            currentNode = currentNode.getNext();
            currentNodeCopy = nextNodeCopy;
        }

        return listCopy;
    }

    private void checkIndex(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index must be < than size of the list. Size is " + size
                    + ". Index is " + index);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Index must be >= 0 than size of the list. Index is " + size);
        }
    }

    private void checkHead() {
        if (head == null) {
            throw new NullPointerException("The method is not valid for an empty list.");
        }
    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> node = head;
        int i = 0;

        while (i != index) {
            node = node.getNext();
            i++;
        }

        return node;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        Node<T> listNode = head;

        while (listNode != null) {
            stringBuilder.append(listNode.getValue());
            stringBuilder.append(", ");
            listNode = listNode.getNext();
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}