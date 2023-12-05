package com.solvd.laba.block1.task2;

import com.solvd.laba.block1.task2.persons.Client;

import java.util.Arrays;
import java.util.List;

public class Order {
    private List<Delivery> deliveries;
    private Client client;

    public Order(Delivery[] deliveries, Client client) {
        this.deliveries = Arrays.stream(deliveries).toList();
        this.client = client;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public Client getClient() {
        return client;
    }

    public void setDeliveries(Delivery[] deliveries) {
        this.deliveries = Arrays.stream(deliveries).toList();
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
