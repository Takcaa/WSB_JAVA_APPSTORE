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
    public List<Employee> candidateEmployeeList;
    public List<Project> availableProjectList;
    public List<Project> workingOnProjectList;
    public List<Project> completedProjectList;
    public List<Client> clientList;

    public Game(double budget, LocalDate startDate) {
        this.budget = budget;
        this.daysInZUS = 0;
        this.projectSearchingDays = 0;
        this.employeeList = new ArrayList<>();
        this.candidateEmployeeList = new ArrayList<>();
        this.availableProjectList = new ArrayList<>();
        this.workingOnProjectList = new ArrayList<>();
        this.completedProjectList = new ArrayList<>();
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

    public void doTesting(int index) {
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

    public boolean showWorkingOnProjects() {
        if (workingOnProjectList.isEmpty()) {
            System.out.println("Brak projektow");
            return false;
        } else {
            int i = 0;
            for (Project proj : workingOnProjectList) {
                i++;
                System.out.println(i + ". " + proj);
            }
            return true;
        }
    }

    public boolean showCompletedProjects() {
        if (completedProjectList.isEmpty()) {
            System.out.println("Brak projektow");
            return false;
        } else {
            int i = 0;
            for (Project proj : completedProjectList) {
                i++;
                System.out.println(i + ". " + proj);
            }
            return true;
        }
    }

    public void deliverProjectToClient(int index) {

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

    public boolean pickAvailableProject(int index) {
        if (availableProjectList.get(index).level > 2 && employeeList.isEmpty()) {
            System.out.println("Nie masz pracownikow wiec nie mozesz podjac sie zlecenia lvl 3");
            return false;
        } else {
            workingOnProjectList.add(availableProjectList.get(index));
            availableProjectList.remove(index);
            return true;
        }

    }

    public void goToZUS() {
        daysInZUS++;
        System.out.println("Dni poswiecone na ZUS: " + daysInZUS);
    }

    public void traderGoWork() {
        traderWorkDays++;
        if (traderWorkDays == 5) {
            addClient();
            traderWorkDays = 0;
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
        if (projectSearchingDays == 5) {
            projectSearchingDays = 0;
            addAvilableProject(today);
            System.out.println("Znalazles nowy projekt!");
        } else {
            System.out.println("Dni do nastepnego projektu " + projectSearchingDays + "/5");
        }
    }

    public void hireTester() {
        employeeList.add(new Tester());
    }

    public void hireProgrammer() {
        employeeList.add(new Programmer());
    }

    public void hireTrader() {
        employeeList.add(new Trader());
    }

    public void fireEmployee(int index) {
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

    public void writeMenu(LocalDate today) {
        System.out.println("Data: " + today + " " + today.getDayOfWeek());
        System.out.println("1. Podpisz umowe na realizacje nowego projektu");
        System.out.println("2. Szukaj klientów");
        System.out.println("3. Programuj");
        System.out.println("4. Testuj kod");
        System.out.println("5. Oddaj gotowy projekt");
        System.out.println("6. Zatrudnij nowego pracownika");
        System.out.println("7. Zwolnij pracownika");
        System.out.println("8. Odwiedz ZUS");
    }

}
