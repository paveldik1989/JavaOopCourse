package ru.academits.paveldik.list;

import ru.academits.paveldik.node.Node;

import java.util.Objects;

public class List<T> {
    private Node<T> head;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public T getFirst() {
        return head.value;
    }

    public T get(int index) {
        checkIndex(index);

        int i = 0;
        Node<T> node = head;

        while (i != index) {
            node = node.next;
            i++;
        }

        return node.value;
    }

    private void checkIndex(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index must be < than size of the list. Size is " + size
                    + ". Index is " + index);
        if (index < 0)
            throw new IndexOutOfBoundsException("Index must be >= 0 than size of the list. Index is " + size);
    }

    public T set(int index, T element) {
        checkIndex(index);

        int i = 0;
        Node<T> node = head;

        while (i != index) {
            node = node.next;
            i++;
        }

        T value = node.value;
        node.value = element;

        return value;
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>();
        node.value = value;

        if (head != null) {
            node.next = head;
        }
        head = node;
        size++;
    }

    public void addLast(T value) {
        Node<T> node = new Node<>();
        node.value = value;

        if (head == null) {
            head = node;
        } else {
            Node<T> lastNode = head;

            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }

            lastNode.next = node;
        }

        size++;
    }

    public T remove(int index) {
        checkIndex(index);

        int i = 0;
        Node<T> current = head;
        Node<T> previous = null;

        while (i != index) {
            previous = current;
            current = current.next;
            i++;
        }

        T value = current.value;

        if (previous != null) {
            if (current.next != null) {
                previous.next = current.next;
            } else {
                previous.next = null;
            }
        } else {
            if (current.next != null) {
                head = current.next;
            } else {
                head = null;
            }
        }

        size--;
        return value;
    }

    public void add(int index, T element) {
        checkIndex(index);

        int i = 0;
        Node<T> current = head;
        Node<T> previous = null;

        while (i != index) {
            previous = current;
            current = current.next;
            i++;
        }

        Node<T> node = new Node<>();
        node.value = element;

        if (previous != null) {
            previous.next = node;
        } else {
            head = node;
        }

        node.next = current;
        size++;
    }

    public boolean removeFirstOccurrence(T element) {
        Node<T> current = head;
        Node<T> previous = null;

        while (!Objects.equals(current.value, element)) {
            previous = current;
            current = current.next;

            if (current == null) {
                return false;
            }
        }

        if (previous != null) {
            if (current.next != null) {
                previous.next = current.next;
            } else {
                previous.next = null;
            }
        } else {
            if (current.next != null) {
                head = current.next;
            } else {
                head = null;
            }
        }

        size--;
        return true;
    }

    public T removeFirst() {
        T value = head.value;

        if (head.next != null) {
            head = head.next;
        } else {
            head = null;
        }

        size--;
        return value;
    }

    public void reverse() {
        Node<T> previousNode = null;
        Node<T> currentNode = head;
        Node<T> nextNode;

        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }

        head = previousNode;
    }

    public List<T> copy() {
        List<T> list = new List<>();
        Node<T> current = head;

        while (current != null) {
            list.addLast(current.value);
            current = current.next;
        }

        return list;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        Node<T> listNode = head;

        while (listNode != null) {
            stringBuilder.append(listNode.value);
            stringBuilder.append(", ");
            listNode = listNode.next;
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}