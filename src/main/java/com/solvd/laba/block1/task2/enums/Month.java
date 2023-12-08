package com.solvd.laba.block1.task2.enums;

import static java.lang.Math.abs;

public enum Month {
    JANUARY("January", 1),
    FEBRUARY("February", 2),
    MARCH("March", 3),
    APRIL("April", 4),
    MAY("May", 5),
    JUNE("June", 6),
    JULY("July", 7),
    AUGUST("August", 8),
    SEPTEMBER("SEPTEMBER", 9),
    OCTOBER("October", 10),
    NOVEMBER("November", 11),
    DECEMBER("December", 12);

    private final String name;
    private final int number;

    private Month(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public int distance(Month month) {
        return abs(this.number - month.number);
    }
}
