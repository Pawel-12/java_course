package com.solvd.laba.block1.task2.persons;

public class Client extends Person {
    private int nextid = 0;

    public Client(String name) {
        this.name = name;
        //this.id = id;
    }

    @Override
    public int calcId() {
        return nextid++;
    }

    @Override
    public void printAccountBalance() {
        if (accountBalance >= 0)
            System.out.println("You have " + accountBalance + " available to pay for deliveries\n");
        else
            System.out.println("You have unpaid orders with total value of " + (accountBalance * -1) + '\n');
    }
}
