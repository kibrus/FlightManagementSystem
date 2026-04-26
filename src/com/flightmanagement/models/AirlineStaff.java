/**
 * AirlineStaff.java
 * Represents an airline staff member in the Flight Management System.
 * Extends the User base class and adds staff-specific behavior
 * such as managing flight listings.
 *
 * Author: Kibru Menore
 * Date: 04/20/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.models;

public class AirlineStaff extends User {

    private String staffID;
    private String employeeNumber;
    private String assignedAirline; // the airline this staff member works for
    private String accessLevel;     // "BASIC" or "SENIOR"

    // Default constructor
    public AirlineStaff() {
        super();
    }

    // Parameterized constructor passes shared fields up to User
    public AirlineStaff(String userID, String fullName, String email,
                        String password, String phoneNumber, String staffID,
                        String employeeNumber, String assignedAirline, String accessLevel) {
        super(userID, fullName, email, password, phoneNumber, "STAFF");
        this.staffID = staffID;
        this.employeeNumber = employeeNumber;
        this.assignedAirline = assignedAirline;
        this.accessLevel = accessLevel;
    }

    /**
     * Displays the staff management menu options.
     * Actual flight operations are handled by FlightController.
     */
    public void manageFlight() {
        System.out.println("Flight Management Options:");
        System.out.println("1. Add new flight");
        System.out.println("2. Update existing flight");
        System.out.println("3. Remove flight");
        System.out.println("Select an option:");
    }

    /**
     * Returns a summary of this staff member's information
     * including their assigned airline and access level.
     */
    public String getStaffInfo() {
        return "Staff ID: " + staffID +
               " | Name: " + getFullName() +
               " | Airline: " + assignedAirline +
               " | Access Level: " + accessLevel;
    }

    // Getters
    public String getStaffID() { return staffID; }
    public String getEmployeeNumber() { return employeeNumber; }
    public String getAssignedAirline() { return assignedAirline; }
    public String getAccessLevel() { return accessLevel; }

    // Setters
    public void setStaffID(String staffID) { this.staffID = staffID; }
    public void setEmployeeNumber(String employeeNumber) { this.employeeNumber = employeeNumber; }
    public void setAssignedAirline(String assignedAirline) { this.assignedAirline = assignedAirline; }
    public void setAccessLevel(String accessLevel) { this.accessLevel = accessLevel; }
}