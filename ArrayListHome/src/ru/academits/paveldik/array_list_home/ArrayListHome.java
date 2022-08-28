package ru.academits.paveldik.array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> stringsList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            while (scanner.hasNextLine()) {
                stringsList.add(scanner.nextLine());
            }
        }

        System.out.println(stringsList);

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(5);
        integers.add(2);
        integers.add(5);

        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i) % 2 == 0) {
                integers.remove(i);
            }
        }

        System.out.println(integers);

        ArrayList<Integer> integersWithoutDuplicates = new ArrayList<>();

        for (Integer integer : integers) {
            if (!integersWithoutDuplicates.contains(integer)) {
                integersWithoutDuplicates.add(integer);
            }
        }

        System.out.println(integersWithoutDuplicates);
    }
}
