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
    public Marriage(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
        partners = new ArrayList<>();
    }

    public Marriage(int id, LocalDate marriageDate) {
        this.id = id;
        this(marriageDate);
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

    @Override
    public String toString() {
        return "Marriage ID: " + id + ", Married: " + marriageDate;
    }
}
