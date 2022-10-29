package ru.academits.paveldik.minesweeper.model;

public class Model {
    private final int rowsAmount;
    private final int columnsAmount;
    private final int totalBombsAmount;

    private final Map map;
    private final Cell[][] cells;

    private int closedCellsAmount;

    private GameState gameState = GameState.IN_PROCESS;

    public Model(int rowsAmount, int columnsAmount, int totalBombsAmount) {
        this.rowsAmount = rowsAmount;
        this.columnsAmount = columnsAmount;
        this.totalBombsAmount = totalBombsAmount;

        Map map = new Map(rowsAmount, columnsAmount, totalBombsAmount);
        this.map = map;

        closedCellsAmount = map.getRowsAmount() * map.getColumnsAmount();
        cells = map.getCells();
    }

    public Map getMap() {
        return map;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void openCell(int rowIndex, int columnIndex) {
        Cell currentCell = cells[rowIndex][columnIndex];

        if (currentCell.isOpen()) {
            return;
        }

        if (currentCell.getMapState() == 9) {
            gameState = GameState.LOST;

            currentCell.setOpen(true);
            showBombs();
            System.out.println("Game over. You lost, sucker!");
            return;
        }

        closedCellsAmount--;
        currentCell.setOpen(true);
        checkWinning();

        if (currentCell.getMapState() == 0) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i == 0 && j == 0 || rowIndex + i < 0 || rowIndex + i == rowsAmount || columnIndex + j < 0 || columnIndex + j == columnsAmount) {
                        continue;
                    }

                    if (!cells[rowIndex + i][columnIndex + j].isOpen()) {
                        openCell(rowIndex + i, columnIndex + j);
                    }
                }
            }
        }
    }

    public void putFlag(int rowIndex, int columnIndex) {
        Cell cell = cells[rowIndex][columnIndex];

        if (cell.isOpen() || cell.getClosedState() == 1) {
            return;
        }

        cell.setClosedState(1);
    }

    private void checkWinning() {
        if (closedCellsAmount == totalBombsAmount) {
            gameState = GameState.WIN;
            System.out.println("You win!");
        }
    }

    public void showBombs() {
        for (int i = 0; i < map.getRowsAmount(); i++) {
            for (int j = 0; j < map.getRowsAmount(); j++) {
                Cell cell = cells[i][j];

                if (cell.isOpen()) {
                    continue;
                }

                if (cell.getMapState() == 9 && cell.getClosedState() == 0) {
                    cell.setClosedState(2);
                    continue;
                }

                if (cell.getClosedState() == 1 && cell.getMapState() != 9) {
                    cell.setClosedState(3);
                }
            }
        }
    }

    public enum GameState {
        IN_PROCESS,
        LOST,
        WIN
    }
}