package org.example;

import org.example.Service.FamilyTreeService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner scanner = new Scanner(System.in);

    static void main() {
        FamilyTreeService fts;

        String tree = getTreeName();
        fts = new FamilyTreeService(tree); // make new FamilyTreeService

        // Menu and performing actions
        int option;
        do {
            option = menu(); // Get user option
            IO.println();

            // Perform action
            switch(option) {
                case 1:
                    scanner.nextLine();
                    fts.addChild(scanner);
                    break;
                case 2:
                    IO.println("Add a marriage");
//                    fts.addMarriage();
                    break;
                case 3:
                    IO.println("End a marriage");
//                    fts.divorceSpouse();
                    break;
                case 4:
                    IO.println("Update death");
//                    fts update a death
                    break;
            }
        } while (option != 5);

        IO.println("Program Ended.");
    }

    // Get tree name from user
    public static String getTreeName() {
        // TODO: add try-catch and input validation

        // Ask user if they want to create a new tree or use an existing tree
        IO.println("Do you want to create a new family tree or look at an " +
                "existing tree?\nPlease enter name of family tree that already exists " +
                "or 'new' for a new family tree.");
        IO.print("Enter here: ");
        String tree = scanner.next();
        IO.println();

        // Get name of new tree
        if(tree.equalsIgnoreCase("new")) {
            IO.println("Please enter the last name of this family as the name of the " +
                    "family tree (input should be one word.");
            tree = scanner.next();
        }

        return tree;
    }

    // Print menu and get user's option
    public static int menu() {

        // Print menu
        IO.println("--- Menu ---");
        IO.println("1 - Add a person (someone had kids)");
        IO.println("2 - Add a marriage (someone got married)");
        IO.println("3 - End a marriage (someone got divorced)");
        IO.println("4 - Update a death (someone died)");
        IO.println("5 - Exit");

        // Get option
        IO.print("\nEnter an option: ");
        return scanner.nextInt();
    }
}