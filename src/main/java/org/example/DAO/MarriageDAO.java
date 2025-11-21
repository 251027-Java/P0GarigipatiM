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
    public MarriageDAO(String tree) {
        // establish connection to db
        this.URL = Repo.URL();
        this.Username = Repo.Username();
        this.Password = Repo.Password();
        this.Schema = tree;
    }

    // Methods
    // add new marriage
    public int addMarriage(Marriage marriage) {
        String sql = "INSERT INTO " + Schema + ".marriage (startDate) VALUES (?);";

        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setDate(1, Date.valueOf(marriage.marriageDate));
                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Get marriage from id
    public Marriage getMarriage(int id) {
        String sql = "SELECT * FROM " + Schema + ".marriage WHERE marriage.id = ?;";

        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    LocalDate startDate = rs.getDate("startDate").toLocalDate();
                    Date endDate = rs.getDate("endDate");
                    Marriage m = new Marriage(id, startDate);
                    if (endDate != null) {
                        m.divorce(endDate.toLocalDate());
                    }
                    return m;
                }
            }
        } catch(SQLException e) {
            IO.println("getMarriage FAILED: Person with that id doesn't exist?");
        }
        return null;
    }

    // Update Marriage by adding end date
    public boolean updateMarriage(Marriage marriage) {
        String sql = "UPDATE " + Schema + ".marriage SET endDate = ? WHERE id = ?;";

        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDate(1, Date.valueOf(marriage.divorceDate));
                statement.setInt(2, marriage.getId());

                statement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete marriage
    public boolean deleteMarriage(int id) {
        String sql = "DELETE FROM " + Schema + ".marriage WHERE id = ?;";

        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
                return true;
            }
        } catch(SQLException e) {
            IO.println("Marriage deletion failed.");
        }
        return false;
    }
}