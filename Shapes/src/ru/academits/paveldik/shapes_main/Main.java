package ru.academits.paveldik.shapes_main;

import ru.academits.paveldik.comparators.AreaComparator;
import ru.academits.paveldik.comparators.PerimeterComparator;
import ru.academits.paveldik.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /*Shape square = new Square(5);
        System.out.println("Квадрат");
        System.out.println("Ширина: " + square.getWidth());
        System.out.println("Высота: " + square.getHeight());
        System.out.println("Площадь: " + square.getArea());
        System.out.println("Периметр: " + square.getPerimeter());
        System.out.println();

        Shape triangle = new Triangle(0, 0, 1, 0, 0.5, 1);
        System.out.println("Треугольник");
        System.out.println("Ширина: " + triangle.getWidth());
        System.out.println("Высота: " + triangle.getHeight());
        System.out.println("Площадь: " + triangle.getArea());
        System.out.println("Периметр: " + triangle.getPerimeter());
        System.out.println();

        Shape rectangle = new Rectangle(5, 10);
        System.out.println("Прямоугольник");
        System.out.println("Ширина: " + rectangle.getWidth());
        System.out.println("Высота: " + rectangle.getHeight());
        System.out.println("Площадь: " + rectangle.getArea());
        System.out.println("Периметр: " + rectangle.getPerimeter());
        System.out.println();

        Shape circle = new Circle(1);
        System.out.println("Круг");
        System.out.println("Ширина: " + circle.getWidth());
        System.out.println("Высота: " + circle.getHeight());
        System.out.println("Площадь: " + circle.getArea());
        System.out.println("Периметр: " + circle.getPerimeter());
        System.out.println();
*/
        Shape[] shapes = {
                new Square(2),
                new Square(9),
                new Square(3),
                new Triangle(0, 0, 1, 0, 0.5, 1),
                new Rectangle(1, 100),
                new Circle(1)
        };

        // System.out.println(Arrays.deepToString(shapes));

        Arrays.sort(shapes, new AreaComparator());

        System.out.println("Площадь самой большой фигуры: " + shapes[shapes.length - 1].getArea());
        System.out.println(shapes[shapes.length - 1]);

        Arrays.sort(shapes, new PerimeterComparator());
        System.out.println("Периметр второй по величине фигуры: " + shapes[shapes.length - 2].getPerimeter());

        Shape square1 = new Square(5);
        Shape square2 = new Square(5);
        System.out.println("Равенство квадрата 1 и 2: " + square1.equals(square2));

        Shape triangle = new Triangle(0, 0, 1, 0, 0.5, 1);
        System.out.println(triangle);
        System.out.println("Хэш-код треугольника: " + triangle.hashCode());
        System.out.println("Периметр треугольника: " + triangle.getPerimeter());

      /*  for (Shape e : shapes) {
            System.out.println(e.hashCode());
        }*/
    }
}