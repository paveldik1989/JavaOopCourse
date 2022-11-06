package ru.academits.paveldik.minesweeper.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RecordTable {
    String beginnerTablePath = "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/beginner.txt";
    String intermediateTablePath = "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/intermediate.txt";
    String expertTablePath = "Minesweeper/src/ru/academits/paveldik/minesweeper/resources/expert.txt";


    public ArrayList<Record> getRecords(GameDifficulty gameDifficulty) {
        String recordTablePath;
        int recordTime;
        String recordName;
        ArrayList<Record> records = new ArrayList<>();

        switch (gameDifficulty) {
            case BEGINNER -> recordTablePath = beginnerTablePath;
            case INTERMEDIATE -> recordTablePath = intermediateTablePath;
            case EXPERT -> recordTablePath = expertTablePath;
            default -> {
                return null;
            }
        }

        try (Scanner scanner = new Scanner(new FileInputStream(recordTablePath))) {
            String line;
            String[] splitLine;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                splitLine = line.split(" ", 2);
                recordName = splitLine[1];
                try {
                    recordTime = Integer.parseInt(splitLine[0]);
                } catch (NumberFormatException e) {
                    return null;
                }

                records.add(new Record(recordTime, recordName));
            }
        } catch (FileNotFoundException e) {
            return null;
        }

        records.sort(Record::compareTo);

        return records;
    }

    public void setRecord(int time, String name, GameDifficulty gameDifficulty) {

        String recordTablePath;
        ArrayList<Record> records = getRecords(gameDifficulty);
        if (records == null) {
            records = new ArrayList<>();
        }

        records.add(new Record(time, name));
        records.sort(Record::compareTo);
        records = records.stream().limit(5).collect(Collectors
                .toCollection(ArrayList::new));

        switch (gameDifficulty) {
            case BEGINNER -> recordTablePath = beginnerTablePath;
            case INTERMEDIATE -> recordTablePath = intermediateTablePath;
            case EXPERT -> recordTablePath = expertTablePath;
            default -> {
                return;
            }
        }

        try (PrintWriter writer = new PrintWriter(recordTablePath)) {
            for (Record record : records) {
                writer.println(record);
            }
        } catch (IOException ignored) {
        }
    }

    public enum GameDifficulty {
        BEGINNER,
        INTERMEDIATE,
        EXPERT,
        CUSTOM
    }
}
