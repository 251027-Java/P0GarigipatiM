package org.example.Service;

import org.example.DAO.MarriageDAO;
import org.example.DAO.PersonMarriageDAO;
import org.example.Model.Marriage;
import org.example.Model.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MarriageService {
    // Fields
    private final PersonService personService;
    private final MarriageDAO marriageDAO;
    private final PersonMarriageDAO pmDAO;

    // Constructor
    public MarriageService(String tree) {
        personService = new PersonService(tree);
        marriageDAO = new MarriageDAO(tree);
        pmDAO = new PersonMarriageDAO(tree);
    }

    // Methods
    public void createMarriage(String partner1Name, String partner2Name, LocalDate marriageDate) {
        if(personService.personExists(partner1Name) &&
                personService.personExists(partner2Name)) {
            // Make sure partners exist in table
            // Add them to marriage object
            Marriage m = new Marriage(marriageDate);
            Person partner1 = personService.getPerson(partner1Name);
            partner1.addMarriage(m);
            m.partners.add(partner1);

            Person partner2 = personService.getPerson(partner2Name);
            partner2.addMarriage(m);
            m.partners.add(partner2);

            // Add marriage and person_marriage relations to table
            int mID = marriageDAO.addMarriage(m);
            pmDAO.addPersonMarriage(partner1.getId(), mID);
            pmDAO.addPersonMarriage(partner2.getId(), mID);
        }
    }

    public Marriage getMarriage(int marriageID) {
        return marriageDAO.getMarriage(marriageID);
    }

    public List<Marriage> getMarriages(String firstname, String lastname) {
        List<Marriage> marriageList = new ArrayList<>();
        if(personService.personExists(firstname, lastname)) {
            // Get person id
            int pID = personService.getPerson(firstname, lastname).getId();

            // Use person id to populate list of marriages
            pmDAO.getMarriagesIDList(pID).forEach(
                    (i) -> marriageList.add(marriageDAO.getMarriage(i)));
        }
        return marriageList;
    }

    public Marriage getCurrentMarriage(String firstname, String lastname) {
        // check each marriage to find the one that doesn't have a divorce date
        List<Marriage> marriageList = getMarriages(firstname, lastname);
        for(Marriage m : marriageList) {
            if(m.divorceDate == null) {
                return m;
            }
        }
        return null;
    }

    public List<Person> getPartners(int marriageID) {
        List<Person> partnerList = new ArrayList<>();
        if(getMarriage(marriageID) != null) {
            pmDAO.getPartnerIDList(marriageID).forEach(
                    (i) -> partnerList.add(personService.getPerson(i)));
        }
        return partnerList;
    }
}
