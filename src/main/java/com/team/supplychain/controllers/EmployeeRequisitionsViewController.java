package com.team.supplychain.controllers;

import com.team.supplychain.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the Employee Requisitions View
 * Displays requisition history, filters, and management tools
 */
public class EmployeeRequisitionsViewController {

    // ==================== SUMMARY CARDS ====================
    @FXML private Label totalRequestsValue;
    @FXML private Label pendingValue;
    @FXML private Label approvedValue;
    @FXML private Label rejectedValue;

    // ==================== FILTERS ====================
    @FXML private TextField searchField;
    @FXML private ComboBox<String> statusFilter;
    @FXML private ComboBox<String> dateFilter;

    // ==================== LIST VIEW ====================
    @FXML private VBox requisitionsContainer;
    @FXML private Label totalCountLabel;

    // ==================== TABLE VIEW ====================
    @FXML private TableView<RequisitionRecord> requisitionsTable;
    @FXML private TableColumn<RequisitionRecord, String> reqIdColumn;
    @FXML private TableColumn<RequisitionRecord, String> categoryColumn;
    @FXML private TableColumn<RequisitionRecord, Integer> itemsColumn;
    @FXML private TableColumn<RequisitionRecord, String> dateRequestedColumn;
    @FXML private TableColumn<RequisitionRecord, String> statusColumn;
    @FXML private TableColumn<RequisitionRecord, String> reviewerColumn;

    // ==================== VIEW TOGGLE ====================
    @FXML private Button cardViewButton;
    @FXML private Button tableViewButton;
    @FXML private ScrollPane cardViewPane;
    @FXML private VBox tableViewPane;

    private User currentUser;

    /**
     * Set the current logged-in user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        loadRequisitionsData();
    }

    /**
     * Initialize the controller
     */
    @FXML
    private void initialize() {
        System.out.println("EmployeeRequisitionsViewController initialized with dummy data");

        // Initialize filter ComboBoxes
        if (statusFilter != null) {
            statusFilter.getItems().addAll("All Status", "Pending", "Approved", "Rejected");
            statusFilter.setValue("All Status");
        }

        if (dateFilter != null) {
            dateFilter.getItems().addAll("All Time", "This Week", "This Month", "This Quarter", "This Year");
            dateFilter.setValue("All Time");
        }

        // TODO: Initialize table columns with cell value factories
        // TODO: Load dummy data into table
        // TODO: Set up search listener
    }

    // ==================== ACTION HANDLERS ====================

    @FXML
    private void handleCreateRequisition() {
        System.out.println("Create new requisition clicked");
        // TODO: Open create requisition dialog/form
    }

    @FXML
    private void handleViewRequisition() {
        System.out.println("View requisition details clicked");
        // TODO: Show requisition details dialog
    }

    @FXML
    private void handleCancelRequisition() {
        System.out.println("Cancel requisition clicked");
        // TODO: Confirm and cancel requisition via DAO
    }

    @FXML
    private void handleExportList() {
        System.out.println("Export list clicked");
        // TODO: Export requisitions to Excel/PDF
    }

    // ==================== VIEW TOGGLE HANDLERS ====================

    @FXML
    private void handleCardView() {
        System.out.println("Card view toggled");
        showCardView();
    }

    @FXML
    private void handleTableView() {
        System.out.println("Table view toggled");
        showTableView();
    }

    private void showCardView() {
        if (cardViewPane != null && tableViewPane != null) {
            cardViewPane.setVisible(true);
            cardViewPane.setManaged(true);
            tableViewPane.setVisible(false);
            tableViewPane.setManaged(false);
            updateViewButtonStyles(true);
        }
    }

    private void showTableView() {
        if (cardViewPane != null && tableViewPane != null) {
            cardViewPane.setVisible(false);
            cardViewPane.setManaged(false);
            tableViewPane.setVisible(true);
            tableViewPane.setManaged(true);
            updateViewButtonStyles(false);
        }
    }

    private void updateViewButtonStyles(boolean cardActive) {
        if (cardViewButton != null && tableViewButton != null) {
            if (cardActive) {
                cardViewButton.getStyleClass().remove("action-button-secondary");
                if (!cardViewButton.getStyleClass().contains("action-button-primary")) {
                    cardViewButton.getStyleClass().add("action-button-primary");
                }
                tableViewButton.getStyleClass().remove("action-button-primary");
                if (!tableViewButton.getStyleClass().contains("action-button-secondary")) {
                    tableViewButton.getStyleClass().add("action-button-secondary");
                }
            } else {
                cardViewButton.getStyleClass().remove("action-button-primary");
                if (!cardViewButton.getStyleClass().contains("action-button-secondary")) {
                    cardViewButton.getStyleClass().add("action-button-secondary");
                }
                tableViewButton.getStyleClass().remove("action-button-secondary");
                if (!tableViewButton.getStyleClass().contains("action-button-primary")) {
                    tableViewButton.getStyleClass().add("action-button-primary");
                }
            }
        }
    }

    // ==================== FILTER HANDLERS ====================

    @FXML
    private void handleSearch() {
        String query = searchField.getText();
        System.out.println("Search: " + query);
        // TODO: Filter requisitions by search query
    }

    @FXML
    private void handleStatusFilter() {
        String status = statusFilter.getValue();
        System.out.println("Filter by status: " + status);
        // TODO: Filter requisitions by status
    }

    @FXML
    private void handleDateFilter() {
        String dateRange = dateFilter.getValue();
        System.out.println("Filter by date: " + dateRange);
        // TODO: Filter requisitions by date range
    }

    // ==================== HELPER METHODS ====================

    private void loadRequisitionsData() {
        System.out.println("Loading requisitions data for user: " +
                (currentUser != null ? currentUser.getUsername() : "unknown"));
        // TODO: Load from RequisitionDAO (to be created)
        // For now, dummy data is hardcoded in FXML
    }

    // ==================== INNER CLASS FOR TABLE ====================

    /**
     * Simple POJO for requisition table display
     */
    public static class RequisitionRecord {
        private final String reqId;
        private final String category;
        private final int items;
        private final String dateRequested;
        private final String status;
        private final String reviewer;

        public RequisitionRecord(String reqId, String category, int items,
                                 String dateRequested, String status, String reviewer) {
            this.reqId = reqId;
            this.category = category;
            this.items = items;
            this.dateRequested = dateRequested;
            this.status = status;
            this.reviewer = reviewer;
        }

        public String getReqId() { return reqId; }
        public String getCategory() { return category; }
        public int getItems() { return items; }
        public String getDateRequested() { return dateRequested; }
        public String getStatus() { return status; }
        public String getReviewer() { return reviewer; }
    }
}
