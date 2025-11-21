package org.example.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {
    // Fields
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

    // Constructor
    public Person(String firstName, String lastName, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;

        parents = new ArrayList<>();
        marriages = new ArrayList<>();
        children = new ArrayList<>();
    }

    public Person(String firstName, String middleName, String lastName, LocalDate birthday) {
        this.middleName = middleName;
        this(firstName, lastName, birthday);
    }

    public Person(int id, String firstName, String lastName, LocalDate birthday) {
        this.id = id;
        this(firstName, lastName, birthday);
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

    public List<Person> getParents() { return this.parents; }

    public List<Person> getChildren() { return this.children; }

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
        if (middleName != null && !middleName.isEmpty()) {
            personStr.append(middleName.charAt(0)).append(" ");
        }
        personStr.append(lastName);

        return new String(personStr);
    }
}
