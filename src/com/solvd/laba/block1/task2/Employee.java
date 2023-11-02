package com.solvd.laba.block1.task2;

abstract public class Employee extends Person {
    protected float salary;
    
    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
