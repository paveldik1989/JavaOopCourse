package ru.academits.paveldik.minesweeper.controller;

import ru.academits.paveldik.minesweeper.model.Cell;
import ru.academits.paveldik.minesweeper.model.Map;
import ru.academits.paveldik.minesweeper.model.Game;
import ru.academits.paveldik.minesweeper.view.View;

import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    Game game;
    View view;
    Map map;

    public Controller(Game game, View view) {
        this.game = game;
        this.view = view;
        this.map = game.getMap();

        final boolean[] isStarted = {false};
        Cell[][] cells = map.getCells();

        int rowsAmount = map.getRowsAmount();
        int columnsAmount = map.getColumnsAmount();

        Timer timer = new Timer();
        MouseAdapter[][] mouseAdapters = new MouseAdapter[rowsAmount][columnsAmount];

        for (int i = 0; i < rowsAmount; i++) {
            for (int j = 0; j < columnsAmount; j++) {
                final int rowIndex = i;
                final int columnIndex = j;

                mouseAdapters[i][j] = new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                            if (!isStarted[0]){
                                isStarted[0] = true;

                                timer.scheduleAtFixedRate(new TimerTask() {
                                    int seconds = 0;

                                    public void run() {
                                        view.setTimer(seconds);
                                        seconds++;
                                    }
                                }, 0, 1000);
                            }

                            if (cells[rowIndex][columnIndex].getStatus() == Cell.CellStatus.FLAG) {
                                return;
                            }

                            game.openCell(rowIndex, columnIndex);
                            updateButtonsIcons();
                            view.setRemainingBombsCounter(game.getTotalBombsAmount() - game.getFlagsAmount());

                            if (game.getGameState() == Game.GameState.WIN) {
                                timer.cancel();
                                view.setSmileWin();
                                view.displayGameOver("You win!");
                            }

                            if (game.getGameState() == Game.GameState.LOST) {
                                timer.cancel();
                                view.setSmileLost();
                                view.displayGameOver("Game over. You lost, sucker!");
                            }
                        } else if (mouseEvent.getButton() == MouseEvent.BUTTON2) {
                            game.openAround(rowIndex, columnIndex);
                            updateButtonsIcons();
                            view.setRemainingBombsCounter(game.getTotalBombsAmount() - game.getFlagsAmount());

                            if (game.getGameState() == Game.GameState.WIN) {
                                timer.cancel();
                                view.setSmileWin();
                                view.displayGameOver("You win!");
                            }

                            if (game.getGameState() == Game.GameState.LOST) {
                                timer.cancel();
                                view.setSmileLost();
                                view.displayGameOver("Game over. You lost, sucker!");
                            }
                        } else if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                            game.putOrRemoveFlag(rowIndex, columnIndex);
                            updateButtonsIcons();
                            view.setRemainingBombsCounter(game.getTotalBombsAmount() - game.getFlagsAmount());
                        }
                    }
                };

                view.addButtonListener(i, j, mouseAdapters[i][j]);
            }
        }
    }

    private void updateButtonsIcons() {
        int rowsAmount = map.getRowsAmount();
        int columnsAmount = map.getColumnsAmount();
        Cell[][] cells = map.getCells();

        for (int i = 0; i < rowsAmount; i++) {
            for (int j = 0; j < columnsAmount; j++) {
                switch (cells[i][j].getStatus()) {
                    case EMPTY -> view.setEmpty(i, j);
                    case ONE -> view.setOne(i, j);
                    case TWO -> view.setTwo(i, j);
                    case THREE -> view.setThree(i, j);
                    case FOUR -> view.setFour(i, j);
                    case FIVE -> view.setFive(i, j);
                    case SIX -> view.setSix(i, j);
                    case SEVEN -> view.setSeven(i, j);
                    case EIGHT -> view.setEight(i, j);
                    case EXPLOSION -> view.setExplosion(i, j);
                    case FLAG -> view.setFlag(i, j);
                    case BOMB -> view.setBomb(i, j);
                    case WRONG_FLAG -> view.setWrongFlag(i, j);
                    default -> view.setClosed(i, j);
                }
            }
        }
    }
}