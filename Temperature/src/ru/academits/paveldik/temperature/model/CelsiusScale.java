package ru.academits.paveldik.temperature.model;

public class CelsiusScale implements Scale {
    @Override
    public double convertToCelsius(double temperatureInCurrentScale) {
        return temperatureInCurrentScale;
    }

    @Override
    public double convertFromCelsius(double temperatureInCelsius) {
        return temperatureInCelsius;
    }

    @Override
    public String toString() {
        return "Celsius";
    }
}