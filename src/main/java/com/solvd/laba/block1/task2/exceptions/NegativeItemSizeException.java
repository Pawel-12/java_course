package com.solvd.laba.block1.task2.exceptions;

public class NegativeItemSizeException extends RuntimeException {
    public NegativeItemSizeException(String errorMessage) {
        super(errorMessage);
    }
}
