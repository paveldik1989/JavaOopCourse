package ru.academits.paveldik.matrix;

import ru.academits.paveldik.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsAmount, int columnsAmount) {
        if (rowsAmount <= 0) {
            throw new IllegalArgumentException("Matrix rows amount must be > 0. Rows amount entered is " + rowsAmount);
        }

        if (columnsAmount <= 0) {
            throw new IllegalArgumentException("Matrix columns amount must be > 0. Columns amount entered is " + columnsAmount);
        }

        rows = new Vector[rowsAmount];

        for (int i = 0; i < rowsAmount; i++) {
            rows[i] = new Vector(columnsAmount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(Vector[] rows) {
        if (rows.length == 0) {
            throw new IllegalArgumentException("Rows amount must be > 0. Rows amount is " + rows.length);
        }

        int maxRowLength = 0;

        for (Vector row : rows) {
            if (maxRowLength < row.getSize()) {
                maxRowLength = row.getSize();
            }
        }

        this.rows = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            this.rows[i] = new Vector(maxRowLength, rows[i].getComponents());
        }
    }

    public Matrix(double[][] matrixComponents) {
        if (matrixComponents.length == 0) {
            throw new IllegalArgumentException("Matrix components array length must be > 0. Matrix components length is 0");
        }

        int maxArrayLength = 0;

        for (double[] matrixRowComponents : matrixComponents) {
            if (matrixRowComponents.length > maxArrayLength) {
                maxArrayLength = matrixRowComponents.length;
            }
        }

        if (maxArrayLength == 0) {
            throw new IllegalArgumentException("Columns amount of a matrix must be > 0. All arrays sizes are 0.");
        }

        rows = new Vector[matrixComponents.length];

        for (int i = 0; i < matrixComponents.length; i++) {
            rows[i] = new Vector(maxArrayLength, matrixComponents[i]);
        }
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsAmount() != matrix2.rows.length) {
            throw new IllegalArgumentException(
                    "Matrix1 columns amount must be equal to matrix2 rows amount. Matrix1 columns amount is "
                            + matrix1.getColumnsAmount() + ". Matrix2 rows amount is " + matrix2.rows.length);
        }

        double[][] components = new double[matrix1.rows.length][matrix2.getColumnsAmount()];

        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix2.getColumnsAmount(); j++) {
                components[i][j] = Vector.getDotProduct(matrix1.rows[i], matrix2.getColumn(j));
            }
        }

        return new Matrix(components);
    }

    private double[][] getMatrixComponents() {
        double[][] matrixComponents = new double[rows.length][getColumnsAmount()];

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < getColumnsAmount(); j++) {
                matrixComponents[i][j] = rows[i].getComponent(j);
            }
        }

        return matrixComponents;
    }

    public int getRowsAmount() {
        return rows.length;
    }

    public int getColumnsAmount() {
        return rows[0].getSize();
    }

    public Vector getRow(int rowIndex) {
        if (rowIndex < 0) {
            throw new IndexOutOfBoundsException("Row index cannot be < 0. Row index is " + rowIndex);
        }

        if (rowIndex >= rows.length) {
            throw new IndexOutOfBoundsException("Row index must be < than rows amount. Row index is " + rowIndex
                    + ". Rows amount is " + rows.length);
        }

        return new Vector(rows[rowIndex]);
    }

    public void setRow(int rowIndex, Vector row) {
        if (rowIndex < 0) {
            throw new IndexOutOfBoundsException("Row index cannot be < 0. Row index is " + rowIndex);
        }

        if (rowIndex >= rows.length) {
            throw new IndexOutOfBoundsException("Row index must be less than rows amount of the matrix. Row index is "
                    + rowIndex + ". Rows amount of the matrix " + rows.length);
        }

        if (row.getSize() != getColumnsAmount()) {
            throw new IllegalArgumentException("Vector size must be equal to the columns amount of the matrix. Vector size is "
                    + row.getSize() + ". Columns amount is " + getColumnsAmount());
        }

        rows[rowIndex] = new Vector(row);
    }

    public Vector getColumn(int columnIndex) {
        if (columnIndex < 0) {
            throw new IndexOutOfBoundsException("Column index cannot be < 0. Column index is " + columnIndex);
        }

        if (columnIndex >= getColumnsAmount()) {
            throw new IndexOutOfBoundsException("Column index must be less than columns amount of a matrix. Column index is "
                    + columnIndex + ". Columns amount is " + getColumnsAmount());
        }

        double[] components = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            components[i] = rows[i].getComponent(columnIndex);
        }

        return new Vector(components);
    }

    public void transpose() {
        Vector[] transposedRows = new Vector[getColumnsAmount()];

        for (int i = 0; i < getColumnsAmount(); i++) {
            transposedRows[i] = getColumn(i);
        }

        rows = transposedRows;
    }

    public void multiplyByScalar(double multiplier) {
        for (Vector row : rows) {
            row.multiplyByScalar(multiplier);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsAmount()) {
            throw new UnsupportedOperationException("Determinant calculation is only valid for a square matrix. Rows amount is "
                    + rows.length + ". Columns amount is " + getColumnsAmount());
        }

        double[][] matrix = getMatrixComponents();
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

    private static void swapColumns(double[][] matrix, int column1Index, int column2Index) {
        for (double[] row : matrix) {
            double temp = row[column1Index];
            row[column1Index] = row[column2Index];
            row[column2Index] = temp;
        }
    }

    private static void subtractRow(double[][] matrix, int rowIndex) {
        for (int i = rowIndex + 1; i < matrix.length; i++) {
            double coefficient = matrix[i][rowIndex] / matrix[rowIndex][rowIndex];

            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] -= coefficient * matrix[rowIndex][j];
            }
        }
    }

    public Vector multiplyByVector(Vector vector) {
        if (getColumnsAmount() != vector.getSize()) {
            throw new IllegalArgumentException("Matrix columns amount must be equal to vector size. Columns amount is "
                    + getColumnsAmount() + ". Vector size is " + vector.getSize());
        }

        double[] vectorComponents = new double[getRowsAmount()];

        for (int i = 0; i < rows.length; i++) {
            vectorComponents[i] = Vector.getDotProduct(rows[i], vector);
        }

        return new Vector(vectorComponents);
    }

    public void add(Matrix matrix) {
        checkSizes(matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        matrix1.checkSizes(matrix2);

        Matrix sum = new Matrix(matrix1);
        sum.add(matrix2);
        return sum;
    }

    public void subtract(Matrix matrix) {
        checkSizes(matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        matrix1.checkSizes(matrix2);

        Matrix difference = new Matrix(matrix1);
        difference.subtract(matrix2);
        return difference;
    }

    private void checkSizes(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsAmount() != matrix.getColumnsAmount()) {
            throw new IllegalArgumentException("Operation is only valid for matrices of equivalent size. Rows amount of matrix1 is "
                    + rows.length + "Rows amount of matrix2 is " + matrix.rows.length + ". Columns amount of matrix1 is "
                    + getColumnsAmount() + ". Columns amount of matrix2 is " + matrix.getColumnsAmount());
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        for (Vector row : rows) {
            stringBuilder.append(row).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append('}');

        return stringBuilder.toString();
    }
}