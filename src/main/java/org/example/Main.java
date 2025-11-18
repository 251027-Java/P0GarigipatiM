package org.example;

import org.example.Service.FamilyTreeService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        // start program as a loop
        // create family tree and add person
        // create more people and add relations
        // print the family tree

        FamilyTreeService fts;
        Scanner scanner = new Scanner(System.in);

        // Ask user if they want to create a new tree or use an existing tree
        IO.println("Do you want to create a new family tree or look at an " +
                "existing tree?\nPlease enter name of family tree that already exists " +
                "or 'new' for a new family tree");
        String tree = scanner.next();

        // Get name of new tree
        if(tree.equalsIgnoreCase("new")) {
            IO.println("What is the name of your new tree? Please enter as one word.");
            tree = scanner.next();
        }

        fts = new FamilyTreeService(tree); // make new FamilyTreeService

        // Menu to ask user what they want to do with the tree
        /*
        do
            Print menu:
            1 - someone had kids
            2 - someone got married
            3 - someone got divorced
            4 - someone died
            5 - end program

            Get input
            switch(input)
                1: fts.personborn
                2: fts.startmarriage
                3: fts.endmarriage
                4: fts.persondied
        while (option !=5);
         */

        scanner.close();
    }

    public static int menu()
}