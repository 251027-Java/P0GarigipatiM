package org.example.Model;

import java.time.LocalDate;

public class Person {
    // Fields
    private String firstName;
    private String middleName;
    private String lastName;
    private final LocalDate birthday; //Date of birth

    // Parent Relations
    // First parent
    // Second parent

    // Child Relations

    // Spouse(s) Relations

    // TODO: decide how middle names and Roman numerals will work and add parents in
    //  constructor

    // Constructor
    public Person(String firstName, String lastName, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public Person(String firstName, String middleName, String lastName,
                  LocalDate birthday) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    // Methods

    // Getter and Setters
    public String getFirstName() { return this.firstName; }
    public String getMiddleName() { return this.middleName; }
    public String getLastName() { return this.lastName; }
    public LocalDate getBirthDate() { return this.birthday; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    // Create a marriage

    // Create a parent

    // Create a child

    @Override
    public String toString() {
        StringBuilder personStr = new StringBuilder(firstName);
        if (!middleName.isEmpty()) {
            personStr.append(middleName.charAt(0)).append(" ");
        }
        personStr.append(lastName);

        return new String(personStr);
    }
}
