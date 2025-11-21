package org.example.Model;

public class FamilyTree {
    // Fields
    private String treeName;

    // Constructor
    public FamilyTree(String treeName) {
        this.treeName = treeName;
    }

    // Methods
    public String name() { return this.treeName; }

    // list of persons ancestors
    public void displayAncestry(int pID) {
        // start with person, list their parents, and their parents and so on
        // TODO: add ancestry method
    }

}
