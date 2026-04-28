/**
 * FlightController.java
 * Handles all flight management operations in the Flight Management System.
 * Responsible for adding, updating, and removing flight listings.
 * Only accessible by authenticated airline staff members.
 *
 * Author: Kibru Menore
 * Date: 04/23/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.controllers;

import com.flightmanagement.data.DataStore;
import com.flightmanagement.models.Flight;

import java.util.List;

public class FlightController {

    private Flight selectedFlight;
    private String updateStatus;

    // Default constructor
    public FlightController() {}

    /**
     * Adds a new flight listing to the DataStore.
     * Called by airline staff when creating a new flight route.
     * Returns true if flight was added successfully.
     */
    public boolean addFlight(String flightID, String airlineName,
                             String departureDateTime, String arrivalDateTime,
                             String duration, int numberOfStops, String ticketClass,
                             String ticketClassCode, int seatCapacity, double fare,
                             String airlineType, String departureAirportCode,
                             String arrivalAirportCode) {

        // check if a flight with this ID already exists
        if (DataStore.findFlightByID(flightID) != null) {
            System.out.println("Flight with ID " + flightID + " already exists.");
            return false;
        }

        // validate that both airport codes exist in the system
        if (DataStore.findAirportByCode(departureAirportCode) == null) {
            System.out.println("Invalid departure airport code: " + departureAirportCode);
            return false;
        }
        if (DataStore.findAirportByCode(arrivalAirportCode) == null) {
            System.out.println("Invalid arrival airport code: " + arrivalAirportCode);
            return false;
        }

        // create and save the new flight
        Flight newFlight = new Flight(flightID, airlineName, departureDateTime,
                                      arrivalDateTime, duration, numberOfStops,
                                      ticketClass, ticketClassCode, seatCapacity,
                                      fare, airlineType, departureAirportCode,
                                      arrivalAirportCode);
        DataStore.addFlight(newFlight);
        this.updateStatus = "ADDED";
        System.out.println("Flight " + flightID + " added successfully.");
        return true;
    }

    /**
     * Updates the fare of an existing flight.
     * Called by airline staff when adjusting ticket prices.
     */
    public boolean updateFlightFare(String flightID, double newFare) {
        Flight flight = DataStore.findFlightByID(flightID);

        if (flight == null) {
            System.out.println("Flight " + flightID + " not found.");
            return false;
        }

        // update the fare and record the change
        double oldFare = flight.getFare();
        flight.setFare(newFare);
        this.selectedFlight = flight;
        this.updateStatus = "UPDATED";
        System.out.println("Flight " + flightID + " fare updated from $" +
                           oldFare + " to $" + newFare);
        return true;
    }

    /**
     * Updates the seat capacity of an existing flight.
     * Called by airline staff when adjusting available seats.
     */
    public boolean updateFlightCapacity(String flightID, int newCapacity) {
        Flight flight = DataStore.findFlightByID(flightID);

        if (flight == null) {
            System.out.println("Flight " + flightID + " not found.");
            return false;
        }

        flight.setSeatCapacity(newCapacity);
        flight.setAvailableSeats(newCapacity);
        this.selectedFlight = flight;
        this.updateStatus = "UPDATED";
        System.out.println("Flight " + flightID + " capacity updated to " + newCapacity);
        return true;
    }

    /**
     * Removes a flight listing from the DataStore.
     * Called by airline staff when cancelling a flight route.
     */
    public boolean removeFlight(String flightID) {
        Flight flight = DataStore.findFlightByID(flightID);

        if (flight == null) {
            System.out.println("Flight " + flightID + " not found.");
            return false;
        }

        DataStore.removeFlight(flight);
        this.updateStatus = "REMOVED";
        System.out.println("Flight " + flightID + " removed successfully.");
        return true;
    }

    /**
     * Displays all flights currently stored in the DataStore.
     * Used by airline staff to view all available flight listings.
     */
    public void displayAllFlights() {
        List<Flight> flights = DataStore.getFlights();

        if (flights.isEmpty()) {
            System.out.println("No flights currently in the system.");
            return;
        }

        System.out.println("\n========== ALL FLIGHTS ==========");
        for (int i = 0; i < flights.size(); i++) {
            System.out.println((i + 1) + ". " + flights.get(i).getFlightDetails());
        }
        System.out.println("==================================\n");
    }

    // Getters
    public Flight getSelectedFlight() { return selectedFlight; }
    public String getUpdateStatus() { return updateStatus; }

    // Setters
    public void setSelectedFlight(Flight selectedFlight) { this.selectedFlight = selectedFlight; }
    public void setUpdateStatus(String updateStatus) { this.updateStatus = updateStatus; }
}
