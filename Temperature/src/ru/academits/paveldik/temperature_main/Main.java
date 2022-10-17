package ru.academits.paveldik.temperature_main;

import ru.academits.paveldik.temperature_controller.Controller;
import ru.academits.paveldik.temperature_model.Model;
import ru.academits.paveldik.temperature_view.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        Model model = new Model();
        new Controller(view, model);
        // Если написать так: Controller controller = new Controller(view, model);
        // То выдается приедупреждение, что переменная не используется.
        // Переписал так, вероятно через анонимный класс, насколько так делать правильно? Если надо по другому, то как?

        view.setVisible(true);
    }
}