package com.solvd.laba.block1.task2.interfaces;

import com.solvd.laba.block1.task2.Cords;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public interface Trackable {
    public Cords getCords();

    public void setCords(Cords cords);

    public default double calcDistance(Trackable track2) {
        return sqrt(pow(track2.getCords().getX() - this.getCords().getX(), 2))
                + sqrt(pow(track2.getCords().getY() - this.getCords().getY(), 2));
    }
}
