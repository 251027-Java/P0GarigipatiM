package org.example.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Marriage {
    // Fields
    LocalDate marriageDate;

    boolean activeMarriage;
    LocalDate divorceDate; // Is null by default
    List<Person> partners;

    // Constructor
    public Marriage(Person person1, Person person2, LocalDate marriageDate) {
        this.partners = new ArrayList<>();

        this.partners.add(person1);

        this.partners.add(person2);

        this.marriageDate = marriageDate;
        this.activeMarriage = true;
    }

    // Methods
    // add a partner to the marriage
    public void addPartner(Person person) {
        partners.add(person);
    }

    // divorce the marriage
    public void divorce(LocalDate divorceDate) {
        this.divorceDate = divorceDate;
        this.activeMarriage = false;
    }
}
