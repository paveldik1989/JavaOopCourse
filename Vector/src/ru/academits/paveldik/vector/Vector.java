package ru.academits.paveldik.vector;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Vector size must be > 0");
        }

        components = new double[n];

        for (int i = 0; i < n; i++) {
            components[i] = 0;
        }
    }

    public Vector(Vector vector) {
        components = new double[vector.getSize()];

        System.arraycopy(vector.components, 0, components, 0, vector.getSize());
    }

    public Vector(double[] components) {
        if (components.length <= 0) {
            throw new IllegalArgumentException("Vector size must be > 0");
        }

        this.components = new double[components.length];
        System.arraycopy(components, 0, this.components, 0, components.length);
    }

    public Vector(int n, double[] components) { // Из задания не ясно что делать в случае n<components.length?
        if (n <= 0) {
            throw new IllegalArgumentException("Vector size must be > 0");
        }

        this.components = new double[n];

        for (int i = 0; i < n; i++) {
            if (i < components.length) {
                this.components[i] = components[i];
            } else {
                this.components[i] = 0;
            }
        }
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.getSize(), vector2.getSize());
        double[] vectorSum = new double[maxSize];

        for (int i = 0; i < maxSize; i++) {

            if (i < vector1.getSize()) {
                vectorSum[i] = vector1.components[i];
            }

            if (i < vector2.getSize()) {
                vectorSum[i] += vector2.components[i];
            }
        }

        return new Vector(vectorSum);
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.getSize(), vector2.getSize());
        double dotProduct = 0;

        for (int i = 0; i < minSize; i++) {
            dotProduct += vector1.getComponent(i) * vector2.getComponent(i);
        }

        return dotProduct;
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
                if (vector.getComponent(i) != getComponent(i)) {
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
            hash = prime * hash + Double.hashCode(getComponent(i));
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

    public Vector getDifference(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.getSize(), vector2.getSize());
        double[] componentsDifference = new double[maxSize];

        for (int i = 0; i < maxSize; i++) {

            if (i < vector1.getSize()) {
                componentsDifference[i] = components[i];
            }

            if (i < vector2.getSize()) {
                componentsDifference[i] -= vector2.components[i];
            }
        }

        return new Vector(componentsDifference);
    }

    public void getScalarMultiplication(double multiplier) {
        double[] componentsMultiplied = new double[getSize()];

        for (int i = 0; i < getSize(); i++) {
            componentsMultiplied[i] = multiplier * components[i];
        }

        components = componentsMultiplied;
    }

    public void getReversed() {
        double[] componentsReversed = new double[getSize()];

        for (int i = 0; i < getSize(); i++) {
            componentsReversed[i] = -components[i];
        }

        components = componentsReversed;
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