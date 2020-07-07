package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class subcontractor {
    private int type;
    private double mistakeChance;
    private double cost;
    private double possibleDelay; //days

    public subcontractor(int type, double mistakeChance, double cost) {
        this.type = type;
        this.cost = 10 * (type + 1) * 10;
        this.possibleDelay = 0;
        if (this.type == 1){
            this.mistakeChance = 0;
        }
        if (this.type == 2){
            this.mistakeChance = 20;
        }
        if (this.type == 3){
            this.mistakeChance = 0;
            int i =  ThreadLocalRandom.current().nextInt(1, 5 + 1);
            if (i == 3){
                this.possibleDelay = 10;
            }
        }
    }
}
