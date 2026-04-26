/**
 * DataStore.java
 * Central in-memory data manager for the Flight Management System.
 * Acts as a simple database that stores all users, flights, airports,
 * bookings, payments, reservations, and reports during runtime.
 * All controllers access and modify data through this class.
 *
 * Author: Kibru Menore
 * Date: 04/23/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.data;

import com.flightmanagement.models.User;
import com.flightmanagement.models.Customer;
import com.flightmanagement.models.AirlineStaff;
import com.flightmanagement.models.Administrator;
import com.flightmanagement.models.Flight;
import com.flightmanagement.models.Airport;
import com.flightmanagement.models.Booking;
import com.flightmanagement.models.Payment;
import com.flightmanagement.models.Reservation;
import com.flightmanagement.models.Report;
import com.flightmanagement.models.AncillaryService;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    // lists that act as our in-memory database tables
    private static List<User> users = new ArrayList<>();
    private static List<Flight> flights = new ArrayList<>();
    private static List<Airport> airports = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static List<Payment> payments = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static List<Report> reports = new ArrayList<>();
    private static List<AncillaryService> services = new ArrayList<>();

    // tracks the currently logged in user during the session
    private static User loggedInUser = null;

    /**
     * Finds a user by their email address.
     * Used during login to locate the matching account.
     */
    public static User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Finds a flight by its flight ID.
     * Used when booking a specific flight.
     */
    public static Flight findFlightByID(String flightID) {
        for (Flight flight : flights) {
            if (flight.getFlightID().equalsIgnoreCase(flightID)) {
                return flight;
            }
        }
        return null;
    }

    /**
     * Finds an airport by its IATA code.
     * Used when validating departure and arrival locations.
     */
    public static Airport findAirportByCode(String code) {
        for (Airport airport : airports) {
            if (airport.getAirportCode().equalsIgnoreCase(code)) {
                return airport;
            }
        }
        return null;
    }

    /**
     * Finds a booking by its booking reference number.
     * Used when customer wants to view or cancel a booking.
     */
    public static Booking findBookingByReference(String reference) {
        for (Booking booking : bookings) {
            if (booking.getBookingReference().equalsIgnoreCase(reference)) {
                return booking;
            }
        }
        return null;
    }

    /**
     * Searches for flights matching the given departure and
     * arrival airport codes. Returns a list of matching flights.
     */
    public static List<Flight> searchFlights(String departureCode, String arrivalCode) {
        List<Flight> results = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDepartureAirportCode().equalsIgnoreCase(departureCode) &&
                flight.getArrivalAirportCode().equalsIgnoreCase(arrivalCode)) {
                results.add(flight);
            }
        }
        return results;
    }

    // methods to add new records to each list
    public static void addUser(User user) { users.add(user); }
    public static void addFlight(Flight flight) { flights.add(flight); }
    public static void addAirport(Airport airport) { airports.add(airport); }
    public static void addBooking(Booking booking) { bookings.add(booking); }
    public static void addPayment(Payment payment) { payments.add(payment); }
    public static void addReservation(Reservation reservation) { reservations.add(reservation); }
    public static void addReport(Report report) { reports.add(report); }
    public static void addService(AncillaryService service) { services.add(service); }

    // methods to remove records from each list
    public static void removeUser(User user) { users.remove(user); }
    public static void removeFlight(Flight flight) { flights.remove(flight); }
    public static void removeBooking(Booking booking) { bookings.remove(booking); }

    // Getters for all lists
    public static List<User> getUsers() { return users; }
    public static List<Flight> getFlights() { return flights; }
    public static List<Airport> getAirports() { return airports; }
    public static List<Booking> getBookings() { return bookings; }
    public static List<Payment> getPayments() { return payments; }
    public static List<Reservation> getReservations() { return reservations; }
    public static List<Report> getReports() { return reports; }
    public static List<AncillaryService> getServices() { return services; }

    // logged in user session management
    public static User getLoggedInUser() { return loggedInUser; }
    public static void setLoggedInUser(User user) { loggedInUser = user; }
    public static void clearLoggedInUser() { loggedInUser = null; }
}