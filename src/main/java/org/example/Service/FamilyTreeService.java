package org.example.Service;

import org.example.DAO.PersonDAO;

import org.example.Model.FamilyTree;
import org.example.Model.Marriage;
import org.example.Model.Person;

import java.time.LocalDate;

public class FamilyTreeService {
    // Fields
    FamilyTree familyTree;
    PersonDAO personDAO;

    // Constructor
    public FamilyTreeService() {
        personDAO = new PersonDAO();
        startTree();
    }

    // Methods
    private void startTree() {
        // Get the root person from user
        Person root = null;
        familyTree = new FamilyTree(root);
        // Create person, add to database
    }

    // Add new child to tree
    // parameters: parents, and person object
    public void addChild(Person parent1, Person parent2, Person child) {
        // TODO: addChild method
        // edit the family tree
    }

    // Add marriage relation to family tree
    public void addMarriage(Person familyPerson, Person spouse, LocalDate marriageDate) {
        // TODO: add input validation
        Marriage m = new Marriage(familyPerson, spouse, marriageDate);
        familyPerson.addMarriage(m);
        spouse.addMarriage(m);
    }

    // change marriage into divorced relation
    public void divorceSpouse(Person familyPerson, Person divorced) {
        // TODO: add divorceSpouse method
        // edit the family tree
    }

    public void displayFamilyTree() {
        familyTree.displayTree();
    }
}
