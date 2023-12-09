package com.solvd.laba.block1.task2;

import com.solvd.laba.block1.task2.enums.DeliveryStatus;
import com.solvd.laba.block1.task2.interfaces.Trackable;

public class Delivery implements Trackable {
    private Package package1;
    private Vehicle vehicle;
    //private float price;
    private Priority priority;
    private DeliveryStatus status = DeliveryStatus.CONFIRMED;
    private Cords cords;

    private static int deliveriesCount;

    static {
        deliveriesCount = 0;
    }

    /*private void updatePrice() {
        this.price = (package1.getItem().getPrice() + (package1.getDistance() * vehicle.getRate())) * priority.getRate();
    }*/

    public Delivery(Package package1, Vehicle vehicle, Priority priority) {
        this.package1 = package1;
        this.vehicle = vehicle;
        this.priority = priority;

        cords = package1.getSource().getCords();

        deliveriesCount++;
        //updatePrice();
    }


    public Package getPackage() {
        return package1;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    /*public float getPrice() {
        return price;
    }*/

    public Priority getPriority() {
        return priority;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    @Override
    public Cords getCords() {
        return cords;
    }

    public String deliveryProgress() {
        return "Progress " + (1.0 - (cords.calcDistance(package1.getDestination()) / package1.getDistance())) + "%";
    }

    public static int getDeliveriesCount() {
        return deliveriesCount;
    }


    public void setPackage(Package package1) {
        this.package1 = package1;
        //updatePrice();
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        //updatePrice();
    }

    /*public void setPrice(float price) {
        this.price = price;
    }*/

    public void setPriority(Priority priority) {
        this.priority = priority;
        //updatePrice();
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    @Override
    public void setCords(Cords cords) {
        this.cords = cords;
    }

    @Override
    public String toString() {
        return package1 +
                ", vehicle = " + vehicle.getName() +
                ", priority = " + priority.getName() +
                ", status = " + status +
                ", cords = " + cords +
                " " + deliveryProgress();
    }
}
