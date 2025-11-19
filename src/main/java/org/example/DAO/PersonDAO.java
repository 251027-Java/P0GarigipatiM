package org.example.DAO;

import org.example.Model.Person;

import java.sql.*;
import java.time.LocalDate;

public class PersonDAO {
    // Fields
    // TODO: SET DATABASE CONNECTION PARAMS
    private static final String URL = "";
    private static final String Username = "";
    private static final String Password = "";
    Connection connection;

    // Constructor
    public PersonDAO() {
        // establish connection to db
        try {
            connection = DriverManager.getConnection(URL, Username, Password);

            // create person table
            try (Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS person (" +
                                "id INT PRIMARY KEY," +
                                "firstname VARCHAR(20) NOT NULL," +
                                "middlename VARCHAR(20) DEFAULT ''," +
                                "lastname VARCHAR(20) NOT NULL," +
                                "birthdate DATE NOT NULL" +
                            ");";
                statement.execute(sql);

                IO.println("Person table created successfully!");
            }
        } catch(SQLException e) {
            IO.println("Database connection not established or table creation failed.");
        }
    }

    // Methods

    // Add new person
    public boolean addPerson(Person person) {
        String sql = "INSERT INTO person (id, firstname, lastname, birthdate) " +
                "VALUES (?, ?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, person.getId());
            statement.setString(2, person.getFirstName());
            statement.setString(3, person.getLastName());
            statement.setDate(4, Date.valueOf(person.getBirthDate()));

            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            IO.println("Person creation failed!");
        }
        return false;
    }

    // Get person from name
    public Person getPerson(String firstname, String lastname) {
        String sql = "SELECT * FROM person WHERE person.firstname = ? AND person" +
                ".lastname = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstname);
            statement.setString(2, lastname);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                int id = rs.getInt("id");
                String middlename = rs.getString("middlename");
                LocalDate birthdate = rs.getDate("birthdate").toLocalDate();

                return new Person(id, firstname, middlename, lastname, birthdate);
            }

        } catch(SQLException e) {
            IO.println("getPerson FAILED: Person with that name doesn't exist?");
        }
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
    public boolean deletePerson(int id) {
        String sql = "DELETE FROM person WHERE person.id = ?;";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;

        } catch(SQLException e) {
            IO.println("Person deletion failed.");
        }
        return false;
    }
}
