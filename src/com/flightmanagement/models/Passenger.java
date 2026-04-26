/**
 * Passenger.java
 * Represents a passenger in the Flight Management System.
 * Stores personal details of each passenger included in a booking.
 * A single booking can have multiple passengers.
 *
 * Author: Kibru Menore
 * Date: 04/21/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.models;

public class Passenger {

    private String passengerID;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String passportNumber;
    private String nationality;
    private String seatNumber; // assigned seat on the flight

    // Default constructor
    public Passenger() {}

    // Parameterized constructor to create a fully initialized passenger
    public Passenger(String passengerID, String firstName, String lastName,
                     String dateOfBirth, String passportNumber,
                     String nationality, String seatNumber) {
        this.passengerID = passengerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
        this.nationality = nationality;
        this.seatNumber = seatNumber;
    }

    /**
     * Returns the passenger's full name by combining
     * first and last name.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Returns a formatted summary of the passenger's details.
     * Used when displaying booking information.
     */
    public String getPassengerInfo() {
        return "Passenger ID: " + passengerID +
               " | Name: " + getFullName() +
               " | Date of Birth: " + dateOfBirth +
               " | Passport: " + passportNumber +
               " | Nationality: " + nationality +
               " | Seat: " + seatNumber;
    }

    // Getters
    public String getPassengerID() { return passengerID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getPassportNumber() { return passportNumber; }
    public String getNationality() { return nationality; }
    public String getSeatNumber() { return seatNumber; }

    // Setters
    public void setPassengerID(String passengerID) { this.passengerID = passengerID; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
}