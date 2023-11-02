package com.solvd.laba.block1.task2;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cords cords = (Cords) o;
        return Float.compare(x, cords.x) == 0 && Float.compare(y, cords.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}