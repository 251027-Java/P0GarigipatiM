package org.example.DAO;

import org.example.Model.Person;

import java.sql.Connection;

public class PersonDAO {
    // Fields
    Connection connection;

    // Constructor
    public PersonDAO() {
        // establish connection to db
    }

    // Methods

    // Add new person
    public boolean addPerson(Person person) {
        // Insert person into person table
        // Return true if successful
        return false;
    }

    // Get person from first and last name
    public Person getPerson(String firstName, String lastName) {
        // TODO: get person by id or some other identifier

        // select from person table where first and last name
        // create new person object and return it
        // return null if doesn't exist
        return null;
    }

    // Update the person, but can't update birth
    public boolean updatePerson(Person person) {
        // TODO: change the parameters, can't expect user to send in full person when
        //  birth and other things might not change

        // update these fields in person where id/name matches
        // return true if successful
        return false;
    }

    // Delete person
    public Person deletePerson(Person person) {
        // TODO: change input to id or some other identifier

        // delete in person table where identifier
        // check that deletion was successful
        // return deleted person
        return null;
    }
}
