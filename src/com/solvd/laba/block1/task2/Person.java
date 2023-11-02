package com.solvd.laba.block1.task2;

abstract public class Person {
    protected String name;
    protected int id;
    protected float accountBalance;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public float getAccountBalance() {
        return accountBalance;
    }


    abstract public void printAccountBalance();


    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccountBalance(float balance) {
        accountBalance = balance;
    }
}
