package ru.academits.paveldik.temperature.model;

public class KelvinScale implements Scale {
    @Override
    public double convertToCelsius(double temperatureInCurrentScale) {
        return temperatureInCurrentScale - 273.15;
    }

    @Override
    public double convertFromCelsius(double temperatureInCelsius) {
        return temperatureInCelsius + 273.15;
    }

    @Override
    public String toString() {
        return "Kelvin";
    }
}