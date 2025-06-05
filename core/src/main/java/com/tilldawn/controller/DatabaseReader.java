package com.tilldawn.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseReader {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:./data/mygame", "sa", "");
            String sql = "SELECT username, password FROM users";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                //System.out.println("User: " + username + ", Pass: " + password);
            }

            rs.close();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
