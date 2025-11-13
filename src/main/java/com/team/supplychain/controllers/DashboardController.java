package com.team.supplychain.controllers;

import com.team.supplychain.models.User;
import com.team.supplychain.enums.UserRole;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.io.IOException;

public class DashboardController {
    
    @FXML private Label welcomeLabel;
    @FXML private Label roleLabel;
    @FXML private Label userInfoLabel;
    @FXML private BorderPane mainContainer;
    
    // Menu buttons
    @FXML private Button inventoryButton;
    @FXML private Button suppliersButton;
    @FXML private Button employeesButton;
    @FXML private Button attendanceButton;
    @FXML private Button purchaseOrdersButton;
    @FXML private Button reportsButton;
    @FXML private Button settingsButton;
    @FXML private Button logoutButton;
    
    private User currentUser;
    
    /**
     * Set the current logged-in user
     * This method is called from LoginController
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        updateUserInterface();
        configureAccessBasedOnRole();
    }
    
    /**
     * Initialize the controller
     */
    @FXML
    private void initialize() {
        // Set up button actions
        setupButtonHandlers();
    }
    
    /**
     * Update UI with user information
     */
    private void updateUserInterface() {
        if (currentUser != null) {
            welcomeLabel.setText("Welcome, " + currentUser.getFullName() + "!");
            roleLabel.setText("Role: " + currentUser.getRole().getDisplayName());
            userInfoLabel.setText(currentUser.getEmail());
        }
    }
    
    /**
     * Configure menu access based on user role
     */
    private void configureAccessBasedOnRole() {
        if (currentUser == null) return;
        
        UserRole role = currentUser.getRole();
        
        switch (role) {
            case ADMIN:
                // Admin has access to everything
                enableAllButtons();
                break;
                
            case MANAGER:
                // Manager has access to most features
                enableAllButtons();
                settingsButton.setDisable(true); // Only admin can access settings
                break;
                
            case EMPLOYEE:
                // Employee has limited access
                inventoryButton.setDisable(false);
                attendanceButton.setDisable(false);
                suppliersButton.setDisable(true);
                employeesButton.setDisable(true);
                purchaseOrdersButton.setDisable(true);
                reportsButton.setDisable(true);
                settingsButton.setDisable(true);
                break;
                
            case SUPPLIER:
                // Supplier has very limited access
                inventoryButton.setDisable(false);
                purchaseOrdersButton.setDisable(false);
                suppliersButton.setDisable(true);
                employeesButton.setDisable(true);
                attendanceButton.setDisable(true);
                reportsButton.setDisable(true);
                settingsButton.setDisable(true);
                break;
                
            default:
                disableAllButtons();
        }
    }
    
    /**
     * Enable all menu buttons
     */
    private void enableAllButtons() {
        inventoryButton.setDisable(false);
        suppliersButton.setDisable(false);
        employeesButton.setDisable(false);
        attendanceButton.setDisable(false);
        purchaseOrdersButton.setDisable(false);
        reportsButton.setDisable(false);
        settingsButton.setDisable(false);
    }
    
    /**
     * Disable all menu buttons
     */
    private void disableAllButtons() {
        inventoryButton.setDisable(true);
        suppliersButton.setDisable(true);
        employeesButton.setDisable(true);
        attendanceButton.setDisable(true);
        purchaseOrdersButton.setDisable(true);
        reportsButton.setDisable(true);
        settingsButton.setDisable(true);
    }
    
    /**
     * Set up button click handlers
     */
    private void setupButtonHandlers() {
        if (inventoryButton != null) {
            inventoryButton.setOnAction(e -> handleInventory());
        }
        if (suppliersButton != null) {
            suppliersButton.setOnAction(e -> handleSuppliers());
        }
        if (employeesButton != null) {
            employeesButton.setOnAction(e -> handleEmployees());
        }
        if (attendanceButton != null) {
            attendanceButton.setOnAction(e -> handleAttendance());
        }
        if (purchaseOrdersButton != null) {
            purchaseOrdersButton.setOnAction(e -> handlePurchaseOrders());
        }
        if (reportsButton != null) {
            reportsButton.setOnAction(e -> handleReports());
        }
        if (settingsButton != null) {
            settingsButton.setOnAction(e -> handleSettings());
        }
        if (logoutButton != null) {
            logoutButton.setOnAction(e -> handleLogout());
        }
    }
    
    // Menu Action Handlers
    
    @FXML
    private void handleInventory() {
        loadView("/fxml/Inventory.fxml", "Inventory Management");
    }
    
    @FXML
    private void handleSuppliers() {
        loadView("/fxml/Suppliers.fxml", "Supplier Management");
    }
    
    @FXML
    private void handleEmployees() {
        loadView("/fxml/Employees.fxml", "Employee Management");
    }
    
    @FXML
    private void handleAttendance() {
        loadView("/fxml/Attendance.fxml", "Attendance Tracking");
    }
    
    @FXML
    private void handlePurchaseOrders() {
        loadView("/fxml/PurchaseOrders.fxml", "Purchase Orders");
    }
    
    @FXML
    private void handleReports() {
        loadView("/fxml/Reports.fxml", "Reports & Analytics");
    }
    
    @FXML
    private void handleSettings() {
        loadView("/fxml/Settings.fxml", "System Settings");
    }
    
    @FXML
    private void handleLogout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Are you sure you want to logout?");
        alert.setContentText("You will be returned to the login screen.");
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    // Load login screen
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
                    Parent root = loader.load();
                    
                    // Get current stage and set new scene
                    Stage stage = (Stage) logoutButton.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Login - Supply Chain Management");
                    stage.show();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                    showError("Logout Error", "Could not return to login screen.");
                }
            }
        });
    }
    
    /**
     * Load a view into the main container
     */
    private void loadView(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();
            
            // Set the view in the center of the BorderPane
            mainContainer.setCenter(view);
            
            // Update window title
            Stage stage = (Stage) mainContainer.getScene().getWindow();
            stage.setTitle(title + " - Supply Chain Management");
            
        } catch (IOException e) {
            e.printStackTrace();
            showError("Loading Error", "Could not load " + title + " view.");
        } catch (NullPointerException e) {
            // FXML file doesn't exist yet - show placeholder
            showInfo("Coming Soon", title + " module is under development.");
        }
    }
    
    /**
     * Show error alert
     */
    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Show info alert
     */
    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Get current user
     */
    public User getCurrentUser() {
        return currentUser;
    }
}