package ru.academits.paveldik.minesweeper.view;

import ru.academits.paveldik.minesweeper.model.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class View {
    JButton[][] buttons;
    JFrame frame;

    public View(Map map) {
        final int CELL_SIZE = 25;

        int rowsAmount = map.getRowsAmount();
        int columnsAmount = map.getColumnsAmount();

        frame = new JFrame();
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(rowsAmount, columnsAmount));
        buttons = new JButton[rowsAmount][columnsAmount];

        for (int i = 0; i < rowsAmount; i++) {
            for (int j = 0; j < columnsAmount; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setSize(CELL_SIZE, CELL_SIZE);
                buttons[i][j].setFocusable(false);
                buttons[i][j].setIcon(new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/closed.png"));
                buttons[i][j].setBorderPainted(false);
                buttons[i][j].setFocusPainted(false);

                panel.add(buttons[i][j]);
            }
        }

        panel.setSize(columnsAmount * CELL_SIZE, rowsAmount * CELL_SIZE);
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(columnsAmount * CELL_SIZE, rowsAmount * CELL_SIZE + 30);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void addButtonListener(int rowIndex, int columnIndex, MouseAdapter actionListener) {
        buttons[rowIndex][columnIndex].addMouseListener(actionListener);
    }

    public void setButtonIcon(int rowIndex, int columnIndex, String iconPath) {
        buttons[rowIndex][columnIndex].setIcon(new ImageIcon(iconPath));
    }

    public void displayGameOver(String gameOverMessage) {
        JOptionPane.showMessageDialog(frame, gameOverMessage);
    }
}