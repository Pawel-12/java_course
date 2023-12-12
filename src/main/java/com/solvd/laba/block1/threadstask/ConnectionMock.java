package com.solvd.laba.block1.threadstask;

public class ConnectionMock {
    private static int number = 0;
    private final int id;

    public ConnectionMock() {
        id = ++number;
    }

    @Override
    public String toString() {
        return "CON" + id;
    }
}
