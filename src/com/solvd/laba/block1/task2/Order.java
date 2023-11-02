package com.solvd.laba.block1.task2;

public class Order {
    private Delivery[] deliveries;
    private Client client;
//    private float totalPrice;

    public Order(Delivery[] deliveries, Client client) {
        this.deliveries = deliveries;
        this.client = client;

//        for (Delivery delivery : this.deliveries)
//            this.totalPrice += delivery.getPrice();
    }

    public Delivery[] getDeliveries() {
        return deliveries;
    }

    public Client getClient() {
        return client;
    }

//    public float getTotalPrice() {
//        return totalPrice;
//    }

    public void setDeliveries(Delivery[] deliveries) {
        this.deliveries = deliveries;
    }

    public void setClient(Client client) {
        this.client = client;
    }

//    public void setTotalPrice(float totalPrice) {
//        this.totalPrice = totalPrice;
//    }
}
