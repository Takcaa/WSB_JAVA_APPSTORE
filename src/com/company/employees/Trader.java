package com.company.employees;

import java.util.concurrent.ThreadLocalRandom;

public class Trader extends Employee {
    private int searchingDays;

    public Trader() {
        this.searchingDays = 1;
        this.salary = ThreadLocalRandom.current().nextInt(2, 4 + 1) * 1000;
    }

    public boolean searchForProject() {
        this.searchingDays++;
        if (searchingDays == 5) {
            searchingDays = 0;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Trader{" +
                "searchingDays=" + searchingDays +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                '}';
    }
}
