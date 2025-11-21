package org.example.Service;

import org.example.DAO.ParentChildDAO;
import org.example.DAO.PersonDAO;
import org.example.Model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonService {
    // Fields
    private final PersonDAO personDAO;
    private final ParentChildDAO parentChildDAO;

    // Constructor
    public PersonService(String tree) {
        this.personDAO = new PersonDAO(tree);
        this.parentChildDAO = new ParentChildDAO(tree);
    }

    // Methods
    public Person getPerson(String firstname, String lastname) {
        Person p = personDAO.getPerson(firstname, lastname);
        if(p != null) {
            p = personSetUp(p);
        }
        return p;
    }
    public Person getPerson(String fullname) {
        String[] split = fullname.split(" ");
        return getPerson(split[0], split[split.length - 1]);
    }
    public Person getPerson(int personID) {
        Person p = personDAO.getPerson(personID);
        if(p != null) {
            p = personSetUp(p);
        }
        return p;
    }

    public boolean personExists(String firstname, String lastname) {
        return (this.getPerson(firstname, lastname) != null);
    }
    public boolean personExists(String fullname) {
        return (this.getPerson(fullname) != null);
    }

    public void addPerson(Person p, List<Person> parents) {
        // personDAO will add person to table
        int id = personDAO.addPerson(p);
        if(!parents.isEmpty()) {
            // add parents to person object
            p.addParents(parents);

            // parent_child dao will add 2 new relations using ids
            parentChildDAO.addParentChild(parents.getFirst().getId(), id);
            parentChildDAO.addParentChild(parents.getLast().getId(), id);
        }
    }

    private Person personSetUp(Person p) {
        List<Person> parents = new ArrayList<>();
        parentChildDAO.getParentsIDList(p.getId()).forEach(
                (i) -> parents.add(personDAO.getPerson(i))
        );
        p.addParents(parents);

        parentChildDAO.getChildrenIDList(p.getId()).forEach(
                (i) -> p.addChild(personDAO.getPerson(i))
        );
        return p;
    }
}
