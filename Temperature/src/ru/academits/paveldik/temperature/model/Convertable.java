package ru.academits.paveldik.temperature.model;

public interface Convertable {
    double convert(double temperature, Scale scaleFrom, Scale scaleTo);
}