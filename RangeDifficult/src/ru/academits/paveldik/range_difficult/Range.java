package ru.academits.paveldik.range_difficult;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double point) {
        return point >= from && point <= to;
    }

    public Range getIntersection(Range range) {
        double from1 = from;
        double to1 = to;

        double from2 = range.getFrom();
        double to2 = range.getTo();

        if (from1 > from2) {
            double temp = from1;
            from1 = from2;
            from2 = temp;

            temp = to1;
            to1 = to2;
            to2 = temp;
        }

        if (to1 <= from2 || from1 == to1 || from2 == to2) {
            return null;
        }

        if (to2 <= to1) {
            return new Range(from2, to2);
        } else {
            return new Range(from2, to1);
        }
    }

    public Range[] getUnion(Range range) {
        double from1 = from;
        double to1 = to;

        double from2 = range.getFrom();
        double to2 = range.getTo();

        if (from1 > from2) {
            double temp = from1;
            from1 = from2;
            from2 = temp;

            temp = to1;
            to1 = to2;
            to2 = temp;
        }

        if (from2 <= to1) {
            Range[] rangeUnion = new Range[1];
            if (to2 >= to1) {
                rangeUnion[0] = new Range(from1, to2);
            } else {
                rangeUnion[0] = new Range(from1, to1);
            }
            return rangeUnion;
        } else {
            Range[] rangeUnion = new Range[2];
            rangeUnion[0] = new Range(from1, to1);
            rangeUnion[1] = new Range(from2, to2);
            return rangeUnion;
        }
    }

    public Range[] getDifference(Range range) {
        double from1 = from;
        double to1 = to;

        double from2 = range.getFrom();
        double to2 = range.getTo();

        if (from1 >= from2) {
            if (to2 >= to1) {
                return null;
            }

            if (to2 >= from1) {
                Range[] rangeDifference = new Range[1];
                rangeDifference[0] = new Range(to2, to1);
                return rangeDifference;
            }


            Range[] rangeDifference = new Range[1];
            rangeDifference[0] = new Range(from1, to1);
            return rangeDifference;
        }

        if (from2 <= to1) {
            if (to2 >= to1) {
                Range[] rangeDifference = new Range[1];
                rangeDifference[0] = new Range(from1, from2);
                return rangeDifference;
            }

            Range[] rangeDifference = new Range[2];
            rangeDifference[0] = new Range(from1, from2);
            rangeDifference[1] = new Range(to2, to1);
            return rangeDifference;
        }

        Range[] rangeDifference = new Range[1];
        rangeDifference[0] = new Range(from1, to1);
        return rangeDifference;
    }
}