/**
 * Booking.java
 * Represents a flight booking in the Flight Management System.
 * Stores all booking details including flight, passengers,
 * payment status, and cancellation policy.
 * A booking can have multiple passengers and ancillary services.
 *
 * Author: Kibru Menore
 * Date: 04/21/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.models;

import java.util.ArrayList;
import java.util.List;

public class Booking {

    private String bookingID;
    private String bookingReference;  // unique code given to customer after booking
    private String bookingDate;
    private String bookingStatus;     // "CONFIRMED", "CANCELLED", "PENDING"
    private int passengerCount;
    private double totalPrice;
    private String cancellationPolicy; // e.g. "Free cancellation within 24 hours"
    private Flight flight;             // the flight this booking is for
    private List<Passenger> passengers; // list of passengers on this booking
    private List<AncillaryService> services; // optional add-on services

    // Default constructor initializes empty lists
    public Booking() {
        this.passengers = new ArrayList<>();
        this.services = new ArrayList<>();
        this.bookingStatus = "PENDING";
    }

    // Parameterized constructor for a fully initialized booking
    public Booking(String bookingID, String bookingReference, String bookingDate,
                   int passengerCount, double totalPrice, String cancellationPolicy,
                   Flight flight) {
        this.bookingID = bookingID;
        this.bookingReference = bookingReference;
        this.bookingDate = bookingDate;
        this.bookingStatus = "PENDING";
        this.passengerCount = passengerCount;
        this.totalPrice = totalPrice;
        this.cancellationPolicy = cancellationPolicy;
        this.flight = flight;
        this.passengers = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    /**
     * Confirms the booking by updating its status to CONFIRMED.
     * Called after successful payment is processed.
     */
    public void confirmBooking() {
        this.bookingStatus = "CONFIRMED";
        System.out.println("Booking " + bookingReference + " has been confirmed!");
    }

    /**
     * Cancels the booking by updating its status to CANCELLED.
     * Called when customer requests a cancellation.
     */
    public void cancelBooking() {
        this.bookingStatus = "CANCELLED";
        System.out.println("Booking " + bookingReference + " has been cancelled.");
    }

    /**
     * Generates a unique booking reference using the booking ID
     * and current timestamp to ensure uniqueness.
     */
    public String generateReference() {
        // combine booking ID with timestamp for a unique reference
        this.bookingReference = "BK" + bookingID + System.currentTimeMillis();
        return this.bookingReference;
    }

    /**
     * Adds a passenger to this booking.
     * Called during the booking process for each traveler.
     */
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
        System.out.println("Passenger " + passenger.getFullName() + " added to booking.");
    }

    /**
     * Adds an ancillary service such as baggage or seat selection.
     * Updates the total price to include the service cost.
     */
    public void addService(AncillaryService service) {
        services.add(service);
        // add service price to the total booking price
        this.totalPrice += service.getServicePrice();
        System.out.println("Service " + service.getServiceName() + " added to booking.");
    }

    /**
     * Returns a formatted summary of the booking details.
     * Used when displaying booking history to the customer.
     */
    public String getBookingSummary() {
        return "Booking Reference: " + bookingReference +
               " | Date: " + bookingDate +
               " | Status: " + bookingStatus +
               " | Flight: " + (flight != null ? flight.getFlightID() : "N/A") +
               " | Passengers: " + passengerCount +
               " | Total Price: $" + totalPrice;
    }

    // Getters
    public String getBookingID() { return bookingID; }
    public String getBookingReference() { return bookingReference; }
    public String getBookingDate() { return bookingDate; }
    public String getBookingStatus() { return bookingStatus; }
    public int getPassengerCount() { return passengerCount; }
    public double getTotalPrice() { return totalPrice; }
    public String getCancellationPolicy() { return cancellationPolicy; }
    public Flight getFlight() { return flight; }
    public List<Passenger> getPassengers() { return passengers; }
    public List<AncillaryService> getServices() { return services; }

    // Setters
    public void setBookingID(String bookingID) { this.bookingID = bookingID; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }
    public void setBookingStatus(String bookingStatus) { this.bookingStatus = bookingStatus; }
    public void setPassengerCount(int passengerCount) { this.passengerCount = passengerCount; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void setCancellationPolicy(String cancellationPolicy) { this.cancellationPolicy = cancellationPolicy; }
    public void setFlight(Flight flight) { this.flight = flight; }
    public void setPassengers(List<Passenger> passengers) { this.passengers = passengers; }
    public void setServices(List<AncillaryService> services) { this.services = services; }
}