package com.solvd.laba.block1.task2;

import com.solvd.laba.block1.task2.exceptions.NegativeRateException;
import com.solvd.laba.block1.task2.interfaces.HasName;
import com.solvd.laba.block1.task2.interfaces.HasRate;

public class Priority implements HasRate, HasName {
    private String name;
    private float rate;

    public Priority(String name, float rate) throws NegativeRateException {
        if (rate < 0)
            throw new NegativeRateException("Rate cannot be negative!");

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

    public void setRate(float rate) throws NegativeRateException {
        if (rate < 0)
            throw new NegativeRateException("Rate cannot be negative!");
        this.rate = rate;
    }
}
