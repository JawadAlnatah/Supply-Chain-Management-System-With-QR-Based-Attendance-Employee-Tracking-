package com.team.supplychain.test;

import com.team.supplychain.utils.DatabaseConnection;
import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("=== Testing TiDB Cloud Connection ===\n");
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            System.out.println("✓ Connection successful!");
            System.out.println("  Database: " + conn.getCatalog());
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM users");
            
            if (rs.next()) {
                System.out.println("✓ Users count: " + rs.getInt("count"));
            }
            
            System.out.println("\n✓✓✓ All tests passed! ✓✓✓");
            
        } catch (SQLException e) {
            System.err.println("✗ Connection failed!");
            e.printStackTrace();
        }
    }
}