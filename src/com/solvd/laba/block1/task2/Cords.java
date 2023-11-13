package com.solvd.laba.block1.task2;

import com.solvd.laba.block1.task2.interfaces.Trackable;

import java.util.Objects;

public class Cords implements Trackable {
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

    @Override
    public Cords getCords() {
        return this;
    }

    @Override
    public void setCords(Cords cords) {
        this.x = cords.x;
        this.y = cords.y;
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