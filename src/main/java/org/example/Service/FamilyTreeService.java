package org.example.Service;

import org.example.DAO.PersonDAO;

import org.example.Model.FamilyTree;
import org.example.Model.Marriage;
import org.example.Model.Person;

import java.util.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class FamilyTreeService {
    // Fields
    private FamilyTree ft;
    private PersonDAO personDAO;

    // Constructors
    public FamilyTreeService(String tree) {
        setUpDatabase(tree);
    }

    // Methods
    private void setUpDatabase(String tree) {
        ft = new FamilyTree(tree);
        personDAO = new PersonDAO();

        // create schema if doesn't exist
        // create tables if they don't exist
    }

    // get person information from user and add to tree
    public void addChild(Scanner scanner) {

        // ask user to enter person information
        IO.println("Adding new person to the family tree");
        IO.println("Please enter the person's full name.");
        String fullName = scanner.nextLine();

        // Split into first and last name
        String[] nameSplit = fullName.split(" ");
        String firstname = nameSplit[0];
        String lastname = nameSplit[1];

        // Check that last name matches the name of the tree
        if(!ft.name().equalsIgnoreCase(lastname)) {
            IO.println("This person has a different last name. Please make sure last " +
                    "name matches family tree name.");
            return;
        }

        // Check if this person already exists
        if(personDAO.getPerson(firstname, lastname) != null) {
            IO.println("This person already exists in the tree.");
            return;
        }

        // TODO: address null parents for the first generation of the tree

        // enter parent1 name, enter parent2 name
        IO.println("Please enter the full name of this person's first parent.");
        String parent1Name = scanner.nextLine();
        nameSplit = parent1Name.split(" ");
        String parentOneFirst = nameSplit[0];
        String parentOneLast = nameSplit[1];

        IO.println("Please enter the full name of this person's second parent.");
        String parent2Name = scanner.nextLine();
        nameSplit = parent2Name.split(" ");
        String parentTwoFirst = nameSplit[0];
        String parentTwoLast = nameSplit[1];

        // Check if the parents exist in the tree
        if(personDAO.getPerson(parentOneFirst, parentOneLast) == null ||
                personDAO.getPerson(parentTwoFirst, parentTwoLast) == null) {
            IO.println("One or both of this person's parents aren't on the tree. Add them first.");
            return;
        }

        // enter persons date of birth
        IO.println("Please enter date of birth in YYYY-MM-DD format:");
        String date = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(date);
        IO.println(birthDate);

        // use this information to create a new person
        Person p = new Person(1, fullName.substring(1, 5), fullName, birthDate);
        // add parents to person object
        // parent_child dao will add 2 new relations using ids
        // persondao will add person to table
    }
}