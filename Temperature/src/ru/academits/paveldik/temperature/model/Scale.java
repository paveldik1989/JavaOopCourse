package ru.academits.paveldik.temperature.model;

public interface Scale {
    double convertToCelsius(double temperatureInCurrentScale);

    double convertFromCelsius(double temperatureInCelsius);
}