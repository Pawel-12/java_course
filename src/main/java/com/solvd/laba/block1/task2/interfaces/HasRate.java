package com.solvd.laba.block1.task2.interfaces;

import com.solvd.laba.block1.task2.exceptions.NegativeRateException;

public interface HasRate {
    public float getRate();

    public void setRate(float rate) throws NegativeRateException;
}
