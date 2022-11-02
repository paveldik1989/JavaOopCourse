package ru.academits.paveldik.temperature.main;

import ru.academits.paveldik.temperature.controller.Controller;
import ru.academits.paveldik.temperature.model.*;
import ru.academits.paveldik.temperature.view.View;


public class Main {
    public static void main(String[] args) {
        Scale[] scales = {new CelsiusScale(), new KelvinScale(), new FahrenheitScale()};
        View view = new View();
        Model model = new Model();
        new Controller(view, model, scales);
    }
}