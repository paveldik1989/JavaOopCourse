package ru.academits.paveldik.minesweeper.view;

import ru.academits.paveldik.minesweeper.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View {
    Controller controller;

    final static int CELL_SIZE = 24;
    int rowsAmount;
    int columnsAmount;
    int totalBombsAmount;

    JButton[][] buttons;
    JFrame frame;
    JLabel remainingBombsCounter;
    JLabel stopwatch;
    JButton smile;
    JPanel gameField;

    JMenuBar menuBar;
    JMenu gameMenu;
    JMenuItem newGame;
    JCheckBoxMenuItem beginner;
    JCheckBoxMenuItem intermediate;
    JCheckBoxMenuItem expert;
    JCheckBoxMenuItem custom;
    JMenuItem highScores;
    JMenuItem exit;

    ImageIcon smileInProcess;
    ImageIcon smileWin;
    ImageIcon smileLost;

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

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        SwingUtilities.invokeLater(() -> {
                    frame = new JFrame("Minesweeper");

                    initializeMenu();
                    initializeIcons();
                    initializeSmile();
                    initializeGameField();
                    addComponentsToFrame();
                }
        );
    }

    public void setRowsAmount(int rowsAmount) {
        this.rowsAmount = rowsAmount;
    }

    public void setColumnsAmount(int columnsAmount) {
        this.columnsAmount = columnsAmount;
    }

    public void setTotalBombsAmount(int totalBombsAmount) {
        this.totalBombsAmount = totalBombsAmount;
    }

    public void initializeMenu() {
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);

        JRadioButtonMenuItem beginner = new JRadioButtonMenuItem ("Beginner");
        gameMenu.add(beginner);
        beginner.addActionListener(event -> controller.setBeginner());

        JRadioButtonMenuItem  intermediate = new JRadioButtonMenuItem ("Intermediate");
        gameMenu.add(intermediate);
        intermediate.addActionListener(event -> controller.setIntermediate());

        JRadioButtonMenuItem  expert = new JRadioButtonMenuItem ("Expert");
        gameMenu.add(expert);
        expert.addActionListener(event -> controller.setExpert());

        JRadioButtonMenuItem  custom = new JRadioButtonMenuItem ("Custom");
        gameMenu.add(custom);
        custom.addActionListener(event -> controller.setCustom());
        gameMenu.add(new JSeparator());

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(beginner);
        buttonGroup.add(intermediate);
        buttonGroup.add(expert);
        buttonGroup.add(custom);

        JMenuItem highScores = new JMenuItem("High Scores");
        gameMenu.add(highScores);
        gameMenu.add(new JSeparator());

        JMenuItem exit = new JMenuItem("Exit");
        gameMenu.add(exit);
    }

    public void initializeIcons() {
        smileInProcess = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/in_process.png");
        smileWin = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/win.png");
        smileLost = new ImageIcon("Minesweeper/src/ru/academits/paveldik/minesweeper/resources/lost.png");

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
    }

    public void initializeGameField() {
        gameField = new JPanel();
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

                final int rowIndex = i;
                final int columnIndex = j;

                MouseAdapter mouseAdapter = new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                            controller.leftClick(rowIndex, columnIndex);
                        } else if (mouseEvent.getButton() == MouseEvent.BUTTON2) {
                            controller.middleClick(rowIndex, columnIndex);
                        } else if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                            controller.rightClick(rowIndex, columnIndex);
                        }
                    }
                };

                buttons[i][j].addMouseListener(mouseAdapter);
            }
        }
    }

    public void addComponentsToFrame() {
        frame.setLayout(new GridBagLayout());
        JPanel panel = new JPanel(new GridBagLayout());

        remainingBombsCounter = new JLabel();
        setRemainingBombsCounter(totalBombsAmount);
        remainingBombsCounter.setFont(new Font("Agency FB", Font.BOLD, 50));
        remainingBombsCounter.setPreferredSize(new Dimension(75, 56));
        remainingBombsCounter.setBackground(new Color(0, 0, 0));
        remainingBombsCounter.setOpaque(true);
        remainingBombsCounter.setForeground(new Color(255, 0, 0));

        stopwatch = new JLabel();
        setStopwatch(0);
        stopwatch.setFont(new Font("Agency FB", Font.BOLD, 50));
        stopwatch.setPreferredSize(new Dimension(75, 56));
        stopwatch.setBackground(new Color(0, 0, 0));
        stopwatch.setOpaque(true);
        stopwatch.setForeground(new Color(255, 0, 0));

        smile.setPreferredSize(new Dimension(56, 56));
        smile.setFocusable(false);
        smile.setBorderPainted(false);
        smile.setFocusPainted(false);

        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1.gridx = 0;
        constraints1.gridy = 0;
        constraints1.gridwidth = 1;
        constraints1.gridheight = 1;
        constraints1.weightx = 1;
        constraints1.anchor = GridBagConstraints.LINE_START;
        panel.add(remainingBombsCounter, constraints1);

        GridBagConstraints constraints2 = new GridBagConstraints();
        constraints2.gridx = 1;
        constraints2.gridy = 0;
        constraints2.gridwidth = 1;
        constraints2.gridheight = 1;
        constraints2.weightx = 1;
        constraints2.anchor = GridBagConstraints.CENTER;
        panel.add(smile, constraints2);

        GridBagConstraints constraints3 = new GridBagConstraints();
        constraints3.gridx = 2;
        constraints3.gridy = 0;
        constraints3.gridwidth = 1;
        constraints3.gridheight = 1;
        constraints3.weightx = 1;
        constraints3.anchor = GridBagConstraints.LINE_END;
        panel.add(stopwatch, constraints3);

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
        frame.setSize(columnsAmount * CELL_SIZE + 50, rowsAmount * CELL_SIZE + 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }


    public void initializeSmile() {
        smile = new JButton(smileInProcess);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                    controller.restartGame();
                }
            }
        };

        smile.addMouseListener(mouseAdapter);
    }


    public void addSmileListener(MouseAdapter actionListener) {
        smile.addMouseListener(actionListener);
    }

    public void setRemainingBombsCounter(int flagsAmount) {
        remainingBombsCounter.setText(String.format("%03d", flagsAmount));
    }

    public void setStopwatch(int seconds) {
        stopwatch.setText(String.format("%03d", seconds));
    }

    public void setSmileInProcess() {
        smile.setIcon(smileInProcess);
    }

    public void setSmileWin() {
        smile.setIcon(smileWin);
    }

    public void setSmileLost() {
        smile.setIcon(smileLost);
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

    public void displayCustom(String gameOverMessage) {
        JOptionPane.showMessageDialog(frame, gameOverMessage);
    }

    public void closeFrame(){
        frame.dispose();
    }
}