package com.solvd.laba.block1.task2;

import java.util.Objects;

public class Item {
    private String name;
    private float width;
    private float height;
    private float depth;
    private float weight;

    public Item(String name, float width, float height, float depth, float weight) {
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


    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return '\"' + name + '\"' + '\t' +
                "width = " + width +
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
