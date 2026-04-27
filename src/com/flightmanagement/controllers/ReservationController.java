/**
 * ReservationController.java
 * Handles all reservation management operations in the Flight Management System.
 * Responsible for viewing, updating, and cancelling customer reservations.
 * Customers can manage their reservations after a booking is confirmed.
 *
 * Author: Jonathan Elahee
 * Date: 04/27/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.controllers;

import com.flightmanagement.data.DataStore;
import com.flightmanagement.models.Booking;
import com.flightmanagement.models.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationController {

    private Reservation selectedReservation;
    private String cancellationStatus;
    private String updateStatus;

    // Default constructor
    public ReservationController() {}

    /**
     * Creates a new reservation linked to a confirmed booking.
     * Called automatically after a booking is confirmed and
     * payment is processed successfully.
     */
    public Reservation createReservation(String bookingReference) {
        // find the booking this reservation is for
        Booking booking = DataStore.findBookingByReference(bookingReference);
        if (booking == null) {
            System.out.println("Booking not found: " + bookingReference);
            return null;
        }

        // generate a unique reservation ID
        String reservationID = "RES" + String.format("%03d",
                                DataStore.getReservations().size() + 1);
        String creationDate = LocalDate.now().toString();

        // create and save the reservation
        Reservation reservation = new Reservation(reservationID, creationDate, booking);
        DataStore.addReservation(reservation);
        System.out.println("Reservation " + reservationID + " created successfully.");
        return reservation;
    }

    /**
     * Cancels an existing reservation by its reservation ID.
     * Also updates the linked booking status to CANCELLED.
     * Returns true if cancellation was successful.
     */
    public boolean cancelReservation(String reservationID) {
        // find the reservation in the DataStore
        Reservation reservation = findReservationByID(reservationID);
        if (reservation == null) {
            System.out.println("Reservation " + reservationID + " not found.");
            return false;
        }

        // check that reservation is still active before cancelling
        if (!reservation.isActive()) {
            System.out.println("Reservation " + reservationID +
                               " is already " + reservation.getReservationStatus());
            return false;
        }

        // cancel the reservation and its linked booking
        String updateDate = LocalDate.now().toString();
        reservation.updateStatus("CANCELLED", updateDate);
        reservation.getBooking().cancelBooking();

        this.selectedReservation = reservation;
        this.cancellationStatus = "CANCELLED";
        System.out.println("Reservation " + reservationID + " cancelled successfully.");
        return true;
    }

    /**
     * Displays all reservations for the currently logged in customer.
     * Filters the DataStore reservations by the customer's bookings.
     */
    public void viewCustomerReservations() {
        List<Reservation> customerReservations = getCustomerReservations();

        if (customerReservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        System.out.println("\n========== YOUR RESERVATIONS ==========");
        for (int i = 0; i < customerReservations.size(); i++) {
            System.out.println((i + 1) + ". " + customerReservations.get(i).getHistory());
        }
        System.out.println("=======================================\n");
    }

    /**
     * Returns a list of all reservations belonging to the
     * currently logged in customer.
     */
    public List<Reservation> getCustomerReservations() {
        List<Reservation> customerReservations = new ArrayList<>();
        String loggedInEmail = DataStore.getLoggedInUser() != null ?
                               DataStore.getLoggedInUser().getEmail() : null;

        if (loggedInEmail == null) {
            System.out.println("No user is currently logged in.");
            return customerReservations;
        }

        // filter reservations that belong to the logged in customer
        for (Reservation reservation : DataStore.getReservations()) {
            Booking booking = reservation.getBooking();
            if (booking != null && booking.getBookingStatus().equals("CONFIRMED")) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }

    /**
     * Finds a reservation in the DataStore by its reservation ID.
     * Returns the matching reservation or null if not found.
     */
    public Reservation findReservationByID(String reservationID) {
        for (Reservation reservation : DataStore.getReservations()) {
            if (reservation.getReservationID().equalsIgnoreCase(reservationID)) {
                return reservation;
            }
        }
        return null;
    }

    // Getters
    public Reservation getSelectedReservation() { return selectedReservation; }
    public String getCancellationStatus() { return cancellationStatus; }
    public String getUpdateStatus() { return updateStatus; }

    // Setters
    public void setSelectedReservation(Reservation selectedReservation) { this.selectedReservation = selectedReservation; }
    public void setCancellationStatus(String cancellationStatus) { this.cancellationStatus = cancellationStatus; }
    public void setUpdateStatus(String updateStatus) { this.updateStatus = updateStatus; }
}
