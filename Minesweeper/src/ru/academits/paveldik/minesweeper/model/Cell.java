package ru.academits.paveldik.minesweeper.model;

public class Cell {
    private boolean isOpen;
    private final boolean hasBomb;
    private int bombsAmount;
    private CellStatus status = CellStatus.CLOSED; // 0 просто закрытая, 1 - флаг (F), 2 - Бомба (B), 3 - Неверный флаг (W)

    public Cell(boolean hasBomb, int bombsAmount) {
        this.hasBomb = hasBomb;
        this.bombsAmount = bombsAmount;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    public int getBombsAmount() {
        return bombsAmount;
    }

    public void setBombsAmount(int bombsAmount) {
        this.bombsAmount = bombsAmount;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return Integer.toString(bombsAmount);
    }

    public enum CellStatus {
        CLOSED,
        FLAG,
        BOMB,
        WRONG_FLAG,
        EXPLOSION,
        EMPTY,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT
    }
}