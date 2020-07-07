package com.company;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
	// write your code here a
    LocalDate today = LocalDate.now();

    Client klient1 = new Client("Maciek");
    Project appstore = new Project(klient1,today);
        System.out.println(appstore);
    }
}
