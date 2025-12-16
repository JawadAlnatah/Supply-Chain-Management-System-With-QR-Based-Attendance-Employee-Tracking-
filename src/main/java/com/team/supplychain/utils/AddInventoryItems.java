package com.team.supplychain.utils;

import java.sql.*;

public class AddInventoryItems {
    public static void main(String[] args) {
        System.out.println("Adding missing inventory items to database...\n");

        String[] insertStatements = {
            // Dairy Products
            "INSERT INTO inventory_items (item_name, description, category, quantity, unit_price, reorder_level, reorder_quantity, supplier_id, location) " +
            "VALUES ('Butter (25kg blocks)', 'High-quality butter in 25kg blocks', 'Raw Materials', 0, 320.00, 20, 50, 1, 'Warehouse A')",

            "INSERT INTO inventory_items (item_name, description, category, quantity, unit_price, reorder_level, reorder_quantity, supplier_id, location) " +
            "VALUES ('Whole Milk - Bulk (1000L)', 'Fresh whole milk in bulk 1000L containers', 'Raw Materials', 0, 2500.00, 50, 100, 1, 'Warehouse A')",

            "INSERT INTO inventory_items (item_name, description, category, quantity, unit_price, reorder_level, reorder_quantity, supplier_id, location) " +
            "VALUES ('Fresh Cream (200L)', 'Premium fresh cream in 200L containers', 'Raw Materials', 0, 800.00, 30, 60, 1, 'Warehouse A')",

            "INSERT INTO inventory_items (item_name, description, category, quantity, unit_price, reorder_level, reorder_quantity, supplier_id, location) " +
            "VALUES ('Skimmed Milk Powder (50kg)', 'Low-fat milk powder in 50kg bags', 'Raw Materials', 0, 450.00, 40, 80, 1, 'Warehouse A')",

            // Packaging Materials
            "INSERT INTO inventory_items (item_name, description, category, quantity, unit_price, reorder_level, reorder_quantity, supplier_id, location) " +
            "VALUES ('Milk Bottles 1L (1000 units)', '1-liter plastic milk bottles, pack of 1000', 'Packaging', 0, 850.00, 100, 200, 2, 'Warehouse B')",

            "INSERT INTO inventory_items (item_name, description, category, quantity, unit_price, reorder_level, reorder_quantity, supplier_id, location) " +
            "VALUES ('Plastic Bottle Caps (5000 units)', 'Bottle caps for milk bottles, pack of 5000', 'Packaging', 0, 120.00, 50, 100, 2, 'Warehouse B')",

            "INSERT INTO inventory_items (item_name, description, category, quantity, unit_price, reorder_level, reorder_quantity, supplier_id, location) " +
            "VALUES ('Product Labels - Roll (2000)', 'Product labels on roll, 2000 labels per roll', 'Packaging', 0, 220.00, 80, 150, 2, 'Warehouse B')"
        };

        try (Connection conn = DatabaseConnection.getConnection()) {
            int successCount = 0;

            for (String sql : insertStatements) {
                try (Statement stmt = conn.createStatement()) {
                    int result = stmt.executeUpdate(sql);
                    if (result > 0) {
                        successCount++;
                        System.out.println("✓ Added item " + successCount);
                    }
                } catch (SQLException e) {
                    if (e.getMessage().contains("Duplicate entry")) {
                        System.out.println("⊘ Item already exists (skipped)");
                    } else {
                        System.err.println("✗ Error: " + e.getMessage());
                    }
                }
            }

            System.out.println("\n=== SUMMARY ===");
            System.out.println("Successfully added " + successCount + " new items to inventory!");

            // Show all items now
            System.out.println("\n=== ALL INVENTORY ITEMS ===");
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT item_id, item_name, quantity, category FROM inventory_items ORDER BY item_id")) {

                while (rs.next()) {
                    System.out.printf("%d. %s (Qty: %d) - %s%n",
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getInt("quantity"),
                        rs.getString("category"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
