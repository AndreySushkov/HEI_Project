package ru.vsuet.hei_project.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
    private final Connection connection;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "roota";

    public DataBaseConnector() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("DB: " + connection.getMetaData().getDatabaseProductName());
        } catch (SQLException e) {
            throw new RuntimeException("Error while DB connecting...", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
