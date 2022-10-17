package ru.academits.paveldik.temperature_controller;

import ru.academits.paveldik.temperature_model.Model;
import ru.academits.paveldik.temperature_view.View;

public class Controller {
    private Model.TemperatureScale inputScale = Model.TemperatureScale.CELSIUS;
    private Model.TemperatureScale outputScale = Model.TemperatureScale.CELSIUS;

    public Controller(View view, Model model) { // Почему нельзя поместить строки 7 и 8 внутрь конструктора?
        view.addInputScaleListener(event -> {// Почему лямда может работать с полями, но не может с локальной не final переменной?
            switch (view.getInputScaleIndex()) {
                case 0 -> inputScale = Model.TemperatureScale.CELSIUS;
                case 1 -> inputScale = Model.TemperatureScale.FAHRENHEIT;
                case 2 -> inputScale = Model.TemperatureScale.KELVIN;
            }
        });

        view.addOutputScaleListener(event -> {
            switch (view.getOutputScaleIndex()) {
                case 0 -> outputScale = Model.TemperatureScale.CELSIUS;
                case 1 -> outputScale = Model.TemperatureScale.FAHRENHEIT;
                case 2 -> outputScale = Model.TemperatureScale.KELVIN;
            }
        });

        view.addConvertListener(event -> {
            double inputTemperature;

// На лекции вроде бы говорили, что View должен возвращать подготовленное значение. Тогда try-catch должен быть во View?
// Но тогда придется возвращать какое то значение (0 например), что наверное неправильного для некорректного ввода.
            try {
                inputTemperature = view.getInputTemperature();
                model.convertTemperature(inputTemperature, inputScale, outputScale);
                view.setOutputTemperature(model.getOutputTemperature());
            } catch (NumberFormatException err) {
                view.displayErrorMessage("You need to enter a number");
            }
        });
    }
}