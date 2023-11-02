package com.solvd.laba.block1.task2;

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
}
