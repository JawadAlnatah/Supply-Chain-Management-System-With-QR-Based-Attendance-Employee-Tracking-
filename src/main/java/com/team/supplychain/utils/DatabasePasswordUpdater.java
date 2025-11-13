package com.team.supplychain.utils;

import java.sql.*;

public class DatabasePasswordUpdater {
    
    public static void main(String[] args) {
        String[] usernames = {"admin", "manager1", "employee1", "employee2"};
        String plainPassword = "password123";
        
        for (String username : usernames) {
            updateUserPassword(username, plainPassword);
        }
        
        System.out.println("✓ All passwords updated successfully!");
    }
    
    private static void updateUserPassword(String username, String plainPassword) {
        String sql = "UPDATE users SET password_hash = ? WHERE username = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String hashedPassword = PasswordUtil.hashPassword(plainPassword);
            stmt.setString(1, hashedPassword);
            stmt.setString(2, username);
            
            int rows = stmt.executeUpdate();
            System.out.println("Updated " + username + ": " + rows + " row(s)");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}