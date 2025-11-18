package org.example.DAO;

import org.example.Model.Marriage;
import org.example.Model.Person;

import java.sql.*;
import java.time.LocalDate;

public class MarriageDAO {
    // Fields
    // TODO: SET DATABASE CONNECTION PARAMS
    private static final String URL = "";
    private static final String Username = "";
    private static final String Password = "";
    Connection connection;

    // Constructor
    public MarriageDAO() {
        // establish connection to db
        try {
            connection = DriverManager.getConnection(URL, Username, Password);

            // create marriage table
            try (Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS marriage (" +
                        "id INT PRIMARY KEY," +
                        "startDate DATE NOT NULL," +
                        "endDate DATE" +
                        ");";
                statement.execute(sql);

                IO.println("Marriage table created successfully!");
            }
        } catch(SQLException e) {
            IO.println("Database connection not established or table creation failed.");
        }
    }

    // Methods
    // add new marriage
    public boolean addMarriage(Marriage marriage) {
        String sql = "INSERT INTO marriage (id, startDate, endDate) VALUES (?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, marriage.getId());
            statement.setDate(2, Date.valueOf(marriage.marriageDate));
            // TODO: Address null pointer exception
            statement.setDate(3, Date.valueOf(marriage.divorceDate));

            statement.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            IO.println("Marriage creation failed!");
        }
        return false;
    }

    // Get person from id
    public Person getMarriage(int id) {
        String sql = "SELECT * FROM marriage WHERE marriage.id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                LocalDate startDate = rs.getDate("startDate").toLocalDate();
                LocalDate endDate = rs.getDate("endDate").toLocalDate();

                //TODO: Fix this
                //return new Marriage(id, startDate, endDate);
            }

        } catch(SQLException e) {
            IO.println("getMarriage FAILED: Person with that id doesn't exist?");
        }
        return null;
    }
}
