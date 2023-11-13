package com.solvd.laba.block1.task2.interfaces;

import com.solvd.laba.block1.task2.Item;

public interface PhysicalObject {
    public Item getItem();

    public void setItem(Item item);

    public default boolean equals(PhysicalObject obj) {
        return this.getItem().equals(obj.getItem());
    }
}
