package com.solvd.laba.block1.task2;

public class Client extends Person {
    public Client(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public void printAccountBalance() {
        if (accountBalance >= 0)
            System.out.println("You have " + accountBalance + " available to pay for deliveries");
        else
            System.out.println("You have unpaid orders with total value of " + (accountBalance * -1));
    }
}
