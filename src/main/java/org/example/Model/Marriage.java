package org.example.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Marriage {
    // Fields
    private int id;

    public LocalDate marriageDate;
    public LocalDate divorceDate; // Is null by default

    public List<Person> partners;

    // Constructor
    // TODO: person_marriage constructor
    public Marriage(int id, Person person1, Person person2, LocalDate marriageDate) {
        this.id = id;
        this.partners = new ArrayList<>();

        this.partners.add(person1);

        this.partners.add(person2);

        this.marriageDate = marriageDate;
    }

    public Marriage(int id, LocalDate marriageDate) {
        this.id = id;
        this.marriageDate = marriageDate;
    }

    // Methods
    // getters
    public int getId() { return this.id; }

    // add a partner to the marriage
    public void addPartner(Person person) {
        partners.add(person);
    }

    // divorce the marriage
    public void divorce(LocalDate divorceDate) {
        this.divorceDate = divorceDate;
    }
}
