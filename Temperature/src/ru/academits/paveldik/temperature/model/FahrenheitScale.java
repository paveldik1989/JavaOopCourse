package ru.academits.paveldik.temperature.model;

public class FahrenheitScale implements Scale {
    @Override
    public double convertToCelsius(double temperatureInCurrentScale) {
        return (temperatureInCurrentScale - 32) * 5 / 9;
    }

    @Override
    public double convertFromCelsius(double temperatureInCelsius) {
        return temperatureInCelsius * 9 / 5 + 32;
    }

    @Override
    public String toString() {
        return "Fahrenheit";
    }
}