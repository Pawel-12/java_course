package com.solvd.laba.block1.task2;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Cords {
    private float x;
    private float y;

    public Cords(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public double calcDistance(Cords cords2) {
        return sqrt(pow(cords2.x - x, 2)) + sqrt(pow(cords2.y - y, 2));
    }
}
