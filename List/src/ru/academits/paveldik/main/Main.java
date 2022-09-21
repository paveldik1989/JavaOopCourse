package ru.academits.paveldik.main;

import ru.academits.paveldik.list.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new List<>();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(100500);
        System.out.println("Список: " + list);

        list.removeFirst();
        System.out.println("Список после удаления первого элемента: " + list);

        list.addFirst(9);
        System.out.println("Список после вставки первого элемента: " + list);

        list.removeFirstOccurrence(2);
        System.out.println("Список после удаления первого встречаюещося элемента " + list);

        System.out.println("Размер: " + list.getSize());
        System.out.println("Первый элемент: " + list.getFirst());
        System.out.println("Элемент по индексу 2: " + list.get(2));

        list.set(1, 100);
        System.out.println("Список после изменения элемента по индексу 1: " + list);

        list.remove(2);
        System.out.println("Список после удаления элемента по индексу 2: " + list);


        list.add(1, 500);
        System.out.println("Список после добаления элемента по индексу 1: " + list);

        list.reverse();
        System.out.println("Список после разворота: " + list);

        List<Integer> listCopy = list.copy();
        System.out.println("Скопированный список: " + listCopy);
    }
}