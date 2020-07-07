package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class Technology {
    public String techName;
    public int days;


    private String[] technos = new String[]{
            "front-end",
            "backend",
            "database",
            "mobile",
            "wordpress",
            "prestashop",
    };

    public Technology() {
        this.techName = technos[ThreadLocalRandom.current().nextInt(0, 5 + 1)];
        this.days = ThreadLocalRandom.current().nextInt(1, 10 + 1);
    }

    @Override
    public String toString() {
        return "Technology{" +
                "techName='" + techName + '\'' +
                ", days=" + days +
                '}';
    }
}
