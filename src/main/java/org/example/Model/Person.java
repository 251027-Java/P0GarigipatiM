package org.example.Model;

import java.time.LocalDateTime;

public class Person {
    // Fields
    private String firstName;
    private String middleName;
    private String lastName;
    private final LocalDateTime birth; //Date and Time of birth


    // Constructor
    public Person(String firstName, String lastName, LocalDateTime birth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
    }

    public Person(String firstName, String middleName, String lastName,
                  LocalDateTime birth) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birth = birth;
    }

    // Methods

    // Getter and Setters
    public String getFirstName() { return this.firstName; }
    public String getMiddleName() { return this.middleName; }
    public String getLastName() { return this.lastName; }
    public LocalDateTime getBirthDate() { return this.birth; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    // Create a marriage

    // Create a parent

    // Create a child
}
