package ru.academits.paveldik.shapes;

import java.util.Comparator;

public class MaxPerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return (int) (shape1.getPerimeter() - shape2.getPerimeter());
    }
}