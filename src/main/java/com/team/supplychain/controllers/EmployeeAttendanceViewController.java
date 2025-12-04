package com.team.supplychain.controllers;

import com.team.supplychain.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller for the Employee Attendance View
 * Displays attendance history, weekly/monthly calendars, and statistics
 */
public class EmployeeAttendanceViewController {

    // ==================== SUMMARY CARDS ====================
    @FXML private Label weekAttendanceValue;
    @FXML private Label monthAttendanceValue;
    @FXML private Label avgHoursValue;

    // ==================== CALENDAR TOGGLE ====================
    @FXML private Button weeklyViewButton;
    @FXML private Button monthlyViewButton;

    // ==================== CALENDAR VIEWS ====================
    @FXML private HBox weeklyCalendarPane;
    @FXML private VBox monthlyCalendarPane;
    @FXML private Label weekRangeLabel;
    @FXML private Label monthLabel;

    // ==================== ATTENDANCE HISTORY TABLE ====================
    @FXML private TableView<AttendanceRecord> attendanceHistoryTable;
    @FXML private TableColumn<AttendanceRecord, String> dateColumn;
    @FXML private TableColumn<AttendanceRecord, String> dayColumn;
    @FXML private TableColumn<AttendanceRecord, String> checkInColumn;
    @FXML private TableColumn<AttendanceRecord, String> checkOutColumn;
    @FXML private TableColumn<AttendanceRecord, String> hoursColumn;
    @FXML private TableColumn<AttendanceRecord, String> statusColumn;

    private User currentUser;

    /**
     * Set the current logged-in user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        loadAttendanceData();
    }

    /**
     * Initialize the controller
     */
    @FXML
    private void initialize() {
        System.out.println("EmployeeAttendanceViewController initialized with dummy data");
        // Show weekly view by default
        showWeeklyView();
    }

    // ==================== VIEW TOGGLE HANDLERS ====================

    @FXML
    private void handleWeeklyView() {
        System.out.println("Weekly view clicked");
        showWeeklyView();
    }

    @FXML
    private void handleMonthlyView() {
        System.out.println("Monthly view clicked");
        showMonthlyView();
    }

    private void showWeeklyView() {
        if (weeklyCalendarPane != null && monthlyCalendarPane != null) {
            weeklyCalendarPane.setVisible(true);
            weeklyCalendarPane.setManaged(true);
            monthlyCalendarPane.setVisible(false);
            monthlyCalendarPane.setManaged(false);

            // Update button styles
            updateButtonStyles(true);
        }
    }

    private void showMonthlyView() {
        if (weeklyCalendarPane != null && monthlyCalendarPane != null) {
            weeklyCalendarPane.setVisible(false);
            weeklyCalendarPane.setManaged(false);
            monthlyCalendarPane.setVisible(true);
            monthlyCalendarPane.setManaged(true);

            // Update button styles
            updateButtonStyles(false);
        }
    }

    private void updateButtonStyles(boolean weeklyActive) {
        if (weeklyViewButton != null && monthlyViewButton != null) {
            if (weeklyActive) {
                // Weekly button active
                if (!weeklyViewButton.getStyleClass().contains("action-button-primary")) {
                    weeklyViewButton.getStyleClass().add("action-button-primary");
                }
                weeklyViewButton.getStyleClass().remove("action-button-secondary");

                if (!monthlyViewButton.getStyleClass().contains("action-button-secondary")) {
                    monthlyViewButton.getStyleClass().add("action-button-secondary");
                }
                monthlyViewButton.getStyleClass().remove("action-button-primary");
            } else {
                // Monthly button active
                if (!monthlyViewButton.getStyleClass().contains("action-button-primary")) {
                    monthlyViewButton.getStyleClass().add("action-button-primary");
                }
                monthlyViewButton.getStyleClass().remove("action-button-secondary");

                if (!weeklyViewButton.getStyleClass().contains("action-button-secondary")) {
                    weeklyViewButton.getStyleClass().add("action-button-secondary");
                }
                weeklyViewButton.getStyleClass().remove("action-button-primary");
            }
        }
    }

    // ==================== NAVIGATION HANDLERS ====================

    @FXML
    private void handlePreviousWeek() {
        System.out.println("Previous week clicked");
        // TODO: Decrement week and reload data
    }

    @FXML
    private void handleNextWeek() {
        System.out.println("Next week clicked");
        // TODO: Increment week and reload data
    }

    @FXML
    private void handlePreviousMonth() {
        System.out.println("Previous month clicked");
        // TODO: Decrement month and reload data
    }

    @FXML
    private void handleNextMonth() {
        System.out.println("Next month clicked");
        // TODO: Increment month and reload data
    }

    // ==================== ACTION HANDLERS ====================

    @FXML
    private void handleExportExcel() {
        System.out.println("Export to Excel clicked");
        // TODO: Export attendance data to Excel using Apache POI
    }

    @FXML
    private void handleViewRecord() {
        System.out.println("View attendance record clicked");
        // TODO: Show attendance record details dialog
    }

    // ==================== HELPER METHODS ====================

    private void loadAttendanceData() {
        System.out.println("Loading attendance data for user: " +
                (currentUser != null ? currentUser.getUsername() : "unknown"));
        // TODO: Load from AttendanceDAO
        // For now, dummy data is hardcoded in FXML
    }

    // ==================== INNER CLASS FOR TABLE ====================

    /**
     * Simple POJO for attendance table display
     */
    public static class AttendanceRecord {
        private final String date;
        private final String day;
        private final String checkIn;
        private final String checkOut;
        private final String hours;
        private final String status;

        public AttendanceRecord(String date, String day, String checkIn,
                                String checkOut, String hours, String status) {
            this.date = date;
            this.day = day;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.hours = hours;
            this.status = status;
        }

        public String getDate() { return date; }
        public String getDay() { return day; }
        public String getCheckIn() { return checkIn; }
        public String getCheckOut() { return checkOut; }
        public String getHours() { return hours; }
        public String getStatus() { return status; }
    }
}
