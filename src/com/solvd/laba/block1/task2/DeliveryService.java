package com.solvd.laba.block1.task2;

import java.util.*;

public class DeliveryService {
    private Map<Integer, Client> clients;
    private Map<Integer, Employee> employees;

    private Map<Integer, ArrayList<Order>> orders;

    private ArrayList<Delivery> deliveries;
    private ArrayList<Delivery> deliveriesArchive;

    DeliveryService() {
        this.clients = new HashMap<>();
        this.employees = new HashMap<>();
        this.orders = new HashMap<>();
        this.deliveries = new ArrayList<>();
        this.deliveriesArchive = new ArrayList<>();
    }

    public void addClient(Client... client) {
        for (Client c : client)
            clients.putIfAbsent(c.getId(), c);
    }

    public void addEmployee(Employee... employee) {
        for (Employee emp : employee)
            employees.putIfAbsent(emp.getId(), emp);
    }

    public void newOrder(Client client, Delivery... delivery) {

        Order order = new Order(delivery, client);

        this.deliveries.addAll(Arrays.asList(delivery));

        clients.putIfAbsent(client.getId(), client);

        orders.putIfAbsent(client.getId(), new ArrayList<>());
        orders.get(client.getId()).add(order);

        int i = 0;
        for (Delivery d : delivery)
            System.out.println("Delivery " + ++i + " = " + calculateSingleDeliveryCost(d));

        System.out.println("Total =  " + calculateTotalOrderCost(order) + '\n');

        client.setAccountBalance((float) (client.getAccountBalance() - calculateTotalOrderCost(order)));
    }

    public void processDeliveries() {

        int i = 0;
        for (Delivery d : deliveries)
            for (Employee e : employees.values())
                if (e instanceof Driver)
                    if (((Driver) e).getUsedVehicles().contains(d.getVehicle().getName())) {
                        ((Driver) e).addDelivery(d);
                        deliveriesArchive.add(d);
                        deliveries.set(i, null);
                        i++;
                    }

        deliveries.removeIf(Objects::isNull);
    }

    public Map<Integer, Client> getClients() {
        return clients;
    }

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    public Map<Integer, ArrayList<Order>> getOrders() {
        return orders;
    }

    public ArrayList<Delivery> getDeliveries() {
        return deliveries;
    }

    public ArrayList<Delivery> getDeliveriesArchive() {
        return deliveriesArchive;
    }

    public static double calculateSingleDeliveryCost(Delivery delivery) {
        double result = 0;

        if (inSizeRange(delivery.getPackage().getItem(), 5))
            result += 10;
        else if (inSizeRange(delivery.getPackage().getItem(), 10))
            result += 50;
        else if (inSizeRange(delivery.getPackage().getItem(), 40))
            result += 60;

        result += (delivery.getPackage().getDistance() * delivery.getVehicle().getRate());
        result *= delivery.getPriority().getRate();

        return result;
    }

    public static double calculateTotalOrderCost(Order order) {
        double result = 0;

        for (Delivery d : order.getDeliveries())
            result += calculateSingleDeliveryCost(d);

        return result;
    }

    private static boolean inSizeRange(Item item, float size) {
        boolean widthOrHeight = (item.getWidth() <= size) || (item.getHeight() <= size);
        boolean depthOrWeight = (item.getDepth() <= size) || (item.getWeight() <= size);

        return widthOrHeight || depthOrWeight;
    }
}