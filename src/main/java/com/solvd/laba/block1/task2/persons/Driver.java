package com.solvd.laba.block1.task2.persons;

import com.solvd.laba.block1.task2.Delivery;
import com.solvd.laba.block1.task2.exceptions.NegativeSalaryException;
import com.solvd.laba.block1.task2.utils.MyLinkedList;

import java.util.ArrayList;
import java.util.function.Predicate;

import static com.solvd.laba.block1.task2.Main.LOGGER;

public final class Driver extends Employee {
    private MyLinkedList<Delivery> deliveries;
    private int nextid = 0;

    private ArrayList<String> usedVehicles;

    public Driver(String name, float salary, ArrayList<String> usedVehicles) throws NegativeSalaryException {
        setSalary(salary);

        this.name = name;
        this.usedVehicles = usedVehicles;
        this.deliveries = new MyLinkedList<>();
    }

    @Override
    public int calcId() {
        return nextid++;
    }

    public MyLinkedList<Delivery> getDeliveries() {
        return deliveries;
    }

    public Delivery getDelivery(Predicate<Delivery> filter) {
        return deliveries.get(filter);
    }

    public ArrayList<String> getUsedVehicles() {
        return usedVehicles;
    }

    public void setDeliveries(MyLinkedList<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public void setUsedVehicles(ArrayList<String> usedVehicles) {
        this.usedVehicles = usedVehicles;
    }

    public void addDelivery(Delivery delivery) {
        this.deliveries.add(delivery);
    }

    @Override
    public void printAccountBalance() {
        LOGGER.info("Hello " + name + " You have " + accountBalance + " on your salary account, next salary value is " + salary + '\n');
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Driver \"" + name +
                "\" ID " + id +
                "\taccountbalance = " + accountBalance +
                ", salary = " + salary + ", usedVehicles = " + usedVehicles + "\nAssigned deliveries: \n");

        if (deliveries != null)
            for (int i = 0; i < deliveries.size(); i++)
                result.append("- ").append(deliveries.get(i).toString()).append('\n');
        else
            result.append("*empty* \n");

        return result.toString();
    }
}
