package ru.academits.paveldik.minesweeper.main;

import ru.academits.paveldik.minesweeper.controller.Controller;
import ru.academits.paveldik.minesweeper.model.Model;
import ru.academits.paveldik.minesweeper.view.View;

public class Main {
    public static void main(String[] args) {
        int rowsAmount = 20;
        int columnsAmount = 20;
        int bombsAmount = 10;

        Model model = new Model(rowsAmount, columnsAmount, bombsAmount);
        View view = new View(model.getMap());
        new Controller(model, view, model.getMap());
    }
}