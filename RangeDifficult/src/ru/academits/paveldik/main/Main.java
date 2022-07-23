package ru.academits.paveldik.main;

import ru.academits.paveldik.range_difficult.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(1, 4);
        Range range2 = new Range(2, 3);

        Range rangeIntersection = range1.getIntersection(range2);

        System.out.print("Пересечение: " + rangeIntersection.getFrom() + "; " + rangeIntersection.getTo());
        System.out.println();

        Range range3 = new Range(1, 4);
        Range range4 = new Range(2, 3);

        Range[] rangeUnion;
        rangeUnion = range3.getUnion(range4);

        for (Range range : rangeUnion) {
            System.out.println("Объединение: " + range.getFrom() + "; " + range.getTo());
            System.out.println();
        }

        Range range5 = new Range(1, 4);
        Range range6 = new Range(2, 3);

        Range[] rangeDifference;
        rangeDifference = range5.getDifference(range6);

        for (Range range : rangeDifference) {
            System.out.println("Вычитание: " + range.getFrom() + "; " + range.getTo());
        }
    }
}
