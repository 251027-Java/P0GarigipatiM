package org.example.Service;

import org.example.DAO.ParentChildDAO;
import org.example.DAO.PersonDAO;
import org.example.Model.Person;

import java.util.List;

public class PersonService {
    // Fields
    private final PersonDAO personDAO;
    private final ParentChildDAO parentChildDAO;

    // Constructor
    public PersonService() {
        this.personDAO = new PersonDAO();
        this.parentChildDAO = new ParentChildDAO();
    }

    // Methods
    public Person getPerson(String firstname, String lastname) {
        return personDAO.getPerson(firstname, lastname);
    }
    public Person getPerson(String fullname) {
        String[] split = fullname.split(" ");
        return personDAO.getPerson(split[0], split[split.length - 1]);
    }
    public boolean personExists(String firstname, String lastname) {
        return (this.getPerson(firstname, lastname) != null);
    }
    public boolean personExists(String fullname) {
        return (this.getPerson(fullname) != null);
    }

    public void addPerson(Person p, List<Person> parents) {
        if(!parents.isEmpty()) {
            // add parents to person object
            p.addParents(parents);

            // parent_child dao will add 2 new relations using ids
            parentChildDAO.addParentChild(p.getId(), parents.getFirst().getId());
            parentChildDAO.addParentChild(p.getId(), parents.getLast().getId());
        }
        // personDAO will add person to table
        personDAO.addPerson(p);
    }
}
