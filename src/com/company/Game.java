package com.company;

import com.company.employees.Employee;
import com.company.employees.Programmer;
import com.company.employees.Tester;
import com.company.employees.Trader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
    public List<Project> deliveredProjectList;
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
        this.deliveredProjectList = new ArrayList<>();
        this.clientList = new ArrayList<>();
        //Initialize start projects set and clients for projects
        for (int i = 0; i < 2; i++) {
            addClient();
            addAvilableProject(startDate);
            addAvilableProject(startDate);
            this.candidateEmployeeList.add(new Tester());
            this.candidateEmployeeList.add(new Programmer());
            this.candidateEmployeeList.add(new Trader());
        }

    }

    public void addAvilableProject(LocalDate currentTime) {
        this.availableProjectList.add(new Project(clientList.get(clientList.size() - 1), currentTime));


        this.completedProjectList.add(new Project(clientList.get(clientList.size() - 1), currentTime));
    }

    public void addClient() {
        clientList.add(new Client("Maciek"));
    }

    public void doTesting(int index) {
        completedProjectList.get(index).setTested(true);
    }

    public boolean doProgramming(int index) {
        int i = 0; //technologies iterator
        while (i < workingOnProjectList.get(index).technologiesList.size()) {
            if (workingOnProjectList.get(index).technologiesList.get(i).days != 0
                    && workingOnProjectList.get(index).technologiesList.get(i).techName != "mobile") {

                workingOnProjectList.get(index).technologiesList.get(i).days -= 1;
                return true;
            }
            i++;
        }
        return false;
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
                System.out.println(i + ". " + proj + " Tested= " + proj.isTested());
            }
            return true;
        }
    }

    public void showEmpolyees(List<Employee> list) {
        if (list.size() < 1) {
            System.out.println("Brak pracowników. Kliknij aby kontynuowac");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
    }

    public void deliverProjectToClient(int index, LocalDate currentTime) {
        Client client = completedProjectList.get(index).projClient;
        if (checkForTesters()) {
            completedProjectList.get(index).setTested(true);
        }
        Project project = completedProjectList.get(index);

        int i = ThreadLocalRandom.current().nextInt(1, 100 + 1);
        if (!project.isTested()) {
            if (client.getType() == 3) {
                budget -= project.getPenalty();
                completedProjectList.remove(index);
                System.out.println("Oddales nieprzetestowany kod. Klient zerwal wspolprace");
            }
            if (client.getType() == 1) {
                projectDeliver(index);
            }
            if (client.getType() == 2 && i >= 50) {
                budget -= project.getPenalty();
                completedProjectList.remove(index);
                System.out.println("Oddales nieprzetestowany kod. Klient zerwal wspolprace");
            } else if (client.getType() == 2 && i <= 50) {
                projectDeliver(index);
            }
        } else if (project.isTested() && currentTime.compareTo(project.getDeadline()) < 0) {
            if (client.getType() == 1) {
                LocalDate checkDate = currentTime;
                checkDate = checkDate.plusDays(7);
                if (i >= 20 && checkDate.compareTo(project.getDeadline()) < 0) { //20% szans na brak kary po terminie
                    budget -= project.getPenalty();
                }
                if (i > 70) {
                    project.setInvoiceDate(project.getInvoiceDate() + 7);
                }
                projectDeliver(index);
            }
            if (client.getType() == 2) {
                projectDeliver(index);
            }
            if (client.getType() == 3) {
                if (i < 1) {
                    project.setInvoiceDate(10000);
                } else if (i < 5) {
                    project.setInvoiceDate(project.getInvoiceDate() + 30);
                } else if (i < 30) {
                    project.setInvoiceDate(project.getInvoiceDate() + 7);
                }
                projectDeliver(index);

            }

        } else if (project.isTested() && currentTime.compareTo(project.getDeadline()) >= 0) {
            if (client.getType() == 1) ;
        }
    }

    private void projectDeliver(int index) {
        deliveredProjectList.add(completedProjectList.get(index));
        completedProjectList.remove(index);
    }

    private boolean checkForTesters() {
        int testers = 0;
        int programmers = 0;
        for (Employee employ : employeeList) {
            if (employ instanceof Tester) {
                testers++;
            }
            if (employ instanceof Programmer) {
                programmers++;
            }
        }
        if (testers * 3 >= programmers) {
            return true;
        }
        return false;
    }

    private boolean progrmmerDoWork() {
        int i = 0; //technologies iterator
        for (int j = 0; j < workingOnProjectList.size(); j++) {
            while (i < workingOnProjectList.get(j).technologiesList.size()) {
                if (workingOnProjectList.get(j).technologiesList.get(i).days != 0) {
                    workingOnProjectList.get(j).technologiesList.get(i).days -= 1;
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    public void employeesDoWork(LocalDate today) {
        if (today.getDayOfWeek().toString() != "SUNDAY" && today.getDayOfWeek().toString() != "SUNDAY") {
            for (Employee employ : employeeList) {
                if (employ instanceof Programmer) {
                    progrmmerDoWork();
                }
                if (employ instanceof Trader) {
                    traderGoWork();
                }
            }
        }
    }

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

    public void goToZUS(LocalDate today) {
        if (today.getDayOfMonth() == 1) {
            daysInZUS = 0;
        }
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

    public void hireEmployee(int index) {
        employeeList.add(candidateEmployeeList.get(index));
        budget -= candidateEmployeeList.get(index).salary * 0.1;
        candidateEmployeeList.remove(index);
    }

    public void fireEmployee(int index) {
        budget -= employeeList.get(index).salary * 0.3;
        employeeList.remove(index);

    }

    public boolean checkIfProjDone(int index) {
        for (int i = 0; i < workingOnProjectList.get(index).technologiesList.size(); i++) {
            if (workingOnProjectList.get(index).technologiesList.get(i).days != 0) {
                return false;
            }
        }
        completedProjectList.add(workingOnProjectList.get(index));
        workingOnProjectList.remove(index);
        return true;
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
        System.out.println("Data: " + today + " " + today.getDayOfWeek() + " Budget: " + this.budget);
        System.out.println("1. Podpisz umowe na realizacje nowego projektu");
        System.out.println("2. Szukaj klientów");
        System.out.println("3. Programuj");
        System.out.println("4. Testuj kod");
        System.out.println("5. Oddaj gotowy projekt");
        System.out.println("6. Zatrudnij nowego pracownika");
        System.out.println("7. Zwolnij pracownika");
        System.out.println("8. Odwiedz ZUS");
    }

    public boolean isOk(LocalDate today) {
        if (this.budget < 0) {
            System.out.println("GAME OVER: Bankrut");
            return false;
        }
        if (today.getDayOfMonth() == 1 && daysInZUS < 2) {
            System.out.println("GAME OVER: Nie byles w zusie w tym miesiacu, przegrywasz");
            return false;
        } else if (today.getDayOfMonth() == 1) {
            daysInZUS = 0;
        }
        if (checkWin()) {
            System.out.println("Wygrales");
            return false;
        }
        return true;
    }

    private boolean checkWin() {
        int paidProjects = 0;
        for (Project project : deliveredProjectList) {
            if (project.getInvoiceDate() <= 0) {
                paidProjects++;
            }
        }
        if (paidProjects >= 3) {
            return true;
        } else {
            return false;
        }
    }

}
