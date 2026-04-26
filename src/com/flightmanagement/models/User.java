/**
 * User.java
 * Base class representing a system user in the Flight Management System.
 * Holds common attributes and behaviors shared by Customer, AirlineStaff,
 * and Administrator classes.
 *
 * Author: Kibru Menore
 * Date: 04/20/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.models;

public class User {

    private String userID;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;          // possible values: "CUSTOMER", "STAFF", "ADMIN"
    private String accountStatus; // possible values: "ACTIVE", "SUSPENDED", "DELETED"

    // Default constructor sets account status to ACTIVE by default
    public User() {
        this.accountStatus = "ACTIVE";
    }

    // Parameterized constructor to create a fully initialized user
    public User(String userID, String fullName, String email,
                String password, String phoneNumber, String role) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.accountStatus = "ACTIVE";
    }

    /**
     * Validates the entered credentials against stored values.
     * Returns true if both email and password match, false otherwise.
     */
    public boolean login(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            System.out.println("Login successful. Welcome, " + fullName + "!");
            return true;
        }
        System.out.println("Invalid email or password. Please try again.");
        return false;
    }

    /**
     * Logs the user out and displays a goodbye message.
     */
    public void logout() {
        System.out.println("Goodbye, " + fullName + "! You have been logged out.");
    }

    /**
     * Registers a new user by assigning all required fields.
     * Account status is set to ACTIVE upon registration.
     */
    public void register(String userID, String fullName, String email,
                         String password, String phoneNumber, String role) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.accountStatus = "ACTIVE";
        System.out.println("Account created successfully for " + fullName);
    }

    /**
     * Returns a formatted string summary of the user's information.
     * Useful for displaying user details in admin and staff views.
     */
    public String getUserInfo() {
        return "ID: " + userID +
               " | Name: " + fullName +
               " | Email: " + email +
               " | Role: " + role +
               " | Status: " + accountStatus;
    }

    // Getters
    public String getUserID() { return userID; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getRole() { return role; }
    public String getAccountStatus() { return accountStatus; }

    // Setters
    public void setUserID(String userID) { this.userID = userID; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setRole(String role) { this.role = role; }
    public void setAccountStatus(String accountStatus) { this.accountStatus = accountStatus; }
}