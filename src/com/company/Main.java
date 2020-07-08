package com.company;

import com.company.employees.Programmer;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        // write your code here a
        boolean nstop = true;
        LocalDate startDate = LocalDate.now();
        Game game = new Game(10000.0, startDate);
        
        for(LocalDate today = startDate;
            today.isAfter(startDate.minusDays(10)) && nstop;
            today = today.plusDays(1)) {
            game.addClient();
            game.addAvilableProject(today);
            game.showAvailableProjects();
            System.out.println(game);


            System.out.println(game.availableProjectList.get(1).technologiesList.get(1).days);
            game.availableProjectList.get(1).technologiesList.get(1).days -= 1;
            System.out.println(game.availableProjectList.get(1));

            nstop = false;

        }
    }
}
