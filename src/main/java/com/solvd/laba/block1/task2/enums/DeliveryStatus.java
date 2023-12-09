package com.solvd.laba.block1.task2.enums;

public enum DeliveryStatus {
    CONFIRMED("Confirmed"),
    TRANSIT("Transit"),
    FINISHED("Finished");

    private final String name;

    private DeliveryStatus(String name) {
        this.name = name;
    }
}
