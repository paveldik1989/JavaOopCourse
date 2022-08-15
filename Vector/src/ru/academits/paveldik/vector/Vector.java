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
        components = Arrays.copyOf(vector.components, vector.getSize());
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
        vector1.getSum(vector2);
        return vector1;
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.getSize(), vector2.getSize());
        double dotProduct = 0;

        for (int i = 0; i < minSize; i++) {
            dotProduct += vector1.components[i] * vector2.components[i];
        }

        return dotProduct;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        vector1.getDifference(vector2);
        return vector1;
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

        if (vector.getSize() == getSize()) {
            for (int i = 0; i < getSize(); i++) {
                if (vector.components[i] != components[i]) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        for (int i = 0; i < getSize(); i++) {
            hash = prime * hash + Double.hashCode(components[i]);
        }

        return hash;
    }

    public int getSize() {
        return components.length;
    }

    public void getSum(Vector vector) {
        int maxSize = Math.max(getSize(), vector.getSize());
        double[] componentsSum = new double[maxSize];

        for (int i = 0; i < maxSize; i++) {
            if (i < getSize()) {
                componentsSum[i] = components[i];
            }

            if (i < vector.getSize()) {
                componentsSum[i] += vector.components[i];
            }
        }

        components = componentsSum;
    }

    public void getDifference(Vector vector) {
        int maxSize = Math.max(getSize(), vector.getSize());
        double[] componentsDifference = new double[maxSize];

        for (int i = 0; i < maxSize; i++) {
            if (i < getSize()) {
                componentsDifference[i] = components[i];
            }

            if (i < vector.getSize()) {
                componentsDifference[i] -= vector.components[i];
            }
        }

        components = componentsDifference;
    }

    public void multiplyByScalar(double multiplier) {
        for (int i = 0; i < getSize(); i++) {
            components[i] *= multiplier;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double quadraticSum = 0;

        for (int i = 0; i < getSize(); i++) {
            quadraticSum += components[i] * components[i];
        }

        return Math.sqrt(quadraticSum);
    }

    public double getComponent(int index) {
        return components[index];
    }

    public void setComponent(int index, double component) {
        components[index] = component;
    }
}