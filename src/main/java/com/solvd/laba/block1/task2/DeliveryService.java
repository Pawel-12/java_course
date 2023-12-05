package com.solvd.laba.block1.task2;

import com.solvd.laba.block1.task2.persons.Client;
import com.solvd.laba.block1.task2.persons.Driver;
import com.solvd.laba.block1.task2.persons.Employee;

import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.solvd.laba.block1.task2.Main.LOGGER;

public class DeliveryService {
    private Map<Integer, Client> clients;
    private Map<Integer, Employee> employees;

    private Map<Integer, ArrayList<Order>> orders;

    private ArrayList<Delivery> deliveries;
    private ArrayList<Delivery> deliveriesArchive;

    public DeliveryService() {
        this.clients = new HashMap<>();
        this.employees = new HashMap<>();
        this.orders = new HashMap<>();
        this.deliveries = new ArrayList<>();
        this.deliveriesArchive = new ArrayList<>();
    }

    public void addClient(Client... client) {
        Arrays.stream(client).distinct().forEach(c -> clients.putIfAbsent(c.getId(), c));
    }

    public void addEmployee(Employee... employee) {
        Arrays.stream(employee).distinct().forEach(emp -> employees.putIfAbsent(emp.getId(), emp));
    }

    public void newOrder(Client client, Delivery... delivery) {

        Order order = new Order(delivery, client);

        this.deliveries.addAll(Arrays.asList(delivery));

        clients.putIfAbsent(client.getId(), client);

        orders.putIfAbsent(client.getId(), new ArrayList<>());
        orders.get(client.getId()).add(order);

        int i = 0;
        for (Delivery d : delivery)
            LOGGER.info("Delivery " + ++i + " = " + calculateSingleDeliveryCost(d) + '\n');

        LOGGER.info("Total =  " + calculateTotalOrderCost(order) + '\n');

        client.setAccountBalance((float) (client.getAccountBalance() - calculateTotalOrderCost(order)));
    }

    public void processDeliveries() {

        List<Driver> drivers = employees.values().stream()
                .filter(e -> e instanceof Driver)
                .map(e -> (Driver) e).toList();

        AtomicInteger i = new AtomicInteger();
    /*for (Delivery d : deliveries)
            for (Driver e : drivers)
                if (e.getUsedVehicles().contains(d.getVehicle().getName())) {
                    e.addDelivery(d);
                    deliveriesArchive.add(d);
                    deliveries.set(i.get(), null);
                    i.getAndIncrement();
                }*/

        deliveries.forEach(del ->
                drivers.stream()
                        .filter(driver -> driver.getUsedVehicles().contains(del.getVehicle().getName()))
                        .findFirst().ifPresent(driver -> {
                            driver.addDelivery(del);
                            deliveriesArchive.add(del);
                            deliveries.set(i.get(), null);
                            i.getAndIncrement();
                        }));

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

    public void saveClients() {
        try (FileWriter fw = new FileWriter("data/clients.txt")) {
            for (Client c : clients.values())
                fw.write(c.toString());

            fw.close();

        } catch (Exception e) {
            LOGGER.error("FileWriter error ", e);
        }
    }

    public static double calculateSingleDeliveryCost(Delivery delivery) {
        double result = 0;
        Item item = delivery.getPackage().getItem();

        if (inSizeRange(item, 5))
            result += 10;
        else if (inSizeRange(item, 10))
            result += 50;
        else if (inSizeRange(item, 40))
            result += 60;

        result += (delivery.getPackage().getDistance() * delivery.getVehicle().getRate());
        result *= delivery.getPriority().getRate();

        return result;
    }

    public static double calculateTotalOrderCost(Order order) {
        return order.getDeliveries().stream()
                .mapToDouble(DeliveryService::calculateSingleDeliveryCost)
                .sum();
    }

    private static boolean inSizeRange(Item item, float size) {
        boolean widthOrHeight = (item.getWidth() <= size) || (item.getHeight() <= size);
        boolean depthOrWeight = (item.getDepth() <= size) || (item.getWeight() <= size);

        return widthOrHeight || depthOrWeight;
    }
}