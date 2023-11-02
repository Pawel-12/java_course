package com.solvd.laba.block1.task2;

public class Vehicle {
    private String name;
    private float rate;

    public Vehicle(String name, float rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public float getRate() {
        return rate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
