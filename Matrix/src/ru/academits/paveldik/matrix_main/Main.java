package ru.academits.paveldik.matrix_main;

import ru.academits.paveldik.matrix.Matrix;

import ru.academits.paveldik.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(4, 4);
        System.out.println("Конструктор 1: " + matrix1);

        Matrix matrix2 = new Matrix(matrix1);
        System.out.println("Конструктор 2: " + matrix2);

        Matrix matrix3 = new Matrix(new Vector[]{new Vector(new double[]{1, 1}), new Vector(new double[]{2, 2})});
        System.out.println("Конструктор 3: " + matrix3);

        double[][] components1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };

        Matrix matrix4 = new Matrix(components1);
        System.out.println("Конструктор 4: " + matrix4);

        matrix4.transpose();
        System.out.println("Транспонированная матрица 4: " + matrix4);

        double[][] components2 = {
                {1, 1, 1},
                {0, 1, 1},
                {0, 0, 1}
        };

        Matrix matrix5 = new Matrix(components2);
        System.out.println("Матрица 5: " + matrix5);

        matrix5.getDeterminant();
        System.out.println("Дискриминант матрицы 5: " + matrix5.getDeterminant());

        Matrix matrix6 = new Matrix(matrix5);
        System.out.println("Матрица 6: " + matrix6);

        matrix5.add(matrix6);
        System.out.println("Матрица 5 + 6: " + matrix5);

        matrix5.subtract(matrix6);
        System.out.println("Матрица 5 - 6: " + matrix5);

        System.out.println("Умножение матриц 5 и 6" + Matrix.getProduct(matrix5, matrix6));

        matrix5.multiplyByScalar(5);
        System.out.println("Матрица 6 после умножение матрицы на 5: " + matrix6);

        Vector vector1 = new Vector(new double[]{1, 1, 1});
        System.out.println("Вектор 1: " + vector1);
        System.out.println("Умножение матрицы 5 на вектор 1: " + matrix5.multiplyByVector(vector1));
    }
}