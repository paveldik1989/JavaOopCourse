package ru.academits.paveldik.temperature_model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Model {
    private double outputTemperature;

    public enum TemperatureScale {
        CELSIUS,
        FAHRENHEIT,
        KELVIN
    }

    public void convertTemperature(double inputTemperature, TemperatureScale inputScale, TemperatureScale outputScale) {
        switch (inputScale) {
            case CELSIUS -> {
                switch (outputScale) {
                    case CELSIUS -> outputTemperature = inputTemperature;
                    case FAHRENHEIT -> outputTemperature = inputTemperature * 1.8 + 32;
                    case KELVIN -> outputTemperature = inputTemperature + 273.15;
                }
            }

            case FAHRENHEIT -> {
                switch (outputScale) {
                    case CELSIUS -> outputTemperature = (inputTemperature - 32) / 1.8;
                    case FAHRENHEIT -> outputTemperature = inputTemperature;
                    case KELVIN -> outputTemperature = (inputTemperature - 32) / 1.8 + 273.15;
                }
            }

            case KELVIN -> {
                switch (outputScale) {
                    case CELSIUS -> outputTemperature = inputTemperature - 273.15;
                    case FAHRENHEIT -> outputTemperature = (inputTemperature - 273.15) * 1.8 + 32;
                    case KELVIN -> outputTemperature = inputTemperature;
                }
            }
        }
    }

    public double getOutputTemperature() {
        return BigDecimal.valueOf(outputTemperature)
                .setScale(2, RoundingMode.HALF_EVEN)
                .doubleValue();
    }
}