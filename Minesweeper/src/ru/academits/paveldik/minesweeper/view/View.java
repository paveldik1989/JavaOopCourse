package ru.academits.paveldik.minesweeper.view;

import ru.academits.paveldik.minesweeper.controller.Controller;
import ru.academits.paveldik.minesweeper.model.Record;
import ru.academits.paveldik.minesweeper.model.RecordTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class View {
    Controller controller;

    final static int CELL_SIZE = 24;
    int rowsAmount;
    int columnsAmount;
    int totalBombsAmount;

    JButton[][] buttons;
    MouseAdapter[][] mouseAdapters;

    JFrame frame;
    JLabel remainingBombsCounter;
    JLabel stopwatch;
    JButton smile;
    JPanel gameField;

    JMenuBar menuBar;
    JMenu gameMenu;

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

        JMenuItem newGame = new JMenuItem("New game");
        gameMenu.add(newGame);
        newGame.addActionListener(e -> controller.restartGame());
        gameMenu.add(new JSeparator());

        JRadioButtonMenuItem beginner = new JRadioButtonMenuItem("Beginner");
        gameMenu.add(beginner);
        beginner.addActionListener(e -> controller.setBeginner());

        JRadioButtonMenuItem intermediate = new JRadioButtonMenuItem("Intermediate");
        gameMenu.add(intermediate);
        intermediate.addActionListener(e -> controller.setIntermediate());

        JRadioButtonMenuItem expert = new JRadioButtonMenuItem("Expert");
        gameMenu.add(expert);
        expert.addActionListener(e -> controller.setExpert());

        JRadioButtonMenuItem custom = new JRadioButtonMenuItem("Custom");
        gameMenu.add(custom);
        custom.addActionListener(e -> controller.setCustom());
        gameMenu.add(new JSeparator());

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(beginner);
        buttonGroup.add(intermediate);
        buttonGroup.add(expert);
        buttonGroup.add(custom);

        JMenuItem highScores = new JMenuItem("High Scores");
        gameMenu.add(highScores);
        highScores.addActionListener(e -> {
            GridLayout gridLayout = new GridLayout(16, 4);
            gridLayout.setHgap(25);

            JPanel panel = new JPanel(gridLayout);
//            panel.setSize(400, 500);

            panel.add(new JLabel("Rank"));
            panel.add(new JLabel("Difficulty"));
            panel.add(new JLabel("Name"));
            panel.add(new JLabel("Time"));

            ArrayList<Record> beginnerRecords = controller.getRecords(RecordTable.GameDifficulty.BEGINNER);
            int beginnerRecordsAmount;

            if (beginnerRecords == null) {
                beginnerRecordsAmount = 0;
            } else beginnerRecordsAmount = Math.min(beginnerRecords.size(), 5);

            for (int i = 0; i < beginnerRecordsAmount; i++) {
                panel.add(new JLabel(Integer.toString(i + 1)));
                panel.add(new JLabel("Beginner"));
                panel.add(new JLabel(beginnerRecords.get(i).name()));
                panel.add(new JLabel(Integer.toString(beginnerRecords.get(i).time())));
            }

            for (int i = beginnerRecordsAmount; i < 5; i++) {
                panel.add(new JLabel(Integer.toString(i + 1)));
                panel.add(new JLabel("Beginner"));
                panel.add(new JLabel("Not set yet."));
                panel.add(new JLabel("Not set yet."));
            }

            ArrayList<Record> intermediateRecords = controller.getRecords(RecordTable.GameDifficulty.INTERMEDIATE);
            int intermediateRecordsAmount;
            if (intermediateRecords == null) {
                intermediateRecordsAmount = 0;
            } else intermediateRecordsAmount = Math.min(intermediateRecords.size(), 5);
            System.out.println(intermediateRecordsAmount);

            for (int i = 0; i < intermediateRecordsAmount; i++) {
                panel.add(new JLabel(Integer.toString(i + 1)));
                panel.add(new JLabel("Intermediate"));
                panel.add(new JLabel(intermediateRecords.get(i).name()));
                panel.add(new JLabel(Integer.toString(intermediateRecords.get(i).time())));
            }

            for (int i = intermediateRecordsAmount; i < 5; i++) {
                panel.add(new JLabel(Integer.toString(i + 1)));
                panel.add(new JLabel("Intermediate"));
                panel.add(new JLabel("Not set yet."));
                panel.add(new JLabel("Not set yet."));
            }

            ArrayList<Record> expertRecords = controller.getRecords(RecordTable.GameDifficulty.EXPERT);
            int expertRecordsAmount;
            if (expertRecords == null) {
                expertRecordsAmount = 0;
            } else expertRecordsAmount = Math.min(expertRecords.size(), 5);

            for (int i = 0; i < expertRecordsAmount; i++) {
                panel.add(new JLabel(Integer.toString(i + 1)));
                panel.add(new JLabel("Expert"));
                panel.add(new JLabel(expertRecords.get(i).name()));
                panel.add(new JLabel(Integer.toString(expertRecords.get(i).time())));
            }

            for (int i = expertRecordsAmount; i < 5; i++) {
                panel.add(new JLabel(Integer.toString(i + 1)));
                panel.add(new JLabel("Expert"));
                panel.add(new JLabel("Not set yet."));
                panel.add(new JLabel("Not set yet."));
            }

            panel.setSize(600, 600);
            JDialog recordsFrame = new JDialog(frame, "Records", true);
            recordsFrame.getContentPane().add(panel);
            recordsFrame.pack();
            recordsFrame.setVisible(true);
            recordsFrame.setLocationRelativeTo(null); // Почему то не работает.
        });

        gameMenu.add(new JSeparator());

        JMenuItem about = new JMenuItem("About");
        gameMenu.add(about);
        about.addActionListener(e -> JOptionPane.showMessageDialog(frame, "This game was written by Pavel Dik in 2022."));
        gameMenu.add(new JSeparator());

        JMenuItem exit = new JMenuItem("Exit");
        gameMenu.add(exit);
        exit.addActionListener(event -> System.exit(0));
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
        mouseAdapters = new MouseAdapter[rowsAmount][columnsAmount];

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

                mouseAdapters[i][j] = new MouseAdapter() {
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

                buttons[i][j].addMouseListener(mouseAdapters[i][j]);
            }
        }
    }

    public void addComponentsToFrame() {
        frame.setLayout(new GridBagLayout());
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createLoweredSoftBevelBorder());

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

    public void setRemainingBombsCounter(int flagsAmount) {
        remainingBombsCounter.setText(String.format("%03d", flagsAmount));
    }

    public void setStopwatch(int seconds) {
        stopwatch.setText(String.format("%03d", seconds));
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

    public void displayCustom() {
        int customRowsAmount = rowsAmount;
        int customColumnsAmount = columnsAmount;
        int customTotalBombsAmount = totalBombsAmount;

        JTextField rowsAmountField = new JTextField(Integer.toString(customRowsAmount), 3);
        JTextField columnsAmountField = new JTextField(Integer.toString(customColumnsAmount), 3);
        JTextField totalBombsAmountField = new JTextField(Integer.toString(customTotalBombsAmount), 3);

        JPanel customPanel = new JPanel(new GridLayout(3, 2));

        customPanel.add(new JLabel("Rows amount:"));
        customPanel.add(rowsAmountField);

        customPanel.add(new JLabel("Columns amount:"));
        customPanel.add(columnsAmountField);

        customPanel.add(new JLabel("Bombs amount:"));
        customPanel.add(totalBombsAmountField);


        int result = JOptionPane.showConfirmDialog(null, customPanel,
                "Custom game", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                customRowsAmount = Integer.parseInt(rowsAmountField.getText());
            } catch (NumberFormatException ignored) {
            }

            try {
                customColumnsAmount = Integer.parseInt(columnsAmountField.getText());
            } catch (NumberFormatException ignored) {
            }

            try {
                customTotalBombsAmount = Integer.parseInt(totalBombsAmountField.getText());
            } catch (NumberFormatException ignored) {
            }

            if (customRowsAmount < 9) {
                rowsAmount = 9;
            } else rowsAmount = Math.min(customRowsAmount, 24);

            if (customColumnsAmount < 9) {
                columnsAmount = 9;
            } else columnsAmount = Math.min(customColumnsAmount, 30);

            if (customTotalBombsAmount < 10) {
                totalBombsAmount = 10;
            } else if (customTotalBombsAmount > rowsAmount * columnsAmount * 0.8) {
                totalBombsAmount = (int) Math.round(rowsAmount * columnsAmount * 0.8);
            } else {
                totalBombsAmount = customTotalBombsAmount;
            }

            controller.newGame(rowsAmount, columnsAmount, totalBombsAmount);
        }
    }

    public void removeMouseAdapters() {
        for (int i = 0; i < rowsAmount; i++) {
            for (int j = 0; j < columnsAmount; j++) {
                buttons[i][j].removeMouseListener(mouseAdapters[i][j]);
            }
        }
    }

    public void closeFrame() {
        frame.dispose();
    }

    public void displayNewRecord() {
        String name;
        JTextField nameField = new JTextField(10);
        JPanel newRecordPanel = new JPanel();

        newRecordPanel.add(new JLabel("Please, enter your name:"));
        newRecordPanel.add(nameField);

        int result = JOptionPane.showConfirmDialog(null, newRecordPanel,
                "You have set a new record!", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            name = nameField.getText();

            if (name == null) {
                return;
            }

            controller.setRecord(name);
        }
    }
}