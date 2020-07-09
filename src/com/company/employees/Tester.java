package com.company.employees;

import java.util.concurrent.ThreadLocalRandom;

public class Tester extends Employee {

    public Tester() {
        this.salary = ThreadLocalRandom.current().nextInt(2, 4 + 1) * 1000;
    }

    @Override
    public String toString() {
        return "Tester{" +
                "salary=" + salary +
                '}';
    }
}
