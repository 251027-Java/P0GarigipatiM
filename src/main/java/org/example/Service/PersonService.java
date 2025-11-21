package org.example.Service;

import org.example.DAO.PersonDAO;
import org.example.Model.Person;

import java.util.List;

public class PersonService {
    // Fields
    private final PersonDAO personDAO;

    // Constructor
    public PersonService() {
        this.personDAO = new PersonDAO();
    }

    // Methods
    public Person getPerson(String fullname) {
        String[] split = fullname.split(" ");
        return personDAO.getPerson(split[0], split[1]);
    }

    public boolean personExists(String fullname) {
        return (this.getPerson(fullname) != null);
    }

    public void addPerson(Person p, List<Person> parents) {
        // add parents to person object
        p.addParents(parents);

        // parent_child dao will add 2 new relations using ids
        // parent_childDAO.addRelation(p.getID, parents[0].getID);
        // parent_childDAO.addRelation(p.getID, parents[1].getID);

        // persondao will add person to table
        personDAO.addPerson(p);
    }
}
