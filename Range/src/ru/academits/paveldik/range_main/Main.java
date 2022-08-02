package ru.academits.paveldik.range_main;

import ru.academits.paveldik.range.Range;

public class Main {
    public static void main(String[] args) {
     /*   Range range1 = new Range(1, 2);

        System.out.println(range1.getFrom());
        System.out.println(range1.getTo());
        System.out.println(range1.getLength());
        System.out.println(range1.isInside(1.5));

        range1.setTo(9);
        range1.setFrom(7);

        System.out.println(range1.getFrom());
        System.out.println(range1.getTo());
        System.out.println(range1.isInside(1.5));
*/
        Range range2 = new Range(1, 4);
        Range range3 = new Range(2, 3);

        Range rangeIntersection = range3.getIntersection(range3);

        System.out.print("Пересечение: " + rangeIntersection.getFrom() + "; " + rangeIntersection.getTo());
        System.out.println();

        Range range4 = new Range(1, 2);
        Range range5 = new Range(2, 4);

        Range[] rangesUnion;
        rangesUnion = range4.getUnion(range5);

        for (Range range : rangesUnion) {
            System.out.println("Объединение: " + range.getFrom() + "; " + range.getTo());
            System.out.println();
        }

        Range range6 = new Range(1, 4);
        Range range7 = new Range(2, 3);

        Range[] rangesDifference;
        rangesDifference = range6.getDifference(range7);

        for (Range range : rangesDifference) {
            System.out.println("Вычитание: " + range.getFrom() + "; " + range.getTo());
        }
    }
}