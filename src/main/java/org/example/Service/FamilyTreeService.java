package org.example.Service;

import org.example.DAO.PersonDAO;

import org.example.Model.FamilyTree;
import org.example.Model.Marriage;
import org.example.Model.Person;
import org.example.Repository.Repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FamilyTreeService {
    // Fields
    private FamilyTree ft;
    private PersonService personService;
    private MarriageService marriageService;
    private Repo repo;

    // Constructors
    public FamilyTreeService(String tree) {
        setUpDatabase(tree);
    }

    // Methods
    private void setUpDatabase(String tree) {
        ft = new FamilyTree(tree);
        personService = new PersonService();
        marriageService = new MarriageService();

        // create schema and tables
        repo = new Repo(tree);
    }

    // get person information from user and add to tree
    public void addPerson(Scanner scanner) {

        // ask user to enter person information
        IO.println("Adding new person to the family tree");
        IO.println("Please enter the person's full name.");
        String fullName = scanner.nextLine();

        // Split into first and last name
        String[] nameSplit = fullName.split(" ");
        String firstname = nameSplit[0];
        String middlename = (nameSplit.length > 2) ? nameSplit[1] : "";
        String lastname = nameSplit[nameSplit.length - 1];

        // Check that last name matches the name of the tree
        if(!ft.name().equalsIgnoreCase(lastname)) {
            IO.println("This person has a different last name. Please make sure last " +
                    "name matches family tree name.");
            return;
        }

        // Check if this person already exists
        if(personService.personExists(firstname, lastname)) {
            IO.println("This person already exists in the tree.");
            return;
        }

        List<Person> parents = new ArrayList<>();
        IO.println("Does this person have parents on the tree? Y/N");
        String answer = scanner.nextLine();
        if(answer.equalsIgnoreCase("Y")) {
            // enter parent1 name, enter parent2 name
            IO.println("Please enter the full name of this person's first parent.");
            String parent1Name = scanner.nextLine();

            IO.println("Please enter the full name of this person's second parent.");
            String parent2Name = scanner.nextLine();

            // Check if the parents exist in the tree
            Person parent1 = personService.getPerson(parent1Name);
            Person parent2 = personService.getPerson(parent2Name);
            if (parent1 == null || parent2 == null) {
                IO.println("One or both of this person's parents aren't on the tree. Add them first.");
                return;
            }

            // Make parents list
            parents.add(parent1);
            parents.add(parent2);
        }

        // enter persons date of birth
        IO.println("Please enter date of birth in YYYY-MM-DD format:");
        String date = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(date);
        IO.println(birthDate);

        // use this information to create a new person and add them
        Person p = new Person(1, firstname, middlename, lastname, birthDate);
        personService.addPerson(p, parents);
    }

    // get information from user about marriage and create it
    public void addMarriage(Scanner scanner) {
        // Get people being married
        // Get marriage date

        // add marriage
        // create marriage object
        // add person.id to marriage object
        // add to person.marriages
        // update database: add an entry to marriage table
        // and add entries to person_marriage table
        IO.println("Adding a new marriage to the family tree");

        IO.println("Enter the full name of the partner on the tree:");
        String partner1Name = scanner.nextLine();

        // Check that last name matches the name of the tree
        String[] split = partner1Name.split(" ");
        if(!ft.name().equalsIgnoreCase(split[split.length - 1])) {
            IO.println("This person has a different last name. Please make sure last " +
                    "name matches family tree name.");
            return;
        }

        IO.println("Enter the full name of the second partner:");
        String partner2Name = scanner.nextLine();

        // Ask for marriage date
        IO.println("Enter the marriage date (YYYY-MM-DD):");
        LocalDate marriageDate = LocalDate.parse(scanner.nextLine());

        // Delegate to service layer
        marriageService.createMarriage(partner1Name, partner2Name, marriageDate);
    }
}