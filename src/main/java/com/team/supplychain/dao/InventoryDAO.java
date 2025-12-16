package com.team.supplychain.dao;

import com.team.supplychain.models.InventoryItem;
import com.team.supplychain.utils.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for inventory-related database operations
 */
public class InventoryDAO {

    /**
     * Get total count of inventory items
     */
    public int getTotalItemsCount() {
        String sql = "SELECT COUNT(*) as count FROM inventory_items";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get count of low stock items (where quantity <= reorder_level)
     */
    public int getLowStockCount() {
        String sql = "SELECT COUNT(*) as count FROM inventory_items WHERE quantity <= reorder_level";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get count of out of stock items (where quantity = 0)
     */
    public int getOutOfStockCount() {
        String sql = "SELECT COUNT(*) as count FROM inventory_items WHERE quantity = 0";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get total value of inventory
     */
    public double getTotalInventoryValue() {
        String sql = "SELECT SUM(quantity * unit_price) as total_value FROM inventory_items";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getDouble("total_value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    /**
     * Get all inventory items with supplier name (LEFT JOIN)
     * @return List of all inventory items
     */
    public List<InventoryItem> getAllInventoryItems() {
        String sql = "SELECT i.*, s.supplier_name " +
                     "FROM inventory_items i " +
                     "LEFT JOIN suppliers s ON i.supplier_id = s.supplier_id " +
                     "ORDER BY i.item_name ASC";

        List<InventoryItem> items = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                items.add(extractInventoryItemFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    /**
     * Get single inventory item by ID
     * @param itemId The inventory item ID
     * @return InventoryItem or null if not found
     */
    public InventoryItem getInventoryItemById(int itemId) {
        String sql = "SELECT i.*, s.supplier_name " +
                     "FROM inventory_items i " +
                     "LEFT JOIN suppliers s ON i.supplier_id = s.supplier_id " +
                     "WHERE i.item_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractInventoryItemFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get inventory items by category
     * @param category The category to filter by
     * @return List of inventory items in the specified category
     */
    public List<InventoryItem> getInventoryItemsByCategory(String category) {
        String sql = "SELECT i.*, s.supplier_name " +
                     "FROM inventory_items i " +
                     "LEFT JOIN suppliers s ON i.supplier_id = s.supplier_id " +
                     "WHERE i.category = ? " +
                     "ORDER BY i.item_name ASC";

        List<InventoryItem> items = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                items.add(extractInventoryItemFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    /**
     * Search inventory items by name or description
     * @param searchTerm The term to search for
     * @return List of matching inventory items
     */
    public List<InventoryItem> searchInventoryItems(String searchTerm) {
        String sql = "SELECT i.*, s.supplier_name " +
                     "FROM inventory_items i " +
                     "LEFT JOIN suppliers s ON i.supplier_id = s.supplier_id " +
                     "WHERE i.item_name LIKE ? OR i.description LIKE ? " +
                     "ORDER BY i.item_name ASC";

        List<InventoryItem> items = new ArrayList<>();
        String searchPattern = "%" + searchTerm + "%";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                items.add(extractInventoryItemFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    /**
     * Get low stock items (quantity <= reorder_level)
     * @return List of items that need reordering
     */
    public List<InventoryItem> getLowStockItems() {
        String sql = "SELECT i.*, s.supplier_name " +
                     "FROM inventory_items i " +
                     "LEFT JOIN suppliers s ON i.supplier_id = s.supplier_id " +
                     "WHERE i.quantity <= i.reorder_level " +
                     "ORDER BY i.quantity ASC";

        List<InventoryItem> items = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                items.add(extractInventoryItemFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    /**
     * Increase inventory quantity for a specific item (used when requisition is approved)
     * @param itemId The inventory item ID
     * @param quantityToAdd The quantity to add
     * @return true if update was successful, false otherwise
     */
    public boolean increaseInventoryQuantity(int itemId, int quantityToAdd) {
        String sql = "UPDATE inventory_items " +
                     "SET quantity = quantity + ?, last_updated = CURRENT_TIMESTAMP " +
                     "WHERE item_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantityToAdd);
            stmt.setInt(2, itemId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Find inventory item by exact name match
     * @param itemName The item name to search for
     * @return InventoryItem or null if not found
     */
    public InventoryItem findInventoryItemByName(String itemName) {
        String sql = "SELECT i.*, s.supplier_name " +
                     "FROM inventory_items i " +
                     "LEFT JOIN suppliers s ON i.supplier_id = s.supplier_id " +
                     "WHERE i.item_name = ? LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, itemName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractInventoryItemFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Helper method to extract InventoryItem from ResultSet
     * @param rs ResultSet positioned at a row
     * @return InventoryItem object
     * @throws SQLException if database access error occurs
     */
    private InventoryItem extractInventoryItemFromResultSet(ResultSet rs) throws SQLException {
        InventoryItem item = new InventoryItem();

        item.setItemId(rs.getInt("item_id"));
        item.setItemName(rs.getString("item_name"));
        item.setDescription(rs.getString("description"));
        item.setCategory(rs.getString("category"));
        item.setQuantity(rs.getInt("quantity"));
        item.setUnitPrice(rs.getBigDecimal("unit_price"));
        item.setReorderLevel(rs.getInt("reorder_level"));
        item.setReorderQuantity(rs.getInt("reorder_quantity"));

        // Handle nullable supplierId
        int supplierId = rs.getInt("supplier_id");
        if (!rs.wasNull()) {
            item.setSupplierId(supplierId);
        }

        item.setLocation(rs.getString("location"));

        // Handle timestamp
        java.sql.Timestamp timestamp = rs.getTimestamp("last_updated");
        if (timestamp != null) {
            item.setLastUpdated(timestamp.toLocalDateTime());
        }

        // Supplier name from LEFT JOIN
        item.setSupplierName(rs.getString("supplier_name"));

        return item;
    }
}
