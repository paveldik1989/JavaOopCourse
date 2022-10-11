package ru.academits.paveldik.person_main;

import ru.academits.paveldik.person.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Анжелла", 25),
                new Person("Снежанна", 30),
                new Person("Изабелла", 28),
                new Person("Жанна", 58),
                new Person("Матильда", 35),
                new Person("Францеска", 25),
                new Person("Каролина", 18),
                new Person("Лолита", 12),
                new Person("Анжелла", 14),
                new Person("Белла", 13),
                new Person("Катрин", 15),
                new Person("Жанна", 11)
        );

        //А
        List<String> names = people.stream()
                .map(Person::getName)
                .distinct()
                .toList();

        System.out.println("Имена: " + names);

        //Б
        String allNamesString = names.stream()
                .collect(Collectors.joining(", ", "Имена: ", ""));

        System.out.println(allNamesString);

        //В
        double notAdultAverageAge = people.stream()
                .filter(person -> person.getAge() < 18)
                .mapToInt(Person::getAge)
                .average().orElseThrow(IllegalStateException::new); // Пришлось просто загуглить, неясно что такое OptionalDouble

        System.out.println("Средний возраст лиц меньше 18 лет: " + notAdultAverageAge);

        //Г
        Map<String, Double> namesAverageAge = people.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));

        System.out.println("Средний возраст для имен: " + namesAverageAge);

        //Д
        List<Person> peopleWithAgeFrom20To45InDescendingOrder = people.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .toList();

        System.out.println("Имена людей с возрастом 20-45 лет, отсортированные по убыванию возраста: "
                + peopleWithAgeFrom20To45InDescendingOrder.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ")));

        // Задача 2
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число элементов для вычисления корней: ");
        int elementsAmount = scanner.nextInt();
        DoubleStream squareRoots = DoubleStream.iterate(0, x -> x + 1).map(Math::sqrt); // Почему сюда нельзя дописать? .collect(Collectors.joining(", "));

        System.out.println("Корни целых чисел:");
        squareRoots.limit(elementsAmount).forEach(System.out::println);

        // Задача 2*
        System.out.print("Введите число элементов для вычисления чисел Фибоначчи: ");
        elementsAmount = scanner.nextInt();
        final int[] previousNumber = {0}; // как можно выкрутится без этого? Еще видел что реализуют через массив из двух чисел
        IntStream fibonacciNumbers = IntStream.iterate(1, currentNumber -> {
            currentNumber += previousNumber[0];
            previousNumber[0] = currentNumber - previousNumber[0];
            return currentNumber;
        });

        System.out.println("Числа Фибоначчи:");
        fibonacciNumbers.limit(elementsAmount).forEach(System.out::println);
    }
}