package ru.academits.paveldik.temperature.controller;

import ru.academits.paveldik.temperature.model.Convertable;
import ru.academits.paveldik.temperature.model.Scale;
import ru.academits.paveldik.temperature.view.Viewable;

public class Controller {
    private Scale inputScale;
    private Scale outputScale;

    public Controller(Viewable view, Convertable model, Scale[] scales) {
        inputScale = scales[0];
        outputScale = scales[0];

        view.setScales(scales);
        view.run();

        view.addInputScaleListener(event -> inputScale = view.getInputScale());
        view.addOutputScaleListener(event -> outputScale = view.getOutputScale());
        view.addConvertListener(event -> {
            double inputTemperature;

            try {
                inputTemperature = view.getInputTemperature();
                view.setOutputTemperature(model.convert(inputTemperature, inputScale, outputScale));
            } catch (NumberFormatException err) {
                view.displayErrorMessage("You need to enter a number!");
            }
        });
    }
}