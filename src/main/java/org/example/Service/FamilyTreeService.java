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
    public FamilyTreeService(String treeName) {
        startTree(treeName);
    }

    // Methods
    private void startTree(String treeName) {
        // create new schema or load existing schema
            // create person, marriage, parent_child, and person_to_marriage tables
            // if necessary

        familyTree = new FamilyTree(treeName); // create new family tree object

        // Create person, add to database
        personDAO = new PersonDAO();
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
        Marriage m = new Marriage(-1, familyPerson, spouse, marriageDate);
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
