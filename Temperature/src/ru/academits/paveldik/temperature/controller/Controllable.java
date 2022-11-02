package ru.academits.paveldik.temperature.controller;

import ru.academits.paveldik.temperature.model.Scale;

public interface Controllable {
    void setInputScale(Scale inputScale);

    void setOutputScale(Scale outputScale);

    void setInputTemperature(double inputTemperature);

    double getOutputTemperature();
}
