package ru.academits.paveldik.minesweeper.model;

public class Map {
    private final int rowsAmount;
    private final int columnsAmount;
    private final int totalBombsAmount;

    private final Cell[][] cells;

    public Cell[][] getCells() {
        return cells;
    }

    public Map(int rowsAmount, int columnsAmount, int totalBombsAmount) {
        this.rowsAmount = rowsAmount;
        this.columnsAmount = columnsAmount;
        this.totalBombsAmount = totalBombsAmount;

        cells = new Cell[rowsAmount][columnsAmount];

        addBombs();
        setBombsAmount();
    }

    public int getRowsAmount() {
        return rowsAmount;
    }

    public int getColumnsAmount() {
        return columnsAmount;
    }

    private void addBombs() {
        for (int i = 0; i < totalBombsAmount; i++) {
            int bombRow = (int) Math.floor(Math.random() * rowsAmount);
            int bombColumn = (int) Math.floor(Math.random() * columnsAmount);

            while (cells[bombRow][bombColumn] != null && cells[bombRow][bombColumn].hasBomb()) {
                bombRow = (int) Math.floor(Math.random() * rowsAmount);
                bombColumn = (int) Math.floor(Math.random() * columnsAmount);
            }

            cells[bombRow][bombColumn] = new Cell(true, 0);
        }
    }

    private void setBombsAmount() {
        for (int i = 0; i < rowsAmount; i++) {
            for (int j = 0; j < columnsAmount; j++) {
                int bombsAmount = 0;

                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if (k == 0 && l == 0 || i + k < 0 || i + k == rowsAmount || j + l < 0 || j + l == columnsAmount) {
                            continue;
                        }

                        if (cells[i + k][j + l] != null && cells[i + k][j + l].hasBomb()) {
                            bombsAmount++;
                        }
                    }
                }

                if (cells[i][j] == null) {
                    cells[i][j] = new Cell(false, bombsAmount);
                } else {
                    cells[i][j].setBombsAmount(bombsAmount);
                }
            }
        }
    }
}