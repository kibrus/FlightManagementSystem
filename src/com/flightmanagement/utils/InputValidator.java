/**
 * InputValidator.java
 * Provides input validation utility methods for the Flight Management System.
 * Used across the application to validate user inputs such as email addresses,
 * phone numbers, dates, and IATA airport codes before processing them.
 *
 * Author: Jonathan Elahee
 * Date: 04/27/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.utils;

public class InputValidator {

    /**
     * Validates an email address format.
     * Email must contain an @ symbol and a domain extension.
     * Returns true if valid, false otherwise.
     */
    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            System.out.println("Email cannot be empty.");
            return false;
        }
        // check for basic email format with @ and a dot in domain
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            System.out.println("Invalid email format. Example: user@email.com");
            return false;
        }
        return true;
    }

    /**
     * Validates a password meets minimum requirements.
     * Password must be at least 8 characters long.
     * Returns true if valid, false otherwise.
     */
    public static boolean validatePassword(String password) {
        if (password == null || password.length() < 8) {
            System.out.println("Password must be at least 8 characters long.");
            return false;
        }
        return true;
    }

    /**
     * Validates a phone number format.
     * Accepts formats like 713-555-0101 or 7135550101.
     * Returns true if valid, false otherwise.
     */
    public static boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            System.out.println("Phone number cannot be empty.");
            return false;
        }
        // accept phone numbers with or without dashes
        if (!phoneNumber.matches("^[0-9]{3}-?[0-9]{3}-?[0-9]{4}$")) {
            System.out.println("Invalid phone number. Example: 713-555-0101");
            return false;
        }
        return true;
    }

    /**
     * Validates an IATA airport code.
     * Code must be exactly 3 uppercase letters.
     * Returns true if valid, false otherwise.
     */
    public static boolean validateIATACode(String code) {
        if (code == null || !code.matches("[A-Z]{3}")) {
            System.out.println("Invalid IATA code. Must be 3 uppercase letters. Example: IAH");
            return false;
        }
        return true;
    }

    /**
     * Validates a date string in YYYY-MM-DD format.
     * Returns true if valid, false otherwise.
     */
    public static boolean validateDate(String date) {
        if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Invalid date format. Use YYYY-MM-DD. Example: 2026-05-01");
            return false;
        }
        return true;
    }

    /**
     * Validates that a string is not null or empty.
     * Used for general required field validation.
     * Returns true if valid, false otherwise.
     */
    public static boolean validateNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            System.out.println(fieldName + " cannot be empty.");
            return false;
        }
        return true;
    }

    /**
     * Validates that a number is positive.
     * Used for validating passenger count and seat numbers.
     * Returns true if valid, false otherwise.
     */
    public static boolean validatePositiveNumber(int number, String fieldName) {
        if (number <= 0) {
            System.out.println(fieldName + " must be greater than zero.");
            return false;
        }
        return true;
    }
}
