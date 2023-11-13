package com.solvd.laba.block1.task2;

public abstract class Employee extends Person {
    protected float salary;

    public final float getSalary() {
        return salary;
    }

    public final void setSalary(float salary) {
        this.salary = salary;
    }
}
