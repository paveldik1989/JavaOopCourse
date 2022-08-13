package ru.academits.paveldik.range_main;

import ru.academits.paveldik.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(1, 4);
        Range range2 = new Range(2, 3);

        System.out.println("Отрезок 1: " + range1);
        System.out.println("Отрезок 2: " + range2);

        System.out.println("Пересечение: " + range1.getIntersection(range2));

        Range[] union = range1.getUnion(range2);
        System.out.println("Объединение: " + rangesArrayToString(range1.getUnion(range2)));

        Range[] difference1 = range1.getDifference(range2);
        System.out.println("Вычитание 1 - 2: " + rangesArrayToString(range1.getDifference(range2)));

        Range[] difference2 = range2.getDifference(range1);
        System.out.println("Вычитание 2 - 1: " + rangesArrayToString(range2.getDifference(range1)));
    }

    public static String rangesArrayToString(Range[] ranges) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        if (ranges.length > 0) {
            for (Range range : ranges) {
                stringBuilder.append(range).append(", ");
            }

            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}