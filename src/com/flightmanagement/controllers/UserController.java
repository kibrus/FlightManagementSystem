/**
 * UserController.java
 * Handles all user account management operations in the Flight Management System.
 * Responsible for activating, suspending, and deleting user accounts.
 * Only accessible by authenticated system administrators.
 *
 * Author: Jonathan Elahee
 * Date: 04/27/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.controllers;

import com.flightmanagement.data.DataStore;
import com.flightmanagement.models.User;

import java.util.List;

public class UserController {

    private User selectedUser;
    private String accountAction;
    private String accountUpdateStatus;

    // Default constructor
    public UserController() {}

    /**
     * Activates a suspended or deleted user account.
     * Only administrators can activate accounts.
     * Returns true if activation was successful.
     */
    public boolean activateUser(String userID) {
        User user = findUserByID(userID);
        if (user == null) {
            System.out.println("User " + userID + " not found.");
            return false;
        }

        // check if account is already active
        if (user.getAccountStatus().equals("ACTIVE")) {
            System.out.println("Account is already active.");
            return false;
        }

        user.setAccountStatus("ACTIVE");
        this.selectedUser = user;
        this.accountAction = "ACTIVATED";
        this.accountUpdateStatus = "SUCCESS";
        System.out.println("Account for " + user.getFullName() + " has been activated.");
        return true;
    }

    /**
     * Suspends an active user account.
     * Suspended users cannot log in until reactivated.
     * Returns true if suspension was successful.
     */
    public boolean suspendUser(String userID) {
        User user = findUserByID(userID);
        if (user == null) {
            System.out.println("User " + userID + " not found.");
            return false;
        }

        // check if account is already suspended
        if (user.getAccountStatus().equals("SUSPENDED")) {
            System.out.println("Account is already suspended.");
            return false;
        }

        user.setAccountStatus("SUSPENDED");
        this.selectedUser = user;
        this.accountAction = "SUSPENDED";
        this.accountUpdateStatus = "SUCCESS";
        System.out.println("Account for " + user.getFullName() + " has been suspended.");
        return true;
    }

    /**
     * Permanently deletes a user account from the DataStore.
     * This action cannot be undone.
     * Returns true if deletion was successful.
     */
    public boolean deleteUser(String userID) {
        User user = findUserByID(userID);
        if (user == null) {
            System.out.println("User " + userID + " not found.");
            return false;
        }

        DataStore.removeUser(user);
        this.selectedUser = user;
        this.accountAction = "DELETED";
        this.accountUpdateStatus = "SUCCESS";
        System.out.println("Account for " + user.getFullName() + " has been deleted.");
        return true;
    }

    /**
     * Displays all user accounts currently stored in the DataStore.
     * Used by administrators to view and manage all accounts.
     */
    public void displayAllUsers() {
        List<User> users = DataStore.getUsers();

        if (users.isEmpty()) {
            System.out.println("No users found in the system.");
            return;
        }

        System.out.println("\n========== ALL USERS ==========");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i).getUserInfo());
        }
        System.out.println("================================\n");
    }

    /**
     * Displays only users with a specific role.
     * Used to filter customers, staff, or admins separately.
     */
    public void displayUsersByRole(String role) {
        List<User> users = DataStore.getUsers();
        System.out.println("\n========== " + role + " ACCOUNTS ==========");
        int count = 0;

        for (User user : users) {
            if (user.getRole().equalsIgnoreCase(role)) {
                System.out.println(user.getUserInfo());
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No " + role + " accounts found.");
        }
        System.out.println("==========================================\n");
    }

    /**
     * Finds a user in the DataStore by their user ID.
     * Returns the matching user or null if not found.
     */
    public User findUserByID(String userID) {
        for (User user : DataStore.getUsers()) {
            if (user.getUserID().equalsIgnoreCase(userID)) {
                return user;
            }
        }
        return null;
    }

    // Getters
    public User getSelectedUser() { return selectedUser; }
    public String getAccountAction() { return accountAction; }
    public String getAccountUpdateStatus() { return accountUpdateStatus; }

    // Setters
    public void setSelectedUser(User selectedUser) { this.selectedUser = selectedUser; }
    public void setAccountAction(String accountAction) { this.accountAction = accountAction; }
    public void setAccountUpdateStatus(String accountUpdateStatus) { this.accountUpdateStatus = accountUpdateStatus; }
}
