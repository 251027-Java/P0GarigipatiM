package org.example.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Repo {
    // Fields
    //
    private static final String db_url = "jdbc:postgresql://localhost:5432/familytreedb";
    private static final String db_username = "postgres";
    private static final String db_password = "mysecretpassword";
    private String schema_name; // name of the family tree

    // Constructor
    public Repo(String schema_name) {
        this.schema_name = schema_name;
        seed();
    }

    // Methods
    public static String URL() { return db_url; }
    public static String Username() { return db_username; }
    public static String Password() { return db_password; }

    private void seed() {
        schemaSeed();           // Create new schema
        personSeed();           // Create new person table
        marriageSeed();         // Create new marriage table
        parentChildSeed();      // Create new parent_child table
        personMarriageSeed();   // Create person_marriage table
    }

    private void schemaSeed() {
        try (Connection connection = DriverManager.getConnection(db_url, db_username, db_password)) {
            try(Statement statement = connection.createStatement()) {
                String sql = "CREATE SCHEMA IF NOT EXISTS " + schema_name;
                statement.executeUpdate(sql);
                System.out.println("Schema created or already exists: " + schema_name);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void personSeed() {
        // Create person table
        try (Connection connection = DriverManager.getConnection(db_url, db_username, db_password)) {
            try (Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS " + schema_name + ".person (" +
                        "id SERIAL PRIMARY KEY," +
                        "firstname VARCHAR(20) NOT NULL," +
                        "middlename VARCHAR(20) DEFAULT ''," +
                        "lastname VARCHAR(20) NOT NULL," +
                        "birthdate DATE NOT NULL" +
                        ");";
                statement.execute(sql);

                IO.println("Person table created successfully!");
            }
        } catch(SQLException e) {
            IO.println("Person table creation failed.");
        }
    }

    private void marriageSeed() {
        // Create marriage table
        try (Connection connection = DriverManager.getConnection(db_url, db_username, db_password)) {
            try (Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS " + schema_name + ".marriage (" +
                        "id SERIAL PRIMARY KEY," +
                        "startDate DATE NOT NULL," +
                        "endDate DATE" +
                        ");";
                statement.execute(sql);

                IO.println("Marriage table created successfully!");
            }
        } catch(SQLException e) {
            IO.println("Marriage table creation failed.");
        }
    }

    private void parentChildSeed() {
        // Create parent_child_relation table
        try (Connection connection = DriverManager.getConnection(db_url, db_username, db_password)) {
            try (Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS " + schema_name + ".parent_child (" +
                        "parent_id INTEGER NOT NULL," +
                        "child_id INTEGER NOT NULL," +
                        "PRIMARY KEY (parent_id, child_id)," +
                        "FOREIGN KEY (parent_id) REFERENCES " + schema_name + ".person(id)," +
                        "FOREIGN KEY (child_id) REFERENCES " + schema_name + ".person(id)" +
                        ");";
                statement.execute(sql);

                IO.println("Parent child table created successfully!");
            }
        } catch(SQLException e) {
            IO.println("Parent child table creation failed.");
        }
    }

    private void personMarriageSeed() {
        // Create person_marriage table
        try (Connection connection = DriverManager.getConnection(db_url, db_username, db_password)) {
            try (Statement statement = connection.createStatement()) {
                String sql =
                        "CREATE TABLE IF NOT EXISTS " + schema_name + ".person_marriage (" +
                                "person_id INTEGER NOT NULL," +
                                "marriage_id INTEGER NOT NULL," +
                                "PRIMARY KEY (person_id, marriage_id)," +
                                "FOREIGN KEY (person_id) REFERENCES " + schema_name + ".person(id)," +
                                "FOREIGN KEY (marriage_id) REFERENCES " + schema_name + ".marriage(id)" +
                                ");";
                statement.execute(sql);

                IO.println("person marriage table created successfully!");
            }
        } catch(SQLException e) {
            IO.println("person marriage table creation failed.");
        }
    }
}
