package com.solvd.laba.block1.task2;

import com.solvd.laba.block1.task2.interfaces.HasId;
import com.solvd.laba.block1.task2.interfaces.HasName;

public abstract class Person implements HasId, HasName {
    protected String name;
    protected final int id = calcId();
    protected float accountBalance;

    public final String getName() {
        return name;
    }

    public final int getId() {
        return id;
    }

    public final float getAccountBalance() {
        return accountBalance;
    }


    public abstract void printAccountBalance();


    public final void setName(String name) {
        this.name = name;
    }

/*    public final void setId(int id) {
        this.id = id;
    }*/

    public final void setAccountBalance(float balance) {
        accountBalance = balance;
    }
}
