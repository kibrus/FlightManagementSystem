/**
 * FlightResultsUI.java
 * Displays the flight results interface for the Flight Management System.
 * Shows a list of available flights from the search results and allows
 * the customer to select a flight to proceed with booking.
 *
 * Author: Muhammad Moosa Khalid
 * Date: 04/25/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.views;

import com.flightmanagement.models.Flight;

import java.util.List;
import java.util.Scanner;

public class FlightResultsUI {

    private Scanner scanner;

    public FlightResultsUI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the list of flights from search results and
     * prompts the customer to select one to book.
     * Returns the selected flight or null if no valid
     * selection was made.
     */
    public Flight display(List<Flight> flights) {
        if (flights == null || flights.isEmpty()) {
            System.out.println("No flights available to display.");
            return null;
        }

        System.out.println("\n========== AVAILABLE FLIGHTS ==========");
        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            // display a numbered list of flights with key details
            System.out.println((i + 1) + ". Flight: " + flight.getFlightID() +
                               " | " + flight.getAirlineName() +
                               " | " + flight.getDepartureAirportCode() +
                               " -> " + flight.getArrivalAirportCode() +
                               " | Departure: " + flight.getDepartureDateTime() +
                               " | Duration: " + flight.getDuration() +
                               " | Class: " + flight.getTicketClass() +
                               " (" + flight.getTicketClassCode() + ")" +
                               " | Fare: $" + flight.getFare() +
                               " | Seats: " + flight.getAvailableSeats() +
                               " | Type: " + flight.getAirlineType());
        }
        System.out.println("=======================================\n");

        // prompt user to select a flight by number
        int selection = 0;
        while (selection < 1 || selection > flights.size()) {
            System.out.print("Select a flight (1-" + flights.size() + ") or 0 to go back: ");
            try {
                selection = Integer.parseInt(scanner.nextLine().trim());
                if (selection == 0) {
                    // user chose to go back to search
                    System.out.println("Returning to search.");
                    return null;
                }
                if (selection < 1 || selection > flights.size()) {
                    System.out.println("Invalid selection. Please try again.");
                }
            } catch (NumberFormatException e) {
                // handle non-numeric input gracefully
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // return the flight the user selected
        Flight selectedFlight = flights.get(selection - 1);
        System.out.println("\nYou selected: " + selectedFlight.getFlightDetails());
        return selectedFlight;
    }
}
