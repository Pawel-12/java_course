package com.solvd.laba.block1.task2.interfaces;

import com.solvd.laba.block1.task2.Item;

@FunctionalInterface
public interface ItemTest<T extends Item> {
    public abstract boolean test(T item, float value);
}
