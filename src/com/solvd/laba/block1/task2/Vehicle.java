package com.solvd.laba.block1.task2;

import com.solvd.laba.block1.task2.interfaces.HasName;
import com.solvd.laba.block1.task2.interfaces.HasRate;

public class Vehicle implements HasRate, HasName {
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
