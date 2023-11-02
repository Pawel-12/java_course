package com.solvd.laba.block1.task2;

public class Driver extends Employee {
    protected Delivery[] deliveries;

    public Driver(String name, int id, float salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public Delivery[] getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(Delivery[] deliveries) {
        this.deliveries = deliveries;
    }

    @Override
    public void printAccountBalance() {
        System.out.println("You have " + accountBalance + " on your salary account, next salary value is " + salary);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Driver \"" + name +
                "\" ID " + id +
                "\taccountbalance = " + accountBalance +
                ", salary = " + salary + "\nAssigned deliveries: \n");

        if (deliveries != null)
            for (Delivery d : deliveries)
                result.append("- ").append(d.toString()).append('\n');
        else
            result.append("*empty* \n");

        return result.toString();
    }
}
