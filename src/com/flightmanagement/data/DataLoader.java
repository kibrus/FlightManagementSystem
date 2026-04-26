/**
 * DataLoader.java
 * Loads sample seed data into the DataStore when the application starts.
 * Provides pre-loaded users, flights, airports, and ancillary services
 * so the system has data to work with during testing and demonstration.
 *
 * Author: Kibru Menore
 * Date: 04/23/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.data;

import com.flightmanagement.models.Administrator;
import com.flightmanagement.models.AirlineStaff;
import com.flightmanagement.models.AncillaryService;
import com.flightmanagement.models.Airport;
import com.flightmanagement.models.Customer;
import com.flightmanagement.models.Flight;

public class DataLoader {

    /**
     * Loads all seed data into the DataStore.
     * Called once when the application starts in Main.java.
     */
    public static void loadAll() {
        loadAirports();
        loadFlights();
        loadUsers();
        loadAncillaryServices();
        System.out.println("Sample data loaded successfully.");
    }

    /**
     * Loads sample airports with their IATA codes into the DataStore.
     * Includes major US airports used in flight routes.
     */
    private static void loadAirports() {
        DataStore.addAirport(new Airport("IAH", "George Bush Intercontinental Airport", "Houston", "USA"));
        DataStore.addAirport(new Airport("JFK", "John F. Kennedy International Airport", "New York", "USA"));
        DataStore.addAirport(new Airport("LAX", "Los Angeles International Airport", "Los Angeles", "USA"));
        DataStore.addAirport(new Airport("ORD", "O'Hare International Airport", "Chicago", "USA"));
        DataStore.addAirport(new Airport("DFW", "Dallas Fort Worth International Airport", "Dallas", "USA"));
        DataStore.addAirport(new Airport("MIA", "Miami International Airport", "Miami", "USA"));
        DataStore.addAirport(new Airport("ATL", "Hartsfield-Jackson Atlanta International Airport", "Atlanta", "USA"));
        DataStore.addAirport(new Airport("LHR", "Heathrow Airport", "London", "UK"));
    }

    /**
     * Loads sample flights into the DataStore.
     * Includes both LCC and FSA airline types with different ticket classes.
     */
    private static void loadFlights() {
        // Houston to New York - United Airlines (FSA) Economy
        DataStore.addFlight(new Flight(
            "UA101", "United Airlines",
            "2026-05-01 08:00", "2026-05-01 12:30",
            "4h 30m", 0, "Economy", "B",
            180, 299.99, "FSA", "IAH", "JFK"
        ));

        // Houston to New York - United Airlines (FSA) Business
        DataStore.addFlight(new Flight(
            "UA102", "United Airlines",
            "2026-05-01 08:00", "2026-05-01 12:30",
            "4h 30m", 0, "Business", "C",
            40, 799.99, "FSA", "IAH", "JFK"
        ));

        // Houston to Los Angeles - Southwest Airlines (LCC) Economy
        DataStore.addFlight(new Flight(
            "SW201", "Southwest Airlines",
            "2026-05-02 10:00", "2026-05-02 12:00",
            "2h 00m", 0, "Economy", "B",
            200, 149.99, "LCC", "IAH", "LAX"
        ));

        // Houston to Chicago - American Airlines (FSA) Economy
        DataStore.addFlight(new Flight(
            "AA301", "American Airlines",
            "2026-05-03 07:00", "2026-05-03 09:30",
            "2h 30m", 0, "Economy", "B",
            160, 199.99, "FSA", "IAH", "ORD"
        ));

        // Houston to Miami - Spirit Airlines (LCC) Economy
        DataStore.addFlight(new Flight(
            "NK401", "Spirit Airlines",
            "2026-05-04 14:00", "2026-05-04 17:30",
            "3h 30m", 0, "Economy", "B",
            220, 89.99, "LCC", "IAH", "MIA"
        ));

        // New York to London - British Airways (FSA) First Class
        DataStore.addFlight(new Flight(
            "BA501", "British Airways",
            "2026-05-05 18:00", "2026-05-06 06:00",
            "7h 00m", 0, "First", "F",
            20, 2499.99, "FSA", "JFK", "LHR"
        ));

        // Dallas to Atlanta - Delta Airlines (FSA) Economy
        DataStore.addFlight(new Flight(
            "DL601", "Delta Airlines",
            "2026-05-06 09:00", "2026-05-06 11:30",
            "2h 30m", 0, "Economy", "B",
            150, 179.99, "FSA", "DFW", "ATL"
        ));
    }

    /**
     * Loads sample users into the DataStore including one customer,
     * one airline staff member, and one administrator.
     */
    private static void loadUsers() {
        // sample customer account for testing
        DataStore.addUser(new Customer(
            "U001", "John Smith", "john@email.com",
            "password123", "713-555-0101", "C001"
        ));

        // sample airline staff account for testing
        DataStore.addUser(new AirlineStaff(
            "U002", "Sarah Johnson", "sarah@united.com",
            "staffpass123", "713-555-0202",
            "S001", "EMP002", "United Airlines", "SENIOR"
        ));

        // sample administrator account for testing
        DataStore.addUser(new Administrator(
            "U003", "Admin User", "admin@flightms.com",
            "adminpass123", "713-555-0303",
            "A001", "EMP003", "FULL"
        ));
    }

    /**
     * Loads sample ancillary services that customers can add to bookings.
     * Includes common add-ons like baggage and seat selection.
     */
    private static void loadAncillaryServices() {
        DataStore.addService(new AncillaryService(
            "SVC001", "Extra Baggage",
            "Add one extra checked bag up to 23kg", 35.00
        ));
        DataStore.addService(new AncillaryService(
            "SVC002", "Seat Selection",
            "Choose your preferred seat on the flight", 15.00
        ));
        DataStore.addService(new AncillaryService(
            "SVC003", "Priority Boarding",
            "Board the plane before general passengers", 10.00
        ));
        DataStore.addService(new AncillaryService(
            "SVC004", "Travel Insurance",
            "Coverage for trip cancellation and delays", 25.00
        ));
    }
}