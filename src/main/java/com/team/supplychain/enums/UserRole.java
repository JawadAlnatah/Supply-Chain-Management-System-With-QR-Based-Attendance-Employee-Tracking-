package com.team.supplychain.enums;

public enum UserRole {
    ADMIN("Admin"),
    MANAGER("Manager"),
    EMPLOYEE("Employee"),
    SUPPLIER("Supplier");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
