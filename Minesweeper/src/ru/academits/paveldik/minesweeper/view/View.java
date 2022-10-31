package ru.academits.paveldik.minesweeper.view;

import ru.academits.paveldik.minesweeper.model.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class View {
    JButton[][] buttons;
    JFrame frame;

    ImageIcon empty;
    ImageIcon one;
    ImageIcon two;
    ImageIcon three;
    ImageIcon four;
    ImageIcon five;
    ImageIcon six;
    ImageIcon seven;
    ImageIcon eight;
    ImageIcon explosion;
    ImageIcon flag;
    ImageIcon bomb;
    ImageIcon wrongFlag;
    ImageIcon closed;

    public View(Map map) {
        final int CELL_SIZE = 24;

        int rowsAmount = map.getRowsAmount();
        int columnsAmount = map.getColumnsAmount();

        frame = new JFrame("Minesweeper");

        JPanel gameField = new JPanel();

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu game = new JMenu("Game");
        menuBar.add(game);

        JMenuItem newGame = new JMenuItem("New Game");
        game.add(newGame);
        game.add(new JSeparator());

        JCheckBoxMenuItem beginner = new JCheckBoxMenuItem("Beginner");
        game.add(beginner);

        JCheckBoxMenuItem intermediate = new JCheckBoxMenuItem("Intermediate");
        game.add(intermediate);

        JCheckBoxMenuItem expert = new JCheckBoxMenuItem("Expert");
        game.add(expert);

        JCheckBoxMenuItem custom = new JCheckBoxMenuItem("Custom");
        game.add(custom);
        game.add(new JSeparator());

        JMenuItem highScores = new JMenuItem("High Scores");
        game.add(highScores);
        game.add(new JSeparator());

        JMenuItem exit = new JMenuItem("Exit");
        game.add(exit);

        empty = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/0.png");
        one = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/1.png");
        two = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/2.png");
        three = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/3.png");
        four = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/4.png");
        five = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/5.png");
        six = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/6.png");
        seven = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/7.png");
        eight = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/8.png");
        explosion = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/explosion.png");
        flag = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/flag.png");
        bomb = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/bomb.png");
        wrongFlag = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/wrong_flag.png");
        closed = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/closed.png");

        gameField.setLayout(new GridLayout(rowsAmount, columnsAmount));
        gameField.setPreferredSize(new Dimension(columnsAmount * CELL_SIZE, rowsAmount * CELL_SIZE));

        buttons = new JButton[rowsAmount][columnsAmount];

        for (int i = 0; i < rowsAmount; i++) {
            for (int j = 0; j < columnsAmount; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setSize(CELL_SIZE, CELL_SIZE);
                buttons[i][j].setFocusable(false);
                buttons[i][j].setIcon(closed);
                buttons[i][j].setBorderPainted(false);
                buttons[i][j].setFocusPainted(false);

                gameField.add(buttons[i][j]);
            }
        }


        frame.setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1.gridx = 0;
        constraints1.gridy = 0;
        constraints1.gridwidth = 1;
        constraints1.gridheight = 1;
        constraints1.weightx = 1;
        constraints1.anchor = GridBagConstraints.LINE_START;
        panel.add(new JButton("Flags"), constraints1);

        GridBagConstraints constraints2 = new GridBagConstraints();
        constraints2.gridx = 1;
        constraints2.gridy = 0;
        constraints2.gridwidth = 1;
        constraints2.gridheight = 1;
        constraints2.weightx = 1;
        constraints2.anchor = GridBagConstraints.CENTER;
        panel.add(new JButton("Flags"), constraints2);

        GridBagConstraints constraints3 = new GridBagConstraints();
        constraints3.gridx = 2;
        constraints3.gridy = 0;
        constraints3.gridwidth = 1;
        constraints3.gridheight = 1;
        constraints3.weightx = 1;
        constraints3.anchor = GridBagConstraints.LINE_END;
        panel.add(new JButton("Timer"), constraints3);

        GridBagConstraints constraints4 = new GridBagConstraints();
        constraints4.gridx = 0;
        constraints4.gridy = 0;
        constraints4.gridwidth = 1;
        constraints4.gridheight = 1;
        constraints4.fill = GridBagConstraints.HORIZONTAL;
        frame.add(panel, constraints4);

        GridBagConstraints constraints5 = new GridBagConstraints();
        constraints5.gridx = 0;
        constraints5.gridy = 1;
        constraints5.gridwidth = 1;
        constraints5.gridheight = 1;
        frame.add(gameField, constraints5);

        frame.setVisible(true);
        frame.setSize(columnsAmount * CELL_SIZE + 50, rowsAmount * CELL_SIZE + 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void addButtonListener(int rowIndex, int columnIndex, MouseAdapter actionListener) {
        buttons[rowIndex][columnIndex].addMouseListener(actionListener);
    }

    public void setEmpty(int rowIndex, int columnIndex) {
        buttons[rowIndex][columnIndex].setIcon(empty);
    }

    public void setOne(int rowIndex, int columnIndex) {
        buttons[rowIndex][columnIndex].setIcon(one);
    }

    public void setTwo(int rowIndex, int columnIndex) {
        buttons[rowIndex][columnIndex].setIcon(two);
    }

    public void setThree(int rowIndex, int columnIndex) {
        buttons[rowIndex][columnIndex].setIcon(three);
    }

    public void setFour(int rowIndex, int columnIndex) {
        buttons[rowIndex][columnIndex].setIcon(four);
    }

    public void setFive(int rowIndex, int columnIndex) {
        buttons[rowIndex][columnIndex].setIcon(five);
    }

    public void setSix(int rowIndex, int columnIndex) {
        buttons[rowIndex][columnIndex].setIcon(six);
    }

    public void setSeven(int rowIndex, int columnIndex) {
        buttons[rowIndex][columnIndex].setIcon(seven);
    }

    public void setEight(int rowIndex, int columnIndex) {
        buttons[rowIndex][columnIndex].setIcon(eight);
    }

    public void setExplosion(int rowIndex, int columnIndex) {
        buttons[rowIndex][columnIndex].setIcon(explosion);
    }

    public void setFlag(int rowIndex, int columnIndex) {
        buttons[rowIndex][columnIndex].setIcon(flag);
    }

    public void setBomb(int rowIndex, int columnIndex) {
        buttons[rowIndex][columnIndex].setIcon(bomb);
    }

    public void setWrongFlag(int rowIndex, int columnIndex) {
        buttons[rowIndex][columnIndex].setIcon(wrongFlag);
    }

    public void setClosed(int rowIndex, int columnIndex) {
        buttons[rowIndex][columnIndex].setIcon(closed);
    }

    public void displayGameOver(String gameOverMessage) {
        JOptionPane.showMessageDialog(frame, gameOverMessage);
    }
}