package ru.academits.paveldik.minesweeper.controller;

import ru.academits.paveldik.minesweeper.model.Cell;
import ru.academits.paveldik.minesweeper.model.Map;
import ru.academits.paveldik.minesweeper.model.Model;
import ru.academits.paveldik.minesweeper.view.View;

import java.awt.event.*;

public class Controller {
    Model model;
    View view;
    Map map;

    public Controller(Model model, View view, Map map) {
        this.model = model;
        this.view = view;
        this.map = map;

        int rowsAmount = map.getRowsAmount();
        int columnsAmount = map.getRowsAmount();
        MouseAdapter[][] mouseAdapters = new MouseAdapter[rowsAmount][columnsAmount];

        for (int i = 0; i < rowsAmount; i++) {
            for (int j = 0; j < columnsAmount; j++) {
                final int rowIndex = i;
                final int columnIndex = j;

                mouseAdapters[i][j] = new MouseAdapter() { // Не понятно как сделать через лямбду
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        if(mouseEvent.getButton() == MouseEvent.BUTTON1){
                            model.openCell(rowIndex, columnIndex);
                            setButtonsText();

                            if(model.getGameState() == Model.GameState.WIN){
                                view.displayGameOver("You win!");
                            }

                            if(model.getGameState() == Model.GameState.LOST){
                                view.displayGameOver("Game over. You lost, sucker!");
                            }
                        }else if((mouseEvent.getButton() == MouseEvent.BUTTON3)){
                            model.putFlag(rowIndex,columnIndex);
                            setButtonsText();
                        }
                    }
                };

                view.addButtonListener(i, j, mouseAdapters[i][j]);
            }
        }
    }

    private void setButtonsText() {
        int rowsAmount = map.getRowsAmount();
        int columnsAmount = map.getRowsAmount();
        Cell[][] cells = map.getCells();

        for (int i = 0; i < rowsAmount; i++) {
            for (int j = 0; j < columnsAmount; j++) {
                view.setButtonIcon(i, j, cells[i][j].toStringGame());
            }
        }
    }
}