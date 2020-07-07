package com.company;

import com.company.employees.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public double budget;
    public int daysInZUS;
    public List<Employee> employeeList;
    public List<Project> avilableProjectList;
    public List<Client> clientList;

    public Game(double budget) {
        this.budget = budget;
        this.daysInZUS = 0;
        this.employeeList = new ArrayList<>();
        this.avilableProjectList = new ArrayList<>();
        this.clientList = new ArrayList<>();

    }

    public void addAvilableProject(LocalDate currentTime) {
        this.avilableProjectList.add(new Project(clientList.get(clientList.size() - 1), currentTime));
    }

    public void addClient() {
        clientList.add(new Client("Maciek"));
    }

    @Override
    public String toString() {
        return "Game{" +
                "budget=" + budget +
                ", daysInZUS=" + daysInZUS +
                ", employeeList=" + employeeList +
                ", projectList=" + avilableProjectList +
                ", clientList=" + clientList +
                '}';
    }
}
