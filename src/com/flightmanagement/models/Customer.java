/**
 * Customer.java
 * Represents a customer user in the Flight Management System.
 * Extends the User base class and adds customer-specific behavior
 * such as viewing booking history and canceling bookings.
 *
 * Author: Kibru Menore
 * Date: 04/20/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private String customerID;
    
    // stores all bookings made by this customer
    private List<Booking> bookingHistory;

    // Default constructor initializes an empty booking history
    public Customer() {
        super();
        this.bookingHistory = new ArrayList<>();
    }

    // Parameterized constructor passes shared fields up to User
    public Customer(String userID, String fullName, String email,
                    String password, String phoneNumber, String customerID) {
        super(userID, fullName, email, password, phoneNumber, "CUSTOMER");
        this.customerID = customerID;
        this.bookingHistory = new ArrayList<>();
    }

    /**
     * Adds a new booking to this customer's booking history.
     * Called after a successful flight booking.
     */
    public void addBooking(Booking booking) {
        bookingHistory.add(booking);
        System.out.println("Booking added to history for customer: " + getFullName());
    }

    /**
     * Displays all past and current bookings for this customer.
     * If no bookings exist, notifies the customer.
     */
    public void viewBookingHistory() {
        if (bookingHistory.isEmpty()) {
            System.out.println("No bookings found for " + getFullName());
            return;
        }
        System.out.println("Booking history for " + getFullName() + ":");
        for (Booking booking : bookingHistory) {
            System.out.println(booking.getBookingSummary());
        }
    }

    /**
     * Cancels a booking by its reference number.
     * Searches through booking history and cancels if found.
     */
    public void cancelBooking(String bookingReference) {
        for (Booking booking : bookingHistory) {
            if (booking.getBookingReference().equals(bookingReference)) {
                booking.cancelBooking();
                System.out.println("Booking " + bookingReference + " has been cancelled.");
                return;
            }
        }
        // if we reach here, no matching booking was found
        System.out.println("No booking found with reference: " + bookingReference);
    }

    // Getters
    public String getCustomerID() { return customerID; }
    public List<Booking> getBookingHistory() { return bookingHistory; }

    // Setters
    public void setCustomerID(String customerID) { this.customerID = customerID; }
    public void setBookingHistory(List<Booking> bookingHistory) { this.bookingHistory = bookingHistory; }
}