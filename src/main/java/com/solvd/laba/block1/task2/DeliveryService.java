package com.solvd.laba.block1.task2;

import com.solvd.laba.block1.task2.enums.DeliveryStatus;
import com.solvd.laba.block1.task2.enums.Month;
import com.solvd.laba.block1.task2.enums.Year;
import com.solvd.laba.block1.task2.interfaces.DeliveryCalc;
import com.solvd.laba.block1.task2.interfaces.ItemTest;
import com.solvd.laba.block1.task2.persons.Client;
import com.solvd.laba.block1.task2.persons.Driver;
import com.solvd.laba.block1.task2.persons.Employee;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static com.solvd.laba.block1.task2.Main.LOGGER;

public class DeliveryService {
    private Pair<Year, Month> date;
    private Map<Integer, Client> clients;
    private Map<Integer, Employee> employees;

    private Map<Integer, ArrayList<Order>> orders;

    private ArrayList<Delivery> deliveries;
    private ArrayList<Delivery> deliveriesArchive;

    public DeliveryService() {
        this.date = new MutablePair<>(Year.Y2023, Month.DECEMBER);
        this.clients = new HashMap<>();
        this.employees = new HashMap<>();
        this.orders = new HashMap<>();
        this.deliveries = new ArrayList<>();
        this.deliveriesArchive = new ArrayList<>();
    }

    public void nextMonth() {
        date.setValue(date.getValue().next());

        if (date.getValue() == Month.JANUARY)
            date = new MutablePair<>(date.getKey().next(), date.getValue());

        for (var e : employees.values())
            e.setAccountBalance(e.getAccountBalance() + e.getSalary());
    }

    public Pair<Year, Month> getDate() {
        return date;
    }

    public void setDate(Pair<Year, Month> date) {
        this.date = date;
    }

    public void addClient(Client... clientsToAdd) {
        Arrays.stream(clientsToAdd).distinct().forEach(c -> clients.putIfAbsent(c.getId(), c));
    }

    public void addEmployee(Employee... employeesToAdd) {
        Arrays.stream(employeesToAdd).distinct().forEach(emp -> employees.putIfAbsent(emp.getId(), emp));
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

        /*deliveries.forEach(del ->
                drivers.stream()
                        .filter(driver -> driver.getUsedVehicles().contains(del.getVehicle().getName()))
                        .findFirst().ifPresent(driver -> {
                            del.setStatus(DeliveryStatus.TRANSIT);
                            driver.addDelivery(del);
                            deliveriesArchive.add(del);
                            deliveries.set(i.get(), null);
                            i.getAndIncrement();
                        }));*/

        deliveries.forEach(del -> {
            Driver driver = getDriver(d -> d.getUsedVehicles().contains(del.getVehicle().getName()));
            if (driver != null) {
                del.setStatus(DeliveryStatus.TRANSIT);
                driver.addDelivery(del);
                deliveriesArchive.add(del);
                deliveries.set(i.get(), null);
                i.getAndIncrement();
            }
        });

        deliveries.removeIf(Objects::isNull);
    }

    public Map<Integer, Client> getClients() {
        return clients;
    }

    public Client getClient(Predicate<Client> filter) {
        for (var c : clients.values())
            if (filter.test(c))
                return c;

        return null;
    }

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    public Employee getEmployee(Predicate<Employee> filter) {
        for (var e : employees.values())
            if (filter.test(e))
                return e;

        return null;
    }

    public Driver getDriver(Predicate<Driver> filter) {
        return (Driver) getEmployee(e -> e instanceof Driver && filter.test((Driver) e));
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
        try (FileWriter fw = new FileWriter("src/main/resources/data/clients.txt")) {
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

        ItemTest<Item> allSizes = (i, size) -> {
            boolean widthOrHeight = (i.getWidth() <= size) || (i.getHeight() <= size);
            boolean depthOrWeight = (i.getDepth() <= size) || (i.getWeight() <= size);
            return widthOrHeight || depthOrWeight;
        };

        if (allSizes.test(item, 5))
            result += 10;
        else if (allSizes.test(item, 10))
            result += 50;
        else if (allSizes.test(item, 40))
            result += 60;

        DeliveryCalc<Delivery> calcCost = (del, res) -> {
            res += (del.getPackage().getDistance() * del.getVehicle().getRate());
            res *= del.getPriority().getRate();
            return res;
        };

        return calcCost.calc(delivery, result);
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