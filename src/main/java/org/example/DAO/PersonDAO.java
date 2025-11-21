package org.example.DAO;

import org.example.Model.Person;
import org.example.Repository.Repo;

import java.lang.reflect.Type;
import java.sql.*;
import java.time.LocalDate;

public class PersonDAO {
    // Fields
    private final String URL;
    private final String Username;
    private final String Password;
    private final String Schema;

    // Constructor
    public PersonDAO(String tree) {
        // establish connection to db
        this.URL = Repo.URL();
        this.Username = Repo.Username();
        this.Password = Repo.Password();
        this.Schema = tree;
    }

    // Methods

    // Add new person
    public int addPerson(Person person) {
        String sql = "INSERT INTO " + Schema + ".person(firstname, " +
                "middlename, lastname, birthdate) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, Username, Password);
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, person.getFirstName());

            if (person.getMiddleName().isEmpty()) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, person.getMiddleName());
            }

            statement.setString(3, person.getLastName());
            statement.setDate(4, Date.valueOf(person.getBirthDate()));

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);   // RETURN GENERATED ID
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
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

    public Person getPerson(int personID) {
        String sql = "SELECT * FROM " + Schema + ".person WHERE person.id = ?;";

        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, personID);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    String firstname = rs.getString("firstname");
                    String middlename = rs.getString("middlename");
                    String lastname = rs.getString("lastname");
                    LocalDate birthdate = rs.getDate("birthdate").toLocalDate();

                    return new Person(personID, firstname, middlename, lastname, birthdate);
                }
            }
        } catch(SQLException e) {
            IO.println("getPerson FAILED: Person with that id doesn't exist?");
        }
        return null;
    }

    // Update the person, but can't update birth
    public boolean updatePerson(Person person) {
        String sql = "UPDATE " + Schema + ".person SET " +
                "firstname = ?, middlename = ?, lastname = ? " +
                "WHERE id = ?;";
        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, person.getFirstName());
                statement.setString(2, person.getMiddleName());
                statement.setString(3, person.getLastName());
                statement.setInt(4, person.getId());

                statement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
