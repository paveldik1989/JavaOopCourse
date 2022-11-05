package ru.academits.paveldik.minesweeper.main;

import ru.academits.paveldik.minesweeper.controller.Controller;
import ru.academits.paveldik.minesweeper.view.View;

public class Main {
    public static void main(String[] args) {
        new Controller(new View());
    }
}