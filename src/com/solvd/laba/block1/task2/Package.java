package com.solvd.laba.block1.task2;

import com.solvd.laba.block1.task2.interfaces.PhysicalObject;

public class Package implements PhysicalObject {
    private Item item;
    private Location source;
    private Location destination;
    private float distance;

    public Package(Item item, Location from, Location to) {
        this.item = item;
        this.source = from;
        this.destination = to;

        this.distance = (float) from.calcDistance(to);
    }

    public Item getItem() {
        return item;
    }

    public Location getSource() {
        return source;
    }

    public Location getDestination() {
        return destination;
    }

    public float getDistance() {
        return distance;
    }


    public void setItem(Item item) {
        this.item = item;
    }

    public void setSource(Location from) {
        this.source = from;
        distance = (float) source.calcDistance(destination);
    }

    public void setDestination(Location to) {
        destination = to;
        distance = (float) source.calcDistance(destination);
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Package{" +
                "item = {" + item +
                "}, source = {" + source +
                "}, destination = {" + destination +
                "}, distance = " + distance +
                '}';
    }
}
