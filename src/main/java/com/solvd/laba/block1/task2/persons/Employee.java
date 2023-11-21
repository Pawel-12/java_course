package com.solvd.laba.block1.task2.persons;

import com.solvd.laba.block1.task2.exceptions.NegativeSalaryException;

public abstract class Employee extends Person {
    protected float salary;

    public final float getSalary() {
        return salary;
    }

    public final void setSalary(float salary) throws NegativeSalaryException {
        if (salary < 0)
            throw new NegativeSalaryException("Salary cannot be negative!");
        this.salary = salary;
    }
}
