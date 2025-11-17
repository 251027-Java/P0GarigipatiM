package org.example.Model;

public class FamilyTree {
    // Fields
    Person root;

    // Constructor
    public FamilyTree(Person root) {
        this.root = root;
    }

    // Methods
    // print the tree
    public void displayTree() {
        IO.println("The " + root.getLastName() + " Family Tree");
        // Start at root, build out family tree
        // should have names, relations, (optional) birth year - death year
        // TODO: add displayTree method
    }

    // list of persons ancestors
    public void displayAncestry(int pID) {
        // start with person, list their parents, and their parents and so on
        // TODO: add ancestry method
    }

}
