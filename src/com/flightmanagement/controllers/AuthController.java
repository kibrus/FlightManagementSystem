/**
 * AuthController.java
 * Handles all authentication operations in the Flight Management System.
 * Responsible for user login, logout, registration, and session management.
 * Validates user credentials against the DataStore and assigns
 * the appropriate role to the logged in user.
 *
 * Author: Kibru Menore
 * Date: 04/23/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.controllers;

import com.flightmanagement.data.DataStore;
import com.flightmanagement.models.Customer;
import com.flightmanagement.models.User;

public class AuthController {

    private String sessionID;
    private boolean loginStatus;
    private String currentUserRole;

    // Default constructor initializes session as logged out
    public AuthController() {
        this.loginStatus = false;
        this.currentUserRole = null;
    }

    /**
     * Authenticates a user by checking their email and password
     * against records in the DataStore.
     * Returns true if credentials are valid, false otherwise.
     */
    public boolean authenticate(String email, String password) {
        // look up the user by email in the DataStore
        User user = DataStore.findUserByEmail(email);

        if (user == null) {
            System.out.println("No account found with email: " + email);
            return false;
        }

        // check if account is active before allowing login
        if (!user.getAccountStatus().equals("ACTIVE")) {
            System.out.println("Account is " + user.getAccountStatus() +
                               ". Please contact support.");
            return false;
        }

        // validate the password against the stored password
        if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password. Please try again.");
            return false;
        }

        // credentials are valid so start the session
        this.loginStatus = true;
        this.currentUserRole = user.getRole();
        this.sessionID = "SESSION" + System.currentTimeMillis();
        DataStore.setLoggedInUser(user);
        System.out.println("Welcome, " + user.getFullName() + "! Role: " + currentUserRole);
        return true;
    }

    /**
     * Registers a new customer account and adds it to the DataStore.
     * Returns true if registration was successful, false if email
     * is already in use.
     */
    public boolean register(String fullName, String email, String password,
                            String phoneNumber) {
        // check if email is already registered
        if (DataStore.findUserByEmail(email) != null) {
            System.out.println("An account with this email already exists.");
            return false;
        }

        // generate a new customer ID based on current list size
        String customerID = "C" + String.format("%03d", DataStore.getUsers().size() + 1);
        String userID = "U" + String.format("%03d", DataStore.getUsers().size() + 1);

        // create and save the new customer account
        Customer newCustomer = new Customer(userID, fullName, email,
                                            password, phoneNumber, customerID);
        DataStore.addUser(newCustomer);
        System.out.println("Account created successfully. You can now log in.");
        return true;
    }

    /**
     * Logs out the currently logged in user by clearing the session.
     */
    public void logout() {
        User user = DataStore.getLoggedInUser();
        if (user != null) {
            System.out.println("Goodbye, " + user.getFullName() + "!");
        }
        // clear all session data
        DataStore.clearLoggedInUser();
        this.loginStatus = false;
        this.currentUserRole = null;
        this.sessionID = null;
    }

    /**
     * Returns the role of the currently logged in user.
     * Used to determine which menu to display after login.
     */
    public String getUserRole() {
        if (DataStore.getLoggedInUser() != null) {
            return DataStore.getLoggedInUser().getRole();
        }
        return null;
    }

    /**
     * Checks if a user is currently logged in.
     * Used to protect booking and account features.
     */
    public boolean isLoggedIn() {
        return loginStatus && DataStore.getLoggedInUser() != null;
    }

    // Getters
    public String getSessionID() { return sessionID; }
    public boolean getLoginStatus() { return loginStatus; }
    public String getCurrentUserRole() { return currentUserRole; }
}