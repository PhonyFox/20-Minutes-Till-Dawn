package com.tilldawn.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DatabaseHelper {
    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:./data/mygame", "sa", "");
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS users (username VARCHAR(255) PRIMARY KEY, password VARCHAR(255))");
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String username, String password) {
        try {
            String sql = "MERGE INTO users (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
