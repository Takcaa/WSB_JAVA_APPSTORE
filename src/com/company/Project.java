package com.company;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Project {
    public String name;
    public int level;
    public List<Technology> technologiesList; //nazwa techno + liczba dni potrzebna na realizacje
    public Client projClient;
    private LocalDate deadline;
    private double penalty;
    private double price;
    private int invoiceDate;  // ilosc dni miedzy oddaniem projektu a zaplata




    public Project(Client newClient, LocalDate currentTime) {
        this.projClient = newClient;
        this.name = "Projekt " + ThreadLocalRandom.current().nextInt(0, 16 + 1);
        this.level = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        technologiesList = new ArrayList<Technology>();
        for(int i =0; i <= this.level; i++){
            technologiesList.add(new Technology());
        }
        this.penalty = 2000 * this.level;
        this.price = this.penalty * this.level;
        this.deadline = currentTime.plusDays(5*this.level);

        if (newClient.getInvoiceDelayChance() > 0){ //losowanie opoznienia w zaplacie
            double rand = ThreadLocalRandom.current().nextDouble(0,1+1);
            this.invoiceDate = ThreadLocalRandom.current().nextInt(1, 5 + 1); //brak opoznienia
            if (rand < 0.3){
                this.invoiceDate = ThreadLocalRandom.current().nextInt(1, 5 + 1) + 7;
            }
            if(rand < 0.95 & newClient.getType() == 3){
                this.invoiceDate = ThreadLocalRandom.current().nextInt(1, 5 + 1) + 30;
            }
        }










    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(int invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", technologiesList=" + technologiesList +
                ", projClient=" + projClient +
                ", deadline=" + deadline +
                ", penalty=" + penalty +
                ", price=" + price +
                ", invoiceDate=" + invoiceDate +
                '}';
    }
}
