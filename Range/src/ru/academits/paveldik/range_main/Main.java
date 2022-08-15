package ru.academits.paveldik.range_main;

import ru.academits.paveldik.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(1, 4);
        Range range2 = new Range(2, 3);

        System.out.println("Отрезок 1: " + range1);
        System.out.println("Отрезок 2: " + range2);

        System.out.println("Пересечение: " + range1.getIntersection(range2));

        System.out.println("Объединение: " + convertRangesArrayToString(range1.getUnion(range2)));

        System.out.println("Вычитание 1 - 2: " + convertRangesArrayToString(range1.getDifference(range2)));
        System.out.println("Вычитание 2 - 1: " + convertRangesArrayToString(range2.getDifference(range1)));
    }

    public static String convertRangesArrayToString(Range[] ranges) {
        return Arrays.toString(ranges);
    }
}