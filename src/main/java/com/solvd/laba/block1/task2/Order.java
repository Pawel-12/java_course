package com.solvd.laba.block1.task2;

import com.solvd.laba.block1.task2.persons.Client;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Order {
    private List<Delivery> deliveries;
    private Client client;

    public Order(List<Delivery> deliveries, Client client) {
        this.deliveries = deliveries;
        this.client = client;
    }

    public Order(Delivery[] deliveries, Client client) {
        this(Arrays.stream(deliveries).toList(), client);
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public Delivery getDelivery(Predicate<Delivery> filter) {
        for (var d : deliveries)
            if (filter.test(d))
                return d;

        return null;
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
