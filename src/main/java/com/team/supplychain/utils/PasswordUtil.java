package com.team.supplychain.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    
    /**
     * Hash a password using BCrypt
     */
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(10));
    }
    
    /**
     * Check if a password matches the hash
     * Also handles legacy plain text passwords for development
     */
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        try {
            // If hash starts with $2a, it's a valid BCrypt hash
            if (hashedPassword != null && hashedPassword.startsWith("$2")) {
                return BCrypt.checkpw(plainPassword, hashedPassword);
            } else {
                // Legacy: plain text comparison (DEVELOPMENT ONLY!)
                System.out.println("⚠️ WARNING: Using plain text password comparison!");
                return plainPassword.equals(hashedPassword);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Invalid password hash format: " + e.getMessage());
            // Fallback to plain text for development
            return plainPassword.equals(hashedPassword);
        }
    }
}