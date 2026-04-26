/**
 * Administrator.java
 * Represents a system administrator in the Flight Management System.
 * Extends the User base class and adds admin-specific behavior
 * such as managing user accounts and generating reports.
 *
 * Author: Kibru Menore
 * Date: 04/20/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.models;

public class Administrator extends User {

    private String adminID;
    private String employeeNumber;
    private String accessLevel; // "FULL" or "LIMITED"

    // Default constructor
    public Administrator() {
        super();
    }

    // Parameterized constructor passes shared fields up to User
    public Administrator(String userID, String fullName, String email,
                         String password, String phoneNumber, String adminID,
                         String employeeNumber, String accessLevel) {
        super(userID, fullName, email, password, phoneNumber, "ADMIN");
        this.adminID = adminID;
        this.employeeNumber = employeeNumber;
        this.accessLevel = accessLevel;
    }

    /**
     * Displays the user management menu options.
     * Actual account operations are handled by UserController.
     */
    public void manageUsers() {
        System.out.println("User Management Options:");
        System.out.println("1. View all users");
        System.out.println("2. Activate user account");
        System.out.println("3. Suspend user account");
        System.out.println("4. Delete user account");
        System.out.println("Select an option:");
    }

    /**
     * Displays the report generation menu options.
     * Actual report generation is handled by ReportController.
     */
    public void generateReport() {
        System.out.println("Report Options:");
        System.out.println("1. Sales report");
        System.out.println("2. Revenue report");
        System.out.println("3. Booking report");
        System.out.println("Select an option:");
    }

    /**
     * Returns a summary of this administrator's information
     * including their admin ID and access level.
     */
    public String getAdminInfo() {
        return "Admin ID: " + adminID +
               " | Name: " + getFullName() +
               " | Employee Number: " + employeeNumber +
               " | Access Level: " + accessLevel;
    }

    // Getters
    public String getAdminID() { return adminID; }
    public String getEmployeeNumber() { return employeeNumber; }
    public String getAccessLevel() { return accessLevel; }

    // Setters
    public void setAdminID(String adminID) { this.adminID = adminID; }
    public void setEmployeeNumber(String employeeNumber) { this.employeeNumber = employeeNumber; }
    public void setAccessLevel(String accessLevel) { this.accessLevel = accessLevel; }
}