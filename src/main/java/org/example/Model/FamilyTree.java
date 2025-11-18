package org.example.Model;

public class FamilyTree {
    // Fields
    String treeName;

    // Constructor
    public FamilyTree(String treeName) {
        this.treeName = treeName;
    }

    // Methods
    // print the tree
    public void displayTree() {
        IO.println("The " + treeName + " Family Tree");
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
