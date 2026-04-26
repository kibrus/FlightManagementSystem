/**
 * BookingController.java
 * Handles all flight booking operations in the Flight Management System.
 * Responsible for creating bookings, calculating fares, adding passengers,
 * and managing ancillary services for a booking.
 * Only accessible by authenticated customers.
 *
 * Author: Kibru Menore
 * Date: 04/24/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.controllers;

import com.flightmanagement.data.DataStore;
import com.flightmanagement.models.AncillaryService;
import com.flightmanagement.models.Booking;
import com.flightmanagement.models.Customer;
import com.flightmanagement.models.Flight;
import com.flightmanagement.models.Passenger;
import com.flightmanagement.models.User;

import java.time.LocalDate;
import java.util.List;

public class BookingController {

    private String selectedFlightID;
    private Booking currentBooking;
    private double fareTotal;

    // Default constructor
    public BookingController() {}

    /**
     * Creates a new booking for the logged in customer.
     * Validates flight availability before creating the booking.
     * Returns the created booking or null if booking failed.
     */
    public Booking createBooking(String flightID, int passengerCount) {
        // check that a user is logged in before booking
        User loggedInUser = DataStore.getLoggedInUser();
        if (loggedInUser == null || !loggedInUser.getRole().equals("CUSTOMER")) {
            System.out.println("You must be logged in as a customer to book a flight.");
            return null;
        }

        // find the selected flight in the DataStore
        Flight flight = DataStore.findFlightByID(flightID);
        if (flight == null) {
            System.out.println("Flight " + flightID + " not found.");
            return null;
        }

        // check that enough seats are available for all passengers
        if (!flight.isAvailable(passengerCount)) {
            System.out.println("Not enough seats available on flight " + flightID);
            return null;
        }

        // calculate the total fare based on passenger count
        this.fareTotal = calculateFare(flight.getFare(), passengerCount);
        this.selectedFlightID = flightID;

        // generate a unique booking ID and reference
        String bookingID = "BK" + String.format("%03d", DataStore.getBookings().size() + 1);
        String bookingDate = LocalDate.now().toString();
        String reference = "REF" + bookingID + System.currentTimeMillis();

        // create the booking and add it to the DataStore
        currentBooking = new Booking(bookingID, reference, bookingDate,
                                     passengerCount, fareTotal,
                                     "Free cancellation within 24 hours", flight);
        DataStore.addBooking(currentBooking);

        // update the flight's available seats
        flight.updateCapacity(passengerCount);

        System.out.println("Booking created. Reference: " + reference);
        System.out.println("Total fare: $" + fareTotal);
        return currentBooking;
    }

    /**
     * Calculates the total fare by multiplying the base fare
     * by the number of passengers.
     */
    public double calculateFare(double baseFare, int passengerCount) {
        double total = baseFare * passengerCount;
        System.out.println("Fare calculation: $" + baseFare +
                           " x " + passengerCount + " passenger(s) = $" + total);
        return total;
    }

    /**
     * Adds a passenger to the current booking.
     * Called once for each traveler during the booking process.
     */
    public void addPassengerToBooking(String firstName, String lastName,
                                      String dateOfBirth, String passportNumber,
                                      String nationality, String seatNumber) {
        if (currentBooking == null) {
            System.out.println("No active booking found.");
            return;
        }

        // generate a unique passenger ID
        String passengerID = "P" + String.format("%03d",
                             currentBooking.getPassengers().size() + 1);

        Passenger passenger = new Passenger(passengerID, firstName, lastName,
                                            dateOfBirth, passportNumber,
                                            nationality, seatNumber);
        currentBooking.addPassenger(passenger);
    }

    /**
     * Adds an ancillary service to the current booking.
     * Updates the total fare to include the service price.
     */
    public void addServiceToBooking(String serviceID) {
        if (currentBooking == null) {
            System.out.println("No active booking found.");
            return;
        }

        // find the requested service in the DataStore
        AncillaryService service = null;
        for (AncillaryService s : DataStore.getServices()) {
            if (s.getServiceID().equalsIgnoreCase(serviceID)) {
                service = s;
                break;
            }
        }

        if (service == null) {
            System.out.println("Service " + serviceID + " not found.");
            return;
        }

        currentBooking.addService(service);
        this.fareTotal = currentBooking.getTotalPrice();
    }

    /**
     * Confirms the current booking after successful payment.
     * Links the booking to the logged in customer's history.
     */
    public void confirmBooking() {
        if (currentBooking == null) {
            System.out.println("No active booking to confirm.");
            return;
        }

        currentBooking.confirmBooking();

        // add booking to the customer's history
        User loggedInUser = DataStore.getLoggedInUser();
        if (loggedInUser instanceof Customer) {
            ((Customer) loggedInUser).addBooking(currentBooking);
        }
    }

    /**
     * Displays available ancillary services the customer can add.
     */
    public void displayAvailableServices() {
        List<AncillaryService> services = DataStore.getServices();
        System.out.println("\n========== AVAILABLE SERVICES ==========");
        for (int i = 0; i < services.size(); i++) {
            System.out.println((i + 1) + ". " + services.get(i).getServiceInfo());
        }
        System.out.println("=========================================\n");
    }

    // Getters
    public String getSelectedFlightID() { return selectedFlightID; }
    public Booking getCurrentBooking() { return currentBooking; }
    public double getFareTotal() { return fareTotal; }

    // Setters
    public void setSelectedFlightID(String selectedFlightID) { this.selectedFlightID = selectedFlightID; }
    public void setCurrentBooking(Booking currentBooking) { this.currentBooking = currentBooking; }
    public void setFareTotal(double fareTotal) { this.fareTotal = fareTotal; }
}