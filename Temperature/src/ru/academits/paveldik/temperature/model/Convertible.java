package ru.academits.paveldik.temperature.model;

public interface Convertible {
    double convert(double temperature, Scale scaleFrom, Scale scaleTo);
}