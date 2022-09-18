package ru.academits.paveldik.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Vector size must be > 0. Vector size entered is " + size);
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Vector size must be > 0. Vector size entered is " + components.length);
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int size, double[] components) { // Из задания не ясно что делать в случае n<components.length?
        if (size <= 0) {
            throw new IllegalArgumentException("Vector size must be > 0. Vector size entered is " + size);
        }

        this.components = Arrays.copyOf(components, size);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector vectorSum = new Vector(vector1);
        vectorSum.add(vector2);
        return vectorSum;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector vectorDifference = new Vector(vector1);
        vectorDifference.subtract(vector2);
        return vectorDifference;
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.components.length, vector2.components.length);
        double dotProduct = 0;

        for (int i = 0; i < minSize; i++) {
            dotProduct += vector1.components[i] * vector2.components[i];
        }

        return dotProduct;
    }

    public double[] getComponents() {
        return Arrays.copyOf(components, components.length);
    }

    public int getSize() {
        return components.length;
    }

    public double getComponent(int index) {
        return components[index];
    }

    public void setComponent(int index, double component) {
        components[index] = component;
    }

    public void multiplyByScalar(double multiplier) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= multiplier;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double e : components) {
            sum += e * e;
        }

        return Math.sqrt(sum);
    }

    public void add(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        for (double e : components) {
            stringBuilder.append(e).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) o;
        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }
}