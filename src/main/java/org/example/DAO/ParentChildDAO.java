package org.example.DAO;

import org.example.Repository.Repo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParentChildDAO {
    // Fields
    private final String URL;
    private final String Username;
    private final String Password;
    private final String Schema;

    // Constructor
    public ParentChildDAO(String tree) {
        // establish connection to db
        this.URL = Repo.URL();
        this.Username = Repo.Username();
        this.Password = Repo.Password();
        this.Schema = tree;
    }

    // Methods
    // Add new parent child relation
    public boolean addParentChild(int parentID, int childId) {
        String sql = "INSERT INTO " + Schema + ".parent_child(parent_id, child_id) " +
                "VALUES (?, ?);";

        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, parentID);
                statement.setInt(2, childId);
                statement.executeUpdate();

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Given the child id, get list of parents
    public List<Integer> getParentsIDList(int childId) {
        String sql = "SELECT * FROM " + Schema + ".parent_child WHERE child_id = ?;";
        List<Integer> parents = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, childId);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    int parentID = rs.getInt("parent_id");
                    parents.add(parentID);
                }
                return parents;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Given the parent id, get list of children
    public List<Integer> getChildrenIDList(int parentID) {
        String sql = "SELECT * FROM " + Schema + ".parent_child WHERE parent_id = ?;";
        List<Integer> children = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, Username, Password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, parentID);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    int childID = rs.getInt("child_id");
                    children.add(parentID);
                }
                return children;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}