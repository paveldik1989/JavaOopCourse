package ru.academits.paveldik.minesweeper.model;

public record Record(int time, String name) implements Comparable<Record> {
    @Override
    public int time() {
        return time;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return time + " " + name;
    }

    @Override
    public int compareTo(Record record) {
        return Integer.compare(time, record.time);
    }
}
