package ru.academits.paveldik.temperature.controller;

import ru.academits.paveldik.temperature.model.Convertible;
import ru.academits.paveldik.temperature.model.Scale;
import ru.academits.paveldik.temperature.view.Viewable;

public class Controller implements Controllable {
    private final Convertible model;
    private Scale inputScale;
    private Scale outputScale;
    private double inputTemperature;

    @Override
    public void setInputScale(Scale inputScale) {
        this.inputScale = inputScale;
    }

    @Override
    public void setOutputScale(Scale outputScale) {
        this.outputScale = outputScale;
    }

    @Override
    public void setInputTemperature(double inputTemperature) {
        this.inputTemperature = inputTemperature;
    }

    @Override
    public double getOutputTemperature() {
        return model.convert(inputTemperature, inputScale, outputScale);
    }

    public Controller(Viewable view, Convertible model, Scale[] scales) {
        inputScale = scales[0];
        outputScale = scales[0];
        this.model = model;

        view.setController(this);
        view.setScales(scales);
        view.run();
    }
}