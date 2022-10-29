package ru.academits.paveldik.minesweeper.model;

public class Cell {
    private boolean isOpen;

    private final int mapState;
    private int closedState; // 0 просто закрытая, 1 - флаг (F), 2 - Бомба (B), 3 - Неверный флаг (W)

    public Cell(int mapStatus) {
        this.mapState = mapStatus;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getMapState() {
        return mapState;
    }

    public int getClosedState() {
        return closedState;
    }

    public void setClosedState(int closedState) {
        this.closedState = closedState;
    }

    @Override
    public String toString() {
        return Integer.toString(mapState);
    }

    public String toStringGame() {
        if (isOpen) {
            if (mapState == 0) {
                return "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/0.png";
            }

            if (mapState == 1) {
                return "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/1.png";
            }

            if (mapState == 2) {
                return "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/2.png";
            }

            if (mapState == 3) {
                return "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/3.png";
            }

            if (mapState == 4) {
                return "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/4.png";
            }

            if (mapState == 5) {
                return "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/5.png";
            }

            if (mapState == 6) {
                return "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/6.png";
            }

            if (mapState == 7) {
                return "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/7.png";
            }

            if (mapState == 8) {
                return "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/8.png";
            }

            return "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/explosion.png";
        } else {
            if (closedState == 1) {
                return "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/flag.png";
            }

            if (closedState == 2) {
                return "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/bomb.png"; // Minesweeper/src/ru/academits/paveldik/minesweeper/
            }

            if (closedState == 3) {
                return "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/wrong_flag.png";
            }

            return "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/closed.png";
        }
    }
}