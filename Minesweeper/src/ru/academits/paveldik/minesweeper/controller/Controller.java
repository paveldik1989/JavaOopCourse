package ru.academits.paveldik.minesweeper.controller;

import ru.academits.paveldik.minesweeper.model.Cell;
import ru.academits.paveldik.minesweeper.model.Map;
import ru.academits.paveldik.minesweeper.model.Game;
import ru.academits.paveldik.minesweeper.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    public static final int DEFAULT_ROWS_AMOUNT = 9;
    public static final int DEFAULT_COLUMNS_AMOUNT = 9;
    public static final int DEFAULT_TOTAL_BOMBS_AMOUNT = 10;
    int gameDuration = 0;

    Game game;
    View view;
    Map map;
    Cell[][] cells;
    Timer timer = new Timer();
    boolean isStarted = false;

    public Controller(View view) {
        this.view = view;
        game = new Game(DEFAULT_ROWS_AMOUNT, DEFAULT_COLUMNS_AMOUNT, DEFAULT_TOTAL_BOMBS_AMOUNT);
        map = game.getMap();
        cells = map.getCells();

        view.setRowsAmount(map.getRowsAmount());
        view.setColumnsAmount(map.getColumnsAmount());
        view.setTotalBombsAmount(map.getTotalBombsAmount());
        view.setController(this);
        view.run();
    }

    public void leftClick(int rowIndex, int columnIndex) {
        if (!isStarted) {
            isStarted = true;

            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    view.setStopwatch(gameDuration);
                    gameDuration++;
                }
            }, 0, 1000);
        }

        if (cells[rowIndex][columnIndex].getStatus() == Cell.CellStatus.FLAG) {
            return;
        }

        game.openCell(rowIndex, columnIndex);
        updateGameField();
        view.setRemainingBombsCounter(game.getRemainingBombsAmount());
        checkGameState();
    }

    public void middleClick(int rowIndex, int columnIndex) {
        game.openAround(rowIndex, columnIndex);
        updateGameField();
        view.setRemainingBombsCounter(game.getRemainingBombsAmount());
        checkGameState();
    }

    public void rightClick(int rowIndex, int columnIndex) {
        game.putOrRemoveFlag(rowIndex, columnIndex);
        updateGameField();
        view.setRemainingBombsCounter(game.getRemainingBombsAmount());
    }

    public void checkGameState() {
        if (game.getGameState() == Game.GameState.WIN) {
            timer.cancel();
            view.setSmileWin();
        }

        if (game.getGameState() == Game.GameState.LOST) {
            timer.cancel();
            view.setSmileLost();
        }
    }

    public void newGame(int rowsAmount, int columnsAmount, int totalBombsAmount) {
        timer.cancel();
        timer = new Timer();
        gameDuration = 0;
        isStarted = false;

        game = new Game(rowsAmount, columnsAmount, totalBombsAmount);
        map = game.getMap();
        cells = map.getCells();

        view.closeFrame();
        view.setRowsAmount(map.getRowsAmount());
        view.setColumnsAmount(map.getColumnsAmount());
        view.setTotalBombsAmount(map.getTotalBombsAmount());
        view.setRemainingBombsCounter(game.getRemainingBombsAmount());
        view.run();
    }

    public void restartGame() {
        timer.cancel();
        timer = new Timer();
        gameDuration = 0;
        isStarted = false;

        game = new Game(map.getRowsAmount(), map.getColumnsAmount(), map.getTotalBombsAmount());
        map = game.getMap();
        cells = map.getCells();

        updateGameField();
        view.setStopwatch(0);
        view.setRemainingBombsCounter(game.getRemainingBombsAmount());
        view.setSmileInProcess();
    }

    public void setBeginner(){
        newGame(DEFAULT_ROWS_AMOUNT, DEFAULT_COLUMNS_AMOUNT, DEFAULT_TOTAL_BOMBS_AMOUNT);
    }

    public void setIntermediate(){
        newGame(16, 16, 40);
    }

    public void setExpert(){
        newGame(16, 30, 99);
    }

    public void setCustom(){
       view.displayCustom("Введите чето");
    }

    public void updateGameField() {
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