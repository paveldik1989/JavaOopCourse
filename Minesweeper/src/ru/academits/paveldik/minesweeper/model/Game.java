package ru.academits.paveldik.minesweeper.model;

public class Game {
    private final int rowsAmount;
    private final int columnsAmount;
    private final int totalBombsAmount;

    private final Map map;
    private final Cell[][] cells;

    private int closedCellsAmount;

    private GameState gameState = GameState.IN_PROCESS;

    public Game(int rowsAmount, int columnsAmount, int totalBombsAmount) {
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

        if (currentCell.hasBomb()) {
            gameState = GameState.LOST;
            currentCell.setStatus(Cell.CellStatus.EXPLOSION);
            showBombs();
            return;
        }

        closedCellsAmount--;
        currentCell.setOpen(true);

        switch (currentCell.getBombsAmount()) {
            case (1) -> currentCell.setStatus(Cell.CellStatus.ONE);
            case (2) -> currentCell.setStatus(Cell.CellStatus.TWO);
            case (3) -> currentCell.setStatus(Cell.CellStatus.THREE);
            case (4) -> currentCell.setStatus(Cell.CellStatus.FOUR);
            case (5) -> currentCell.setStatus(Cell.CellStatus.FIVE);
            case (6) -> currentCell.setStatus(Cell.CellStatus.SIX);
            case (7) -> currentCell.setStatus(Cell.CellStatus.SEVEN);
            case (8) -> currentCell.setStatus(Cell.CellStatus.EIGHT);
            default -> currentCell.setStatus(Cell.CellStatus.EMPTY);
        }

        checkWinning();

        if (currentCell.getBombsAmount() == 0) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i == 0 && j == 0 || rowIndex + i < 0 || rowIndex + i == rowsAmount || columnIndex + j < 0
                            || columnIndex + j == columnsAmount) {
                        continue;
                    }

                    if (!cells[rowIndex + i][columnIndex + j].isOpen()) {
                        openCell(rowIndex + i, columnIndex + j);
                    }
                }
            }
        }
    }

    public void putOrRemoveFlag(int rowIndex, int columnIndex) {
        Cell cell = cells[rowIndex][columnIndex];

        if (cell.isOpen()) {
            return;
        }

        if (cell.getStatus() == Cell.CellStatus.FLAG) {
            cell.setStatus(Cell.CellStatus.CLOSED);
        } else {
            cell.setStatus(Cell.CellStatus.FLAG);
        }
    }

    public void openAround(int rowIndex, int columnIndex) {
        Cell cell = cells[rowIndex][columnIndex];

        if (!cell.isOpen()) {
            return;
        }

        int flagsAmount = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0 || rowIndex + i < 0 || rowIndex + i == rowsAmount || columnIndex + j < 0
                        || columnIndex + j == columnsAmount) {
                    continue;
                }

                if (cells[rowIndex + i][columnIndex + j].getStatus() == Cell.CellStatus.FLAG) {
                    flagsAmount++;
                }
            }
        }

        if (flagsAmount == cell.getBombsAmount()) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i == 0 && j == 0 || rowIndex + i < 0 || rowIndex + i == rowsAmount || columnIndex + j < 0
                            || columnIndex + j == columnsAmount) {
                        continue;
                    }

                    Cell currentCell = cells[rowIndex + i][columnIndex + j];

                    if (currentCell.getStatus() == Cell.CellStatus.CLOSED) {
                        openCell(rowIndex + i, columnIndex + j);
                    }
                }
            }
        }
    }

    private void checkWinning() {
        if (closedCellsAmount == totalBombsAmount) {
            gameState = GameState.WIN;
        }
    }

    public void showBombs() {
        for (int i = 0; i < map.getRowsAmount(); i++) {
            for (int j = 0; j < map.getRowsAmount(); j++) {
                Cell cell = cells[i][j];

                if (cell.isOpen()) {
                    continue;
                }

                if (cell.hasBomb() && cell.getStatus() == Cell.CellStatus.CLOSED) {
                    cell.setStatus(Cell.CellStatus.BOMB);
                    continue;
                }

                if (cell.getStatus() == Cell.CellStatus.FLAG && !cell.hasBomb()) {
                    cell.setStatus(Cell.CellStatus.WRONG_FLAG);
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