package com.solvd.laba.block1.task2;

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
}
