package com.solvd.laba.block1.task2;

import com.solvd.laba.block1.task2.exceptions.NegativeItemSizeException;
import com.solvd.laba.block1.task2.interfaces.HasName;
import com.solvd.laba.block1.task2.interfaces.PhysicalObject;

import java.util.Objects;

public class Item implements PhysicalObject, HasName {
    private String name;
    private float width;
    private float height;
    private float depth;
    private float weight;

    public Item(String name, float width, float height, float depth, float weight) {
        if ((width < 0) || (height < 0) || (depth < 0) || (weight < 0))
            throw new NegativeItemSizeException("At least one of values is negative!");

        this.name = name;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getDepth() {
        return depth;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public Item getItem() {
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(float width) {
        if (width < 0)
            throw new NegativeItemSizeException("Width cannot be negative!");
        this.width = width;
    }

    public void setHeight(float height) {
        if (height < 0)
            throw new NegativeItemSizeException("Height cannot be negative!");
        this.height = height;
    }

    public void setDepth(float depth) {
        if (depth < 0)
            throw new NegativeItemSizeException("Depth cannot be negative!");
        this.depth = depth;
    }

    public void setWeight(float weight) {
        if (weight < 0)
            throw new NegativeItemSizeException("Weight cannot be negative!");
        this.weight = weight;
    }

    @Override
    public void setItem(Item item) {
        this.name = item.name;
        this.width = item.width;
        this.height = item.height;
        this.depth = item.depth;
        this.weight = item.weight;
    }

    @Override
    public String toString() {
        return '\"' + name + '\"' +
                ", width = " + width +
                ", height = " + height +
                ", depth = " + depth +
                ", weight = " + weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Float.compare(width, item.width) == 0 && Float.compare(height, item.height) == 0 && Float.compare(depth, item.depth) == 0 && Float.compare(weight, item.weight) == 0 && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, width, height, depth, weight);
    }
}
