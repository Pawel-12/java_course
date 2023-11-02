package com.solvd.laba.block1.task2;

import java.util.Objects;

public class Location {
    private String town;
    private String road;
    private int houseNumber;
    private Cords cords;

    public Location(String town, String road, int houseNumber, Cords cords) {
        this.town = town;
        this.road = road;
        this.houseNumber = houseNumber;
        this.cords = cords;
    }

    public String getTown() {
        return town;
    }

    public String getRoad() {
        return road;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public Cords getCords() {
        return cords;
    }


    public void setTown(String town) {
        this.town = town;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setCords(Cords cords) {
        this.cords = cords;
    }

    public double calcDistance(Location loc2) {
        return this.cords.calcDistance(loc2.getCords());
    }

    @Override
    public String toString() {
        return town + " " + road + " " + houseNumber + " " + cords.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return houseNumber == location.houseNumber && Objects.equals(town, location.town) && Objects.equals(road, location.road) && Objects.equals(cords, location.cords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(town, road, houseNumber, cords);
    }
}
