package com.team.supplychain.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection connection;
    private static Properties props = new Properties();
    
    static {
        try {
            // Load properties file
            InputStream input = DatabaseConnection.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");
            
            if (input != null) {
                props.load(input);
                System.out.println("✓ Config loaded from config.properties");
            } else {
                System.out.println("⚠ config.properties not found, using default values");
            }
        } catch (IOException e) {
            System.err.println("✗ Error loading config.properties: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            // Get values from properties file (or use defaults)
            String url = props.getProperty("db.url", 
                "jdbc:mysql://gateway01.eu-central-1.prod.aws.tidbcloud.com:4000/supply_chain_qr?sslMode=VERIFY_IDENTITY");
            String user = props.getProperty("db.username", "3uB8fqJmu4peKdN.root");
            String password = props.getProperty("db.password", "46dmNGakAQIh5Q0v");
            
            try {
                // Load MySQL driver (usually auto-loaded, but explicit is better)
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Create connection
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("✓ Database connected successfully to TiDB Cloud!");
                
            } catch (ClassNotFoundException e) {
                System.err.println("✗ MySQL Driver not found!");
                throw new SQLException("MySQL JDBC Driver not found", e);
            }
        }
        return connection;
    }
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✓ Database connection closed");
            }
        } catch (SQLException e) {
            System.err.println("✗ Error closing connection: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Test method
    public static void testConnection() {
        try {
            Connection conn = getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("✓ Connection test successful!");
                System.out.println("  Database: " + conn.getCatalog());
                System.out.println("  URL: " + conn.getMetaData().getURL());
            }
        } catch (SQLException e) {
            System.err.println("✗ Connection test failed!");
            e.printStackTrace();
        }
    }
}