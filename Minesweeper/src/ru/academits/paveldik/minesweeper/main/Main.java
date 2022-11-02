package ru.academits.paveldik.minesweeper.main;

import ru.academits.paveldik.minesweeper.controller.Controller;
import ru.academits.paveldik.minesweeper.model.Game;
import ru.academits.paveldik.minesweeper.view.View;

public class Main {
    public static void main(String[] args) {
        int rowsAmount = 3;
        int columnsAmount = 50;
        int bombsAmount = 10;

        Game game = new Game(rowsAmount, columnsAmount, bombsAmount);
        View view = new View(game.getMap());
        new Controller(game, view);
    }
}