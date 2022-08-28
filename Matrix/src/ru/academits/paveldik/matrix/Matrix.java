package ru.academits.paveldik.matrix;

import ru.academits.paveldik.vector.Vector;

public class Matrix {
    Vector[] vectorsArray;

    public Matrix(int columnSize, int rowSize) {
        if (columnSize <= 0) {
            throw new IllegalArgumentException("Matrix column size must be > 0. Column size entered is " + columnSize);
        }

        if (rowSize <= 0) {
            throw new IllegalArgumentException("Matrix row size must be > 0. Row size entered is " + rowSize);
        }

        Vector[] vectorsArray = new Vector[columnSize];

        for (int i = 0; i < columnSize; i++) {
            vectorsArray[i] = new Vector(rowSize);
        }

        this.vectorsArray = vectorsArray;
    }

    public Matrix(Matrix matrix) {
        Matrix matrix1 = new Matrix(matrix.vectorsArray);
        vectorsArray = matrix1.vectorsArray;
    }

    public Matrix(Vector[] vectorsArray) {
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("Vectors array length must be > 0. Vectors array length is " +
                    vectorsArray.length);
        }

        this.vectorsArray = new Vector[vectorsArray.length];

        for (int i = 0; i < vectorsArray.length; i++) {
            this.vectorsArray[i] = new Vector(vectorsArray[i]);
        }
    }

    public Matrix(double[][] matrixComponents) {
        if (matrixComponents.length == 0) {
            throw new IllegalArgumentException("Matrix components array length must be > 0. Matrix components length is "
                    + matrixComponents.length);
        }

        int zeroLengthArraysNumber = 0;
        for (double[] e : matrixComponents) {
            if (e.length == 0) {
                zeroLengthArraysNumber++;
            }
        }

        if (zeroLengthArraysNumber == matrixComponents.length) {
            throw new IllegalArgumentException("Row size of a matrix must be > 0. All arrays sizes is 0");
        }

        int maxArrayLength = 0;

        for (double[] matrixComponent : matrixComponents) {
            if (matrixComponent.length > maxArrayLength) {
                maxArrayLength = matrixComponent.length;
            }
        }

        vectorsArray = new Vector[matrixComponents.length];
        for (int i = 0; i < matrixComponents.length; i++) {
            vectorsArray[i] = new Vector(maxArrayLength, matrixComponents[i]);
        }
    }

    private static void swapColumns(double[][] matrix, int column1Index, int column2Index) {
        for (double[] e : matrix) {
            double temp = e[column1Index];
            e[column1Index] = e[column2Index];
            e[column2Index] = temp;
        }
    }

    private static void subtractRow(double[][] matrix, int rowIndex) { // Возможно не удачное название
        for (int i = rowIndex + 1; i < matrix.length; i++) {
            double coefficient = matrix[i][rowIndex] / matrix[rowIndex][rowIndex];

            for (int j = 0; j < matrix.length; j++) { // Можно сократить начало.
                matrix[i][j] -= coefficient * matrix[rowIndex][j];
            }
        }
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        Matrix matrixSum = new Matrix(matrix1);
        matrixSum.add(matrix2);
        return matrixSum;
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        Matrix matrixSubtract = new Matrix(matrix1);
        matrixSubtract.subtract(matrix2);
        return matrixSubtract;
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.vectorsArray[0].getSize() != matrix2.vectorsArray.length) {
            throw new IllegalArgumentException("Matrix 1 row size must be equal to matrix 2 column size" + "Matrix 1 row size is " +
                    matrix1.vectorsArray[0].getSize() + "Matrix 2 column size is " + matrix2.vectorsArray.length);
        }

        Vector[] multipliedMatrix = new Vector[matrix2.getColumnSize()];

        for (int i = 0; i < matrix2.getColumnSize(); i++) {
            multipliedMatrix[i] = matrix1.multiplyByVector(matrix2.getColumn(i));
        }

        return new Matrix(multipliedMatrix);
    }

    private double[][] getMatrixComponents() {
        double[][] matrixComponents = new double[vectorsArray.length][vectorsArray[0].getSize()];

        for (int i = 0; i < vectorsArray.length; i++) {
            for (int j = 0; j < vectorsArray[0].getSize(); j++) {
                matrixComponents[i][j] = vectorsArray[i].getComponent(j);
            }
        }

        return matrixComponents;
    }

    public int getColumnSize() {
        return vectorsArray.length;
    }

    public int getRowSize() {
        return vectorsArray[0].getSize();
    }

    public Vector getRow(int rowNumber) {
        return vectorsArray[rowNumber];
    }

    public void setRow(int rowNumber, Vector vector) {
        if (rowNumber < 0) {
            throw new IllegalArgumentException("Row number cannot be < 0. Row number is" + rowNumber);
        }

        if (rowNumber >= vectorsArray[0].getSize()) {
            throw new IllegalArgumentException("Row number must be less than row size of the matrix. Row number is"
                    + rowNumber + "Row size of the matrix" + vectorsArray[0].getSize());
        }

        if (vector.getSize() != vectorsArray[0].getSize()) {
            throw new IllegalArgumentException("Vector size must be equal to the row length of the matrix. Vector size is "
                    + vector.getSize() + "Row size is" + vectorsArray[0].getSize());
        }
        vectorsArray[rowNumber] = new Vector(vector);
    }

    public Vector getColumn(int columnNumber) {
        if (columnNumber < 0) {
            throw new IllegalArgumentException("Column number cannot be < 0. Column number is" + columnNumber);
        }

        if (columnNumber >= vectorsArray[0].getSize()) {
            throw new IllegalArgumentException("Column number must be less than row size of a matrix. Row size is "
                    + vectorsArray[0].getSize() + "Column number is " + columnNumber);
        }

        double[] components = new double[vectorsArray.length];

        for (int i = 0; i < vectorsArray.length; i++) {
            components[i] = vectorsArray[i].getComponent(columnNumber);
        }

        return new Vector(components);
    }

    public void transpose() {
        Vector[] transposedVectorsArray = new Vector[vectorsArray[0].getSize()];

        for (int i = 0; i < vectorsArray[0].getSize(); i++) {
            transposedVectorsArray[i] = getColumn(i);
        }

        vectorsArray = transposedVectorsArray;
    }

    public void multiplyByScalar(double multiplier) {
        for (Vector vector : vectorsArray) {
            vector.multiplyByScalar(multiplier);
        }
    }

    public double getDeterminant() {
        if (vectorsArray.length != vectorsArray[0].getSize()) {
            throw new IllegalArgumentException("Determinant calculation is only valid for a square matrix. " +
                    "Column size is " + vectorsArray.length + ". Row size is" + vectorsArray[0].getSize());
        }

        double[][] matrix = this.getMatrixComponents();
        int sign = 1;
        double epsilon = 1E-10;

        // Приведение к верхнетреугольному виду
        for (int i = 0; i < matrix.length - 1; i++) {
            if (Math.abs(matrix[i][i]) < epsilon) {
                int j = i;

                while (Math.abs(matrix[i][j]) < epsilon) {
                    j++;

                    if (j == matrix.length) {
                        return 0;
                    }
                }

                swapColumns(matrix, i, j);
                sign *= -1;
            }

            subtractRow(matrix, i);
        }

        double determinant = 1;

        for (int i = 0; i < matrix.length; i++) {
            determinant *= matrix[i][i];
        }

        return sign * determinant;
    }

    public Vector multiplyByVector(Vector vector) {
        if (vectorsArray[0].getSize() != vector.getSize()) {
            throw new IllegalArgumentException("Matrix row size must be equal to vector size" + "Row size is" +
                    vectorsArray[0].getSize() + "Vector size is" + vector.getSize());
        }

        double[] multipliedComponents = new double[vectorsArray[0].getSize()];
        for (int i = 0; i < vectorsArray.length; i++) {
            double multipliedComponent = 0;

            for (int j = 0; j < vectorsArray[0].getSize(); j++) {
                multipliedComponent += vectorsArray[i].getComponent(j) * vector.getComponent(j);
            }

            multipliedComponents[i] = multipliedComponent;
        }

        return new Vector(multipliedComponents);
    }

    public void add(Matrix matrix) {
        if (vectorsArray.length != matrix.vectorsArray.length || vectorsArray[0].getSize() != matrix.vectorsArray[0].getSize()) {
            throw new IllegalArgumentException("Sum can be get only for matrices of equivalent size" +
                    "Column size of matrix1 is" + vectorsArray.length + "Column size of matrix2 is" +
                    matrix.vectorsArray.length + "Row size of matrix1 is" + vectorsArray[0].getSize() +
                    "Row size of matrix2 is" + matrix.vectorsArray[0].getSize());
        }

        double[][] matrixComponents1 = this.getMatrixComponents();
        double[][] matrixComponents2 = matrix.getMatrixComponents();

        for (int i = 0; i < matrixComponents1.length; i++) {
            for (int j = 0; j < matrixComponents1[0].length; j++) {
                vectorsArray[i].setComponent(j, matrixComponents1[i][j] + matrixComponents2[i][j]);
            }
        }
    }

    public void subtract(Matrix matrix) {
        if (vectorsArray.length != matrix.vectorsArray.length || vectorsArray[0].getSize() != matrix.vectorsArray[0].getSize()) {
            throw new IllegalArgumentException("Subtract can be get only for matrices of equivalent size" +
                    "Column size of matrix1 is" + vectorsArray.length + "Column size of matrix2 is" +
                    matrix.vectorsArray.length + "Row size of matrix1 is" + vectorsArray[0].getSize() +
                    "Row size of matrix2 is" + matrix.vectorsArray[0].getSize());
        }

        Matrix matrixToSubtract = new Matrix(matrix);
        matrixToSubtract.multiplyByScalar(-1);
        this.add(matrixToSubtract);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        for (Vector e : vectorsArray) {
            stringBuilder.append(e).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append('}');

        return stringBuilder.toString();
    }
}
