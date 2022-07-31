package ru.academits.paveldik.range_main;

import ru.academits.paveldik.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(1, 2);

        System.out.println(range1.getFrom());
        System.out.println(range1.getTo());
        System.out.println(range1.getLength());
        System.out.println(range1.isInside(1.5));

        range1.setTo(9);
        range1.setFrom(7);

        System.out.println(range1.getFrom());
        System.out.println(range1.getTo());
        System.out.println(range1.isInside(1.5));
    }
}
