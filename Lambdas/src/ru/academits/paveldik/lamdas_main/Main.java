package ru.academits.paveldik.lamdas_main;

import ru.academits.paveldik.lamdas_person.Person;

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

        // А
        List<String> names = people.stream()
                .map(Person::getName)
                .distinct()
                .toList();

        System.out.println("Имена: " + names);

        // Б
        String allNamesString = names.stream()
                .collect(Collectors.joining(", ", "Имена: ", ""));

        System.out.println(allNamesString);

        // В
        try {
            double notAdultAverageAge = people.stream()
                    .filter(person -> person.getAge() < 18)
                    .mapToInt(Person::getAge)
                    .average()
                    .orElseThrow(IllegalStateException::new);


            System.out.println("Средний возраст лиц меньше 18 лет: " + notAdultAverageAge);
        } catch (IllegalStateException err) {
            System.out.println("Невозможно вычислить средний возраст лиц меньше 18 лет.");
            // Как напечатать стек вызовов, чтобы он не печатался в консоли в нужном месте, а не после фразы "Введите число элементов.."?
        }

        // Г
        Map<String, Double> namesAverageAges = people.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));

        System.out.println("Средний возраст для имен: " + namesAverageAges);

        // Д
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

        DoubleStream squareRoots = DoubleStream.iterate(0, x -> x + 1).map(Math::sqrt);

        System.out.println("Корни целых чисел:");
        squareRoots.limit(elementsAmount).forEach(System.out::println);

        // Задача 2*
        System.out.print("Введите число элементов для вычисления чисел Фибоначчи: ");
        elementsAmount = scanner.nextInt();

        Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                .mapToInt(n -> n[0])
                .limit(elementsAmount)
                .forEach(System.out::println);
    }
}