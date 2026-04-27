/**
 * PasswordUtils.java
 * Provides password hashing and validation utilities for the
 * Flight Management System. Used to securely handle passwords
 * by hashing them before storing and validating them during login.
 *
 * Author: Jonathan Elahee
 * Date: 04/27/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    /**
     * Hashes a plain text password using the SHA-256 algorithm.
     * The hashed password is returned as a hexadecimal string.
     * Returns null if hashing fails.
     */
    public static String hashPassword(String plainPassword) {
        try {
            // get an instance of the SHA-256 hashing algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // convert the password string to bytes and hash it
            byte[] hashBytes = digest.digest(plainPassword.getBytes());

            // convert the hashed bytes to a readable hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            // SHA-256 should always be available but handle just in case
            System.out.println("Error hashing password: " + e.getMessage());
            return null;
        }
    }

    /**
     * Validates a plain text password against a stored hashed password.
     * Hashes the plain password and compares it to the stored hash.
     * Returns true if they match, false otherwise.
     */
    public static boolean validatePassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }

        // hash the entered password and compare to the stored hash
        String hashedInput = hashPassword(plainPassword);
        if (hashedInput == null) {
            return false;
        }

        return hashedInput.equals(hashedPassword);
    }

    /**
     * Checks if a password meets the minimum security requirements.
     * Password must be at least 8 characters and contain at least
     * one letter and one number.
     * Returns true if requirements are met, false otherwise.
     */
    public static boolean meetsRequirements(String password) {
        if (password == null || password.length() < 8) {
            System.out.println("Password must be at least 8 characters.");
            return false;
        }

        // check for at least one letter
        if (!password.matches(".*[A-Za-z].*")) {
            System.out.println("Password must contain at least one letter.");
            return false;
        }

        // check for at least one digit
        if (!password.matches(".*[0-9].*")) {
            System.out.println("Password must contain at least one number.");
            return false;
        }

        return true;
    }
}
