package com.team.supplychain.controllers;

import com.team.supplychain.dao.EmployeeDAO;
import com.team.supplychain.models.Employee;
import com.team.supplychain.models.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ManagerEmployeesController {

    @FXML private Label totalEmployeesLabel, activeEmployeesLabel, departmentsLabel;
    @FXML private ComboBox<String> departmentFilter;
    @FXML private TextField searchField;
    @FXML private Button refreshButton;
    @FXML private TableView<Employee> employeeTable;
    @FXML private TableColumn<Employee, Integer> employeeIdColumn;
    @FXML private TableColumn<Employee, String> nameColumn, emailColumn, departmentColumn, positionColumn;
    @FXML private TableColumn<Employee, String> hireDateColumn;

    private User currentUser;
    private ObservableList<Employee> employeeData;
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy");

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    @FXML
    private void initialize() {
        System.out.println("ManagerEmployeesController initialized");
        employeeData = FXCollections.observableArrayList();
        setupTable();
        setupFilters();
        loadEmployeesFromDatabase();
    }

    private void setupTable() {
        if (employeeTable == null) return;

        // Set up table columns using real Employee model fields
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));

        // Name column - combine first and last name
        nameColumn.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().getFullName()));

        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));

        // Hire date column - format the date
        hireDateColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getHireDate() != null) {
                return new SimpleStringProperty(
                    cellData.getValue().getHireDate().format(DATE_FORMATTER));
            }
            return new SimpleStringProperty("N/A");
        });

        employeeTable.setItems(employeeData);
    }

    private void setupFilters() {
        if (departmentFilter != null) {
            departmentFilter.getItems().addAll("All Departments", "Production", "Quality Control",
                "Packaging", "Warehouse", "Procurement", "Administration", "Finance");
            departmentFilter.setValue("All Departments");
        }
    }

    /**
     * Load employees from database using background thread
     */
    private void loadEmployeesFromDatabase() {
        Task<List<Employee>> loadTask = new Task<>() {
            @Override
            protected List<Employee> call() {
                // Query database on background thread
                return employeeDAO.getAllEmployees();
            }
        };

        loadTask.setOnSucceeded(event -> {
            // Update UI on JavaFX thread
            List<Employee> employees = loadTask.getValue();
            if (employees != null) {
                employeeData.clear();
                employeeData.addAll(employees);
                updateStats();
                System.out.println("Loaded " + employees.size() + " employees from database");
            } else {
                System.err.println("Failed to load employees - null result");
                showError("Database Error", "Failed to load employees from database");
            }
        });

        loadTask.setOnFailed(event -> {
            // Handle errors
            Throwable error = loadTask.getException();
            error.printStackTrace();
            showError("Database Error", "Failed to load employees: " + error.getMessage());
        });

        // Start background task
        new Thread(loadTask).start();
    }

    private void updateStats() {
        int total = employeeData.size();

        // Count active employees (assuming we'll add isActive field later)
        // For now, count all as active
        long active = total;

        // Count distinct departments
        long depts = employeeData.stream()
            .map(Employee::getDepartment)
            .filter(dept -> dept != null && !dept.isEmpty())
            .distinct()
            .count();

        if (totalEmployeesLabel != null) totalEmployeesLabel.setText(String.valueOf(total));
        if (activeEmployeesLabel != null) activeEmployeesLabel.setText(String.valueOf(active));
        if (departmentsLabel != null) departmentsLabel.setText(String.valueOf(depts));
    }

    @FXML
    private void handleRefresh() {
        System.out.println("Refreshing employee data...");
        loadEmployeesFromDatabase();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
