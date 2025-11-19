package org.example.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {
    // Fields
    // TODO: make id static or auto-increment
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private final LocalDate birthday; //Date of birth

    // Parent Relations
    List<Person> parents;

    // Child Relations
    List<Person> children;

    // Spouse(s) Relations
    List<Marriage> marriages;

    // TODO: decide how middle names and Roman numerals will work and add parents in
    //  constructor

    // Constructor
    public Person(int id, String firstName, String lastName, LocalDate birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;

        parents = new ArrayList<>();
        marriages = new ArrayList<>();
        children = new ArrayList<>();
    }

    public Person(int id, String firstName, String middleName, String lastName,
                  LocalDate birthday) {
        this.middleName = middleName;
        this(id, firstName, lastName, birthday);
    }

    // Methods

    // Getters
    public String getFirstName() { return this.firstName; }

    public String getMiddleName() { return this.middleName; }

    public String getLastName() { return this.lastName; }

    public LocalDate getBirthDate() { return this.birthday; }

    public int getId() { return this.id; }

    // Setters
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void addParents(List<Person> parents) {
        this.parents = parents;
    }

    // Create a marriage
    public void addMarriage(Marriage m) {
        if(marriages == null) {
            marriages = new ArrayList<>();
        }
        marriages.add(m);
    }

    // Create a child
    public void addChild(Person person) {
        if(children == null) {
            children = new ArrayList<>();
        }
        children.add(person);
    }

    @Override
    public String toString() {
        StringBuilder personStr = new StringBuilder(firstName);
        personStr.append(" ");
        if (!middleName.isEmpty()) {
            personStr.append(middleName.charAt(0)).append(" ");
        }
        personStr.append(lastName);

        return new String(personStr);
    }

    // Returns string representation of the person
    // the way they would look on a family tree
    public String personOnTree() {
        return this.toString() + this.birthday + "-";
    }
}
