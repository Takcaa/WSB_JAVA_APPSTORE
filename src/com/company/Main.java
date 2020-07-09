package com.company;

import com.company.employees.Programmer;
import com.company.employees.Tester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {
        // write your code here a
        boolean nstop = true;
        BufferedReader inStream = new BufferedReader(new InputStreamReader(System.in));
        LocalDate startDate = LocalDate.now();
        Game game = new Game(10000.0, startDate);
        boolean choiceExecuted = false;
        String choice;

        for (LocalDate today = startDate;
             today.isAfter(startDate.minusDays(10)) && nstop;
             today = today.plusDays(1)) {


            if(!choiceExecuted){
                today = today.minusDays(1);
            }
            game.writeMenu(today);
            choice = inStream.readLine();


            if (choice.equals("1")) {
                System.out.println("Wybierz projekt: ");
                game.showAvailableProjects();
                choice = inStream.readLine();
                choiceExecuted = game.pickAvailableProject(Integer.parseInt(choice) - 1);

            } else if (choice.equals("2")) {
                game.lookForProjects(today);
                choiceExecuted = true;
            } else if (choice.equals("3")) {
                if(choiceExecuted = game.showWorkingOnProjects()){
                    System.out.println("Wybierz projekt:");
                    choice = inStream.readLine();
                    game.doProgramming(Integer.parseInt(choice) - 1);
                }

            } else if (choice.equals("4")) {
                if(choiceExecuted = game.showCompletedProjects()){
                    System.out.println("Wybierz projekt do testowania:");
                    choice = inStream.readLine();
                    game.doProgramming(Integer.parseInt(choice) - 1);
                }
            } else if (choice.equals("5")) {

            } else if (choice.equals("6")) {
                System.out.println("Wybierz pracownika do zatrudnienia:");
                game.showEmpolyees(game.candidateEmployeeList);
                try {                               // lapanie spacji w input
                    choice = inStream.readLine();
                    game.hireEmployee(Integer.parseInt(choice)-1);
                }
                catch (Exception e){
                    choice = " ";
                }
                choiceExecuted = true;
            } else if (choice.equals("7")) {
                System.out.println("Wybierz pracownika do zwolnienia:");
                game.showEmpolyees(game.employeeList);
                try {                               // lapanie spacji w input
                    choice = inStream.readLine();
                    game.fireEmployee(Integer.parseInt(choice)-1);
                }
                catch (Exception e){
                    choice = " ";
                }
                choiceExecuted = true;
            } else if (choice.equals("8")) {
                game.goToZUS();
                choiceExecuted = true;
            }



        }
    }
}
