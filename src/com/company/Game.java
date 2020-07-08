package com.company;

import com.company.employees.Employee;
import com.company.employees.Programmer;
import com.company.employees.Tester;
import com.company.employees.Trader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public double budget;
    public int daysInZUS;
    public int traderWorkDays;
    public int projectSearchingDays;
    public List<Employee> employeeList;
    public List<Project> availableProjectList;
    public List<Project> workingOnProjectList;
    public List<Client> clientList;

    public Game(double budget, LocalDate startDate) {
        this.budget = budget;
        this.daysInZUS = 0;
        this.projectSearchingDays = 0;
        this.employeeList = new ArrayList<>();
        this.availableProjectList = new ArrayList<>();
        this.workingOnProjectList = new ArrayList<>();
        this.clientList = new ArrayList<>();
        //Initialize start projects set and clients for projects
        for (int i = 0; i < 3; i++) {
            addClient();
            addAvilableProject(startDate);
        }

    }

    public void addAvilableProject(LocalDate currentTime) {
        this.availableProjectList.add(new Project(clientList.get(clientList.size() - 1), currentTime));
    }

    public void addClient() {
        clientList.add(new Client("Maciek"));
    }

    public void doTesting(int index){
        workingOnProjectList.get(index).setTested(true);
    }

    public void doProgramming(int index) {
        int i = 0; //technologies iterator
        boolean lookForTechnology = true;
        while (lookForTechnology) {
            if (availableProjectList.get(index).technologiesList.get(i).days != 0
                    && availableProjectList.get(index).technologiesList.get(i).techName != "mobile") {
                availableProjectList.get(index).technologiesList.get(i).days -= 1;
                lookForTechnology = false;
            }
        }
    }

    public void deliverProjectToClient(int index){

    }

    /*private boolean checkForTesters(){
        int testers = 0;
        int programmers = 0;
        for (Employee employ : employeeList){
            if(employ instanceof Tester){
                testers++;
            }
        }
    }*/

    public void showAvailableProjects() {
        int i = 0;
        for (Project proj : availableProjectList) {
            i++;
            System.out.println(i + ". " + proj);
        }
    }

    public void pickAvailableProject(int index) {
        workingOnProjectList.add(availableProjectList.get(index));
        availableProjectList.remove(index);
    }

    public void goToZUS() {
        daysInZUS++;
    }
    public void traderGoWork(){
        traderWorkDays++;
        if (traderWorkDays == 5){
            addClient();
            traderWorkDays=0;
        }
    }

    public boolean zusOkCheck() {
        if (daysInZUS < 2) {
            daysInZUS = 0;
            return true;
        }
        return false;
    }

    public void lookForProjects(LocalDate today) {
        projectSearchingDays++;
        if (projectSearchingDays == 5){
            projectSearchingDays =0;
            addAvilableProject(today);
        }
    }

    public void hireTester(){
        employeeList.add(new Tester());
    }
    public void hireProgrammer(){
        employeeList.add(new Programmer());
    }
    public void hireTrader(){
        employeeList.add(new Trader());
    }
    public void fireEmployee(int index){
        employeeList.remove(index);
    }
    @Override
    public String toString() {
        return "Game{" +
                "budget=" + budget +
                ", daysInZUS=" + daysInZUS +
                // ", employeeList=" + employeeList +
                ", projectList=" + availableProjectList +
                // ", clientList=" + clientList +
                '}';
    }
}
