package org.example.DAO;

import org.example.Repository.Repo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonMarriageDAO {
    // Fields
    private final String URL;
    private final String Username;
    private final String Password;
    private final String Schema;

    // Constructor
    public PersonMarriageDAO(String tree) {
        // establish connection to db
        this.URL = Repo.URL();
        this.Username = Repo.Username();
        this.Password = Repo.Password();
        this.Schema = tree;
    }

    // Methods
    // Add new person marriage relation
    public boolean addPersonMarriage(int person_id, int marriage_id) {
        String sql = "INSERT INTO " + Schema + ".person_marriage(person_id, " +
                "marriage_id) VALUES (?, ?);";

        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, person_id);
                statement.setInt(2, marriage_id);
                statement.executeUpdate();

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Given the person id, get list of marriages
    public List<Integer> getMarriagesIDList(int personID) {
        String sql = "SELECT * FROM " + Schema + ".person_marriage WHERE person_id = ?;";
        List<Integer> marriages = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, personID);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    int marriageID = rs.getInt("marriage_id");
                    marriages.add(marriageID);
                }
                return marriages;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Given the marriage id, get list of partners
    public List<Integer> getPartnerIDList(int marriageID) {
        String sql = "SELECT * FROM " + Schema + ".person_marriage WHERE marriage_id = ?;";
        List<Integer> partners = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, marriageID);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    int personID = rs.getInt("person_id");
                    partners.add(personID);
                }
                return partners;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}