package org.example.Service;

import org.example.DAO.MarriageDAO;
import org.example.Model.Marriage;
import org.example.Model.Person;

import java.time.LocalDate;

public class MarriageService {
    // Fields
    private PersonService personService;
    private MarriageDAO marriageDAO;

    // Constructor
    public MarriageService() {
        personService = new PersonService();
        marriageDAO = new MarriageDAO();
    }

    // Methods
    public void createMarriage(String partner1Name, String partner2Name, LocalDate marriageDate) {
        if(personService.personExists(partner1Name) &&
                personService.personExists(partner2Name)) {

            // add to person_marriage table
            Marriage m = new Marriage(1, marriageDate);
            Person partner1 = personService.getPerson(partner1Name);
            partner1.addMarriage(m);
            m.partners.add(partner1);

            Person partner2 = personService.getPerson(partner2Name);
            partner2.addMarriage(m);
            m.partners.add(partner2);

            marriageDAO.addMarriage(m);
        }
    }
}
