package ru.academits.paveldik.array_list_home;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static void main(String[] args) {
         ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл input.txt не найден.");
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода.");
        }

        System.out.println("Прочитанные строки  из файла: " + lines);

        Integer[] numbersArray = {2, 4, 6, 8, 10, 12, 5, 5, 2, 5};

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(numbersArray));

        System.out.println("Исходный список чисел: " + numbers);

        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            }
        }

        System.out.println("Список чисел после удаления четных чисел: " + numbers);

        ArrayList<Integer> numbersWithoutDuplicates = new ArrayList<>(numbers.size());

        for (Integer integer : numbers) {
            if (!numbersWithoutDuplicates.contains(integer)) {
                numbersWithoutDuplicates.add(integer);
            }
        }

        System.out.println("Список чисел после удаления дубликатов: " + numbersWithoutDuplicates);
    }
}
