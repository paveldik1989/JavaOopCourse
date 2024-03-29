package ru.academits.paveldik.temperature.model;

public class Model implements Convertible {
    @Override
    public double convert(double temperature, Scale scaleFrom, Scale scaleTo) {
        double temperatureInCelsius = scaleFrom.convertToCelsius(temperature);
        return scaleTo.convertFromCelsius(temperatureInCelsius);
    }
}