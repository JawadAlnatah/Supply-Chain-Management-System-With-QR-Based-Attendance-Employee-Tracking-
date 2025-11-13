package com.team.supplychain.test;

import com.team.supplychain.utils.DatabaseConnection;
import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("=== Testing TiDB Cloud Connection ===\n");
        
        try {
            // Test 1: Get connection
            Connection conn = DatabaseConnection.getConnection();
            System.out.println("✓ Connection established!");
            
            // Test 2: Get database info
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println("\n--- Database Info ---");
            System.out.println("Database: " + conn.getCatalog());
            System.out.println("URL: " + metaData.getURL());
            System.out.println("Driver: " + metaData.getDriverName());
            System.out.println("Driver Version: " + metaData.getDriverVersion());
            System.out.println("User: " + metaData.getUserName());
            
            // Test 3: Query users table
            System.out.println("\n--- Testing Query ---");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM users");
            
            if (rs.next()) {
                System.out.println("✓ Users in database: " + rs.getInt("count"));
            }
            
            // Test 4: Query all tables
            System.out.println("\n--- Tables in Database ---");
            ResultSet tables = metaData.getTables(conn.getCatalog(), null, "%", new String[]{"TABLE"});
            while (tables.next()) {
                System.out.println("  - " + tables.getString("TABLE_NAME"));
            }
            
            System.out.println("\n✓✓✓ All tests passed! ✓✓✓");
            
        } catch (SQLException e) {
            System.err.println("\n✗✗✗ Connection failed! ✗✗✗");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            
            // Common error hints
            System.err.println("\n--- Troubleshooting Hints ---");
            if (e.getMessage().contains("Access denied")) {
                System.err.println("→ Check username/password in config.properties");
            }
            if (e.getMessage().contains("Communications link failure")) {
                System.err.println("→ Check URL and port number");
                System.err.println("→ Verify TiDB Cloud cluster is running");
            }
            if (e.getMessage().contains("SSL")) {
                System.err.println("→ Add SSL parameters to connection URL");
                System.err.println("→ Try: ?useSSL=true&requireSSL=true");
            }
        }
    }
}