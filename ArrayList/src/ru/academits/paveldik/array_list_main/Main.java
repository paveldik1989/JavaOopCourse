package ru.academits.paveldik.array_list_main;

import ru.academits.paveldik.array_list.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>(0);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);

        for (Integer integer : list1) {
            System.out.println(integer);
        }

        Number[] numbers = {1.1, 13.4, 14, 1};
        numbers = list1.toArray(numbers);
        System.out.println(Arrays.toString(numbers));

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(null);
        list2.add(2);

        Object[] objects = list2.toArray();
        System.out.println(Arrays.toString(objects));

        list2.trimToSize();
        System.out.println(list2.remove(null));
        System.out.println(list2);

        ArrayList<Number> list3 = new ArrayList<>(list1);
        System.out.println(list3);

        list3.clear();
        System.out.println(list3);
    }
}