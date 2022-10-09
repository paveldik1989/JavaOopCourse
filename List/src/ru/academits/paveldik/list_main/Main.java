package ru.academits.paveldik.list_main;

import ru.academits.paveldik.list.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new List<>();
        list.addFirst(23);

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

        Integer toRemoveValue = 2;
        list.remove(toRemoveValue);
        System.out.println("Список после удаления первого встречаюещося элемента " + list);

        System.out.println("Размер: " + list.getSize());
        System.out.println("Первый элемент: " + list.getFirst());
        System.out.println("Элемент по индексу: " + list.get(2));

        list.set(1, 100);
        System.out.println("Список после изменения элемента по индексу: " + list);

        list.remove(2);
        System.out.println("Список после удаления элемента по индексу: " + list);

        list.remove(Integer.valueOf(100500));
        System.out.println("Список после удаления элемента по значению: " + list);


        list.add(1, 500);
        System.out.println("Список после добавления элемента по индексу: " + list);

        list.reverse();
        System.out.println("Список после разворота: " + list);

        List<Integer> listCopy = list.copy();
        list.reverse();

        System.out.println("Скопированный список: " + listCopy);

        List<Integer> list1 = new List<>();
        list1.addLast(10);
        list1.addLast(11);
        System.out.println(list1.remove(Integer.valueOf(11)));
        System.out.println(list1);
    }
}