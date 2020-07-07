package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class subcontractor {
    private int type;
    private double mistakeChance;
    private double cost;
    private double possibleDelay; //days

    public int getType() {
        return type;
    }

    public double getMistakeChance() {
        return mistakeChance;
    }

    public double getCost() {
        return cost;
    }

    public double getPossibleDelay() {
        return possibleDelay;
    }

    public subcontractor(int type) {
        this.type = type;
        this.cost = 10 * (type + 1) * 10;
        this.possibleDelay = 0;
        if (this.type == 3){                //najlepszy
            this.mistakeChance = 0;
        }
        if (this.type == 2){                //sredni
            this.mistakeChance = 20;
        }
    if (this.type == 1){                    //najtanszy/najslabszy
            this.mistakeChance = 0;
            int i =  ThreadLocalRandom.current().nextInt(1, 5 + 1);
            if (i == 3){
                this.possibleDelay = 10;
            }
        }
    }
}
