package com.team.supplychain.controllers;

import com.team.supplychain.dao.UserDAO;
import com.team.supplychain.models.User;
import com.team.supplychain.utils.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Label errorLabel;
    
    private UserDAO userDAO = new UserDAO();
    
    @FXML
    private void initialize() {
        // Set up event handlers
        usernameField.setOnAction(e -> handleLogin(null));
        passwordField.setOnAction(e -> handleLogin(null));
    }
    
    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        
        // Validation
        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter both username and password");
            return;
        }
        
        // Disable button during login
        loginButton.setDisable(true);
        
        try {
            User user = userDAO.authenticate(username, password);
            
            if (user != null) {
                // Login successful
                errorLabel.setVisible(false);
                openDashboard(user);
            } else {
                showError("Invalid username or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showError("An error occurred. Please try again.");
        } finally {
            loginButton.setDisable(false);
        }
    }
    
    private void openDashboard(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/fxml/Dashboard.fxml"));
            Parent root = loader.load();
            
            // Pass user to dashboard controller
            DashboardController controller = loader.getController();
            controller.setCurrentUser(user);
            
            // Get current stage and set new scene
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Dashboard - " + user.getUsername());
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
            AlertUtil.showError("Error", "Could not load dashboard");
        }
    }
    
    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
}