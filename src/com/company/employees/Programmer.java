package com.company.employees;

import com.company.Technology;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Programmer extends Employee {
    public List<Technology> technologiesList;

    public Programmer() {
        this.salary = ThreadLocalRandom.current().nextInt(2, 4 + 1) * 1000;
        this.technologiesList = new ArrayList<Technology>();
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(2, 4 + 1); i++) {
            addTechnology();
        }
    }


    private void addTechnology() {
        Technology newTech = new Technology();
        if (!checkIfExists(newTech)) {
            technologiesList.add(newTech);
        }

    }

    private boolean checkIfExists(Technology newTech) {
        for (int i = 0; i < technologiesList.size(); i++) {
            if (newTech.techName == technologiesList.get(i).techName) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Programmer{" +
                "technologiesList=" + technologiesList +
                '}';
    }
}
