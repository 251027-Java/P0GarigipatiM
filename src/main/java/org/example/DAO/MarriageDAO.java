package org.example.DAO;

import org.example.Model.Marriage;
import org.example.Model.Person;
import org.example.Repository.Repo;

import java.sql.*;
import java.time.LocalDate;

public class MarriageDAO {
    // Fields
    private final String URL;
    private final String Username;
    private final String Password;
    private final String Schema;

    // Constructor
    public MarriageDAO() {
        // establish connection to db
        this.URL = Repo.URL();
        this.Username = Repo.Username();
        this.Password = Repo.Password();
        this.Schema = Repo.Schema();
    }

    // Methods
    // add new marriage
    public boolean addMarriage(Marriage marriage) {
        String sql = "INSERT INTO " + Schema + ".marriage (id, startDate, endDate) VALUES (?, ?, ?);";

        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, marriage.getId());
                statement.setDate(2, Date.valueOf(marriage.marriageDate));
                // TODO: Address null pointer exception
                statement.setDate(3, Date.valueOf(marriage.divorceDate));

                statement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            IO.println("Marriage creation failed!");
        }
        return false;
    }

    // Get marriage from id
    public Person getMarriage(int id) {
        String sql = "SELECT * FROM " + Schema + ".marriage WHERE marriage.id = ?;";

        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    LocalDate startDate = rs.getDate("startDate").toLocalDate();
                    LocalDate endDate = rs.getDate("endDate").toLocalDate();

                    //TODO: Fix this
                    //return new Marriage(id, startDate, endDate);
                }
            }
        } catch(SQLException e) {
            IO.println("getMarriage FAILED: Person with that id doesn't exist?");
        }
        return null;
    }
}
