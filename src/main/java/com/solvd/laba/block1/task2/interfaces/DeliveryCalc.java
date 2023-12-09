package com.solvd.laba.block1.task2.interfaces;

import com.solvd.laba.block1.task2.Delivery;

@FunctionalInterface
public interface DeliveryCalc<T extends Delivery> {
    public abstract double calc(T del, double base);
}
