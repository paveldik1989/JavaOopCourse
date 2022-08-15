package ru.academits.paveldik.vector_main;

import ru.academits.paveldik.vector.Vector;

import static ru.academits.paveldik.vector.Vector.getDotProduct;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание векторов разными конструкторами:");
        Vector vector1 = new Vector(3);
        System.out.println(vector1);

        Vector vector2 = new Vector(vector1);
        System.out.println(vector2);

        Vector vector3 = new Vector(new double[]{1, 1, 1});
        System.out.println(vector3);

        Vector vector4 = new Vector(5, new double[]{1, 1, 1, 1});
        System.out.println(vector4);

        System.out.println();

        System.out.println("Сложение, вычитание умножение и т.п. операции: ");
        System.out.println("Размер: " + vector4.getSize());
        Vector vector5 = new Vector(new double[]{1, 1, 1});
        Vector vector6 = new Vector(new double[]{1, 0, 0, 7, 0, 1});

        vector5.getSum(vector6);
        System.out.println("Сложение: " + vector5);

        vector5.getDifference(vector6);
        System.out.println("Вычитание: " + vector5);

        vector5.multiplyByScalar(2);
        System.out.println("Умножение на скаляр: " + vector5);

        vector5.reverse();
        System.out.println("Разворот: " + vector5);

        System.out.println("Длина: " + vector5.getLength());
        System.out.println("Скалярное произведение: " + getDotProduct(vector5, vector6));

        System.out.println();
        System.out.println("Геттер, сеттер, equals, hashCode");
        System.out.println("Получение компонента вектора по индексу: " + vector6.getComponent(3));

        vector6.setComponent(3, 9);
        System.out.println("Задание компонента вектора по индексу: " + vector6);

        Vector vector7 = new Vector(new double[]{1, 1, 1});

        System.out.println("Проверка на равенство: " + vector5.equals(vector6));

        System.out.println("Хэшкод: " + vector5.hashCode());
        System.out.println("Хэшкод: " + vector6.hashCode());
        System.out.println("Хэшкод: " + vector7.hashCode());
    }
}