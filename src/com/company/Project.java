package com.company;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.time.LocalDate;
import java.util.ArrayList;
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


    private String[] names = new String[]{
            "Projekt 1",
            "Projekt 2",
            "Projekt 3",
            "Projekt 4",
            "Projekt 5",
            "Projekt 6",
            "Projekt 7",
            "Projekt 8",
            "Projekt 9",
            "Projekt 10",
            "Projekt 11",
            "Projekt 12",
            "Projekt 13",
            "Projekt 14",
            "Projekt 15",
            "Projekt 16",
            "Projekt 17",
    };
    private String[] technos = new String[]{
            "front-end",
            "backend",
            "database",
            "mobile",
            "wordpress",
            "prestashop",
    };



    public Project(Client newClient, LocalDate currentTime) {
        this.name = names[ThreadLocalRandom.current().nextInt(0, 16 + 1)];
        this.level = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        technologiesList = new ArrayList<Technology>();
        for(int i =0; i <= this.level; i++){
            technologiesList.add(
            new Technology(technos[ThreadLocalRandom.current().nextInt(0, 5 + 1)],
            ThreadLocalRandom.current().nextInt(1, 3 + 1)));
        }
        this.penalty = 2000 * this.level;
        this.price = this.penalty * 3;
        this.deadline = currentTime.plusDays(5*this.level);









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
}
