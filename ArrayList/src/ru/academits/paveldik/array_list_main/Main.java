package ru.academits.paveldik.array_list_main;

import ru.academits.paveldik.array_list.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>(-100);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        System.out.println(list1);

        for (Integer integer : list1) {
            System.out.println(integer);
        }

        Number[] numbers = {1.1, 13.4, 14, 1, 4, 14.99};
        list1.toArray(numbers);
        System.out.println(Arrays.toString(numbers));

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(null);
        list2.add(2);

        list2.trimToSize();
        System.out.println(list2.remove(null));
        System.out.println(list2);

        ArrayList<Number> list3 = new ArrayList<>(list1);
        System.out.println(list3);
    }
}