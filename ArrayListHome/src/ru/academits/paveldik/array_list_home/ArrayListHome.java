package ru.academits.paveldik.array_list_home;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static void main(String[] args) {
        ArrayList<String> stringsList = new ArrayList<>();
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringsList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Прочитанные строки  из файла: " + stringsList);

        Integer[] numbersArray = {2, 4, 6, 8, 10, 12, 5, 5, 2, 5};

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(numbersArray));

        System.out.println("Исходный лист чисел: " + numbers);

        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            }
        }

        System.out.println("Лист чисел после удаления четных чисел: " + numbers);

        ArrayList<Integer> numbersWithoutDuplicates = new ArrayList<>(numbers.size());

        for (Integer integer : numbers) {
            if (!numbersWithoutDuplicates.contains(integer)) {
                numbersWithoutDuplicates.add(integer);
            }
        }

        System.out.println("Лист чисел после удаления дубликатов: " + numbersWithoutDuplicates);
    }
}
