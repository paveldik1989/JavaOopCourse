package ru.academits.paveldik.temperature.view;

import ru.academits.paveldik.temperature.model.Scale;

import java.awt.event.ActionListener;

public interface Viewable {
    void setScales(Scale[] scales);

    void run();

    void addInputScaleListener(ActionListener listenForInputScale);

    Scale getInputScale();

    void addOutputScaleListener(ActionListener listenForOutputScale);

    Scale getOutputScale();

    void addConvertListener(ActionListener listenForConvertButton);

    double getInputTemperature();

    void setOutputTemperature(double outputTemperature);

    void displayErrorMessage(String errorMessage);
}