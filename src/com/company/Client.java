package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class Client {
    public String name;
    private int type;
    private double invoiceDelayChance;
    private double delayPenaltyChance;
    private double breakContactChance;
    private double notPaid;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getInvoiceDelayChance() {
        return invoiceDelayChance;
    }

    public void setInvoiceDelayChance(double invoiceDelayChance) {
        this.invoiceDelayChance = invoiceDelayChance;
    }

    public double getDelayPenaltyChance() {
        return delayPenaltyChance;
    }

    public void setDelayPenaltyChance(double delayPenaltyChance) {
        this.delayPenaltyChance = delayPenaltyChance;
    }

    public double getBreakContactChance() {
        return breakContactChance;
    }

    public void setBreakContactChance(double breakContactChance) {
        this.breakContactChance = breakContactChance;
    }

    public double getNotPaid() {
        return notPaid;
    }

    public void setNotPaid(double notPaid) {
        this.notPaid = notPaid;
    }

    public Client(String name) {
        this.name = name;
        this.type = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        ;
        if (this.type == 1) {//chill
            this.invoiceDelayChance = 30;
            this.delayPenaltyChance = 20;
            this.breakContactChance = 0;
            this.notPaid = 0;
        }
        if (this.type == 2) {//wymagajacy
            this.invoiceDelayChance = 0;
            this.delayPenaltyChance = 0;
            this.breakContactChance = 50;
            this.notPaid = 0;
        }
        if (this.type == 3) { //skrw
            this.invoiceDelayChance = 30;
            this.delayPenaltyChance = 20;
            this.breakContactChance = 100;
            this.notPaid = 1;
        }
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", invoiceDelayChance=" + invoiceDelayChance +
                ", delayPenaltyChance=" + delayPenaltyChance +
                ", breakContactChance=" + breakContactChance +
                ", notPaid=" + notPaid +
                '}';
    }
}
