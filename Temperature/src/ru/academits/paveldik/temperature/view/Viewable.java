package ru.academits.paveldik.temperature.view;

import ru.academits.paveldik.temperature.controller.Controllable;
import ru.academits.paveldik.temperature.model.Scale;

public interface Viewable {
    void setScales(Scale[] scales);

    void setController(Controllable controller);

    void run();
}