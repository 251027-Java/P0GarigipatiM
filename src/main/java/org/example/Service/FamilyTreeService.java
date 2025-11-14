package org.example.Service;

import org.example.DAO.PersonDAO;
import org.example.Model.FamilyTree;
import org.example.Model.Person;

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
        // edit the family tree
    }

    // Add marriage relation to family tree
    public void addSpouse(Person familyPerson, Person spouse) {
        // edit family tree
    }

    // change marriage into divorced relation
    public void divorceSpouse(Person familyPerson, Person divorced) {
        // edit the family tree
    }

    public void displayFamilyTree() {
        familyTree.displayTree();
    }
}
