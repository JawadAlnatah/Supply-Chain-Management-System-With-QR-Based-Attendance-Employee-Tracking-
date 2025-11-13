package com.team.supplychain.models;

import java.time.LocalDate;

public class Employee {
    private int employeeId;
    private int userId;           // Foreign key to users table
    private String department;
    private String position;
    private String phone;
    private String qrCode;
    private LocalDate hireDate;
    
    // For joined queries - user info
    private String firstName;
    private String lastName;
    private String email;
    
    // Constructors
    public Employee() {}
    
    public Employee(int userId, String department, String position, String phone) {
        this.userId = userId;
        this.department = department;
        this.position = position;
        this.phone = phone;
        this.hireDate = LocalDate.now();
    }
    
    // Getters and Setters
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }
    
    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }
    
    // User info getters/setters (for joined queries)
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getFullName() { 
        return firstName + " " + lastName; 
    }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", fullName='" + getFullName() + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}