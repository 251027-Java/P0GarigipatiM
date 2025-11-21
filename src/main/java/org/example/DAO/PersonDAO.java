package org.example.DAO;

import org.example.Model.Person;
import org.example.Repository.Repo;

import java.sql.*;
import java.time.LocalDate;

public class PersonDAO {
    // Fields
    private final String URL;
    private final String Username;
    private final String Password;
    private final String Schema;

    // Constructor
    public PersonDAO() {
        // establish connection to db
        this.URL = Repo.URL();
        this.Username = Repo.Username();
        this.Password = Repo.Password();
        this.Schema = Repo.Schema();
    }

    // Methods

    // Add new person
    public boolean addPerson(Person person) {
        String sql = "INSERT INTO " + Schema + ".person(id, firstname, " +
                "lastname, birthdate) VALUES (?, ?, ?, ?);";

        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, person.getId());
                statement.setString(2, person.getFirstName());
                statement.setString(3, person.getLastName());
                statement.setDate(4, Date.valueOf(person.getBirthDate()));

                statement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get person from name
    public Person getPerson(String firstname, String lastname) {
        String sql = "SELECT * FROM " + Schema + ".person WHERE person.firstname = ? AND person" +
                ".lastname = ?;";

        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, firstname);
                statement.setString(2, lastname);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id");
                    String middlename = rs.getString("middlename");
                    LocalDate birthdate = rs.getDate("birthdate").toLocalDate();

                    return new Person(id, firstname, middlename, lastname, birthdate);
                }
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
        String sql = "DELETE FROM " + Schema + ".person WHERE person.id = ?;";

        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
                return true;
            }
        } catch(SQLException e) {
            IO.println("Person deletion failed.");
        }
        return false;
    }
}
