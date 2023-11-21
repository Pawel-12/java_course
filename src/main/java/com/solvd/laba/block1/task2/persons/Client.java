package com.solvd.laba.block1.task2.persons;

import static com.solvd.laba.block1.task2.Main.LOGGER;

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
            LOGGER.info("You have " + accountBalance + " available to pay for deliveries\n");
        else
            LOGGER.info("You have unpaid orders with total value of " + (accountBalance * -1) + '\n');
    }

    @Override
    public String toString() {
        return "Client \"" + name +
                "\" ID " + id +
                "\taccountbalance = " + accountBalance + '\n';
    }
}
