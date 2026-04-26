/**
 * Airport.java
 * Represents an airport in the Flight Management System.
 * Stores airport details and validates IATA codes used
 * during flight searches.
 *
 * Author: Kibru Menore
 * Date: 04/21/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.models;

public class Airport {

    private String airportCode; // 3-letter IATA code e.g. "IAH", "JFK"
    private String airportName;
    private String city;
    private String country;

    // Default constructor
    public Airport() {}

    // Parameterized constructor to create a fully initialized airport
    public Airport(String airportCode, String airportName, String city, String country) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.city = city;
        this.country = country;
    }

    /**
     * Validates that the IATA code is exactly 3 uppercase letters.
     * Returns true if valid, false otherwise.
     */
    public boolean validate() {
        if (airportCode == null || airportCode.length() != 3) {
            System.out.println("Invalid IATA code: must be exactly 3 characters.");
            return false;
        }
        // check that all characters are letters
        if (!airportCode.matches("[A-Z]{3}")) {
            System.out.println("Invalid IATA code: must contain only uppercase letters.");
            return false;
        }
        return true;
    }

    /**
     * Searches for an airport by its IATA code from a given list.
     * Returns the matching airport or null if not found.
     */
    public static Airport searchByCode(String code, java.util.List<Airport> airports) {
        for (Airport airport : airports) {
            if (airport.getAirportCode().equalsIgnoreCase(code)) {
                return airport;
            }
        }
        // no airport found with the given code
        System.out.println("No airport found with IATA code: " + code);
        return null;
    }

    /**
     * Returns a formatted string of the airport details.
     * Used when displaying airport information to the user.
     */
    public String getAirportInfo() {
        return "Code: " + airportCode +
               " | Airport: " + airportName +
               " | City: " + city +
               " | Country: " + country;
    }

    // Getters
    public String getAirportCode() { return airportCode; }
    public String getAirportName() { return airportName; }
    public String getCity() { return city; }
    public String getCountry() { return country; }

    // Setters
    public void setAirportCode(String airportCode) { this.airportCode = airportCode; }
    public void setAirportName(String airportName) { this.airportName = airportName; }
    public void setCity(String city) { this.city = city; }
    public void setCountry(String country) { this.country = country; }
}