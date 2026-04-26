/**
 * Reservation.java
 * Represents a reservation record in the Flight Management System.
 * Tracks the status and history of a customer's flight reservation.
 * A reservation is created when a booking is confirmed and can be
 * updated or cancelled by the customer.
 *
 * Author: Kibru Menore
 * Date: 04/22/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.models;

public class Reservation {

    private String reservationID;
    private String reservationStatus; // "ACTIVE", "CANCELLED", "COMPLETED"
    private String creationDate;
    private String lastUpdated;
    private Booking booking;          // the booking this reservation is linked to

    // Default constructor sets status to ACTIVE on creation
    public Reservation() {
        this.reservationStatus = "ACTIVE";
    }

    // Parameterized constructor for a fully initialized reservation
    public Reservation(String reservationID, String creationDate, Booking booking) {
        this.reservationID = reservationID;
        this.reservationStatus = "ACTIVE";
        this.creationDate = creationDate;
        this.lastUpdated = creationDate;
        this.booking = booking;
    }

    /**
     * Updates the reservation status and records the update date.
     * Called when a reservation is modified or cancelled.
     */
    public void updateStatus(String newStatus, String updateDate) {
        this.reservationStatus = newStatus;
        this.lastUpdated = updateDate;
        System.out.println("Reservation " + reservationID +
                           " status updated to: " + newStatus);
    }

    /**
     * Returns a formatted history of this reservation including
     * its current status and linked booking details.
     */
    public String getHistory() {
        return "Reservation ID: " + reservationID +
               " | Status: " + reservationStatus +
               " | Created: " + creationDate +
               " | Last Updated: " + lastUpdated +
               " | Booking: " + (booking != null ? booking.getBookingReference() : "N/A");
    }

    /**
     * Checks if this reservation is currently active.
     * Returns true if status is ACTIVE, false otherwise.
     */
    public boolean isActive() {
        return reservationStatus.equals("ACTIVE");
    }

    // Getters
    public String getReservationID() { return reservationID; }
    public String getReservationStatus() { return reservationStatus; }
    public String getCreationDate() { return creationDate; }
    public String getLastUpdated() { return lastUpdated; }
    public Booking getBooking() { return booking; }

    // Setters
    public void setReservationID(String reservationID) { this.reservationID = reservationID; }
    public void setReservationStatus(String reservationStatus) { this.reservationStatus = reservationStatus; }
    public void setCreationDate(String creationDate) { this.creationDate = creationDate; }
    public void setLastUpdated(String lastUpdated) { this.lastUpdated = lastUpdated; }
    public void setBooking(Booking booking) { this.booking = booking; }
}