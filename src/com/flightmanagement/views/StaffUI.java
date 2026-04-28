/**
 * StaffUI.java
 * Displays the airline staff interface for the Flight Management System.
 * Allows airline staff to manage flight listings including adding,
 * updating, and removing flights from the system.
 * Only accessible by authenticated airline staff members.
 *
 * Author: Muhammad Moosa Khalid
 * Date: 04/25/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.views;

import com.flightmanagement.controllers.FlightController;

import java.util.Scanner;

public class StaffUI {

    private FlightController flightController;
    private Scanner scanner;

    public StaffUI(FlightController flightController) {
        this.flightController = flightController;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the staff main menu and handles staff selection
     * for managing flight listings.
     */
    public void display() {
        boolean running = true;

        while (running) {
            System.out.println("\n========== STAFF MENU ==========");
            System.out.println("1. View all flights");
            System.out.println("2. Add new flight");
            System.out.println("3. Update flight fare");
            System.out.println("4. Update flight capacity");
            System.out.println("5. Remove flight");
            System.out.println("6. Logout");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    flightController.displayAllFlights();
                    break;

                case "2":
                    addFlight();
                    break;

                case "3":
                    updateFlightFare();
                    break;

                case "4":
                    updateFlightCapacity();
                    break;

                case "5":
                    removeFlight();
                    break;

                case "6":
                    running = false;
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    /**
     * Collects all required details for a new flight from the
     * staff member and passes them to the FlightController.
     */
    private void addFlight() {
        System.out.println("\n--- Add New Flight ---");

        System.out.print("Flight ID: ");
        String flightID = scanner.nextLine().trim();

        System.out.print("Airline Name: ");
        String airlineName = scanner.nextLine().trim();

        System.out.print("Departure Date and Time (YYYY-MM-DD HH:MM): ");
        String departureDateTime = scanner.nextLine().trim();

        System.out.print("Arrival Date and Time (YYYY-MM-DD HH:MM): ");
        String arrivalDateTime = scanner.nextLine().trim();

        System.out.print("Duration (e.g. 2h 30m): ");
        String duration = scanner.nextLine().trim();

        // collect number of stops with input validation
        int numberOfStops = 0;
        while (true) {
            System.out.print("Number of Stops: ");
            try {
                numberOfStops = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        System.out.print("Ticket Class (Economy/Business/First): ");
        String ticketClass = scanner.nextLine().trim();

        System.out.print("Ticket Class Code (B/C/F): ");
        String ticketClassCode = scanner.nextLine().trim().toUpperCase();

        // collect seat capacity with input validation
        int seatCapacity = 0;
        while (true) {
            System.out.print("Seat Capacity: ");
            try {
                seatCapacity = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // collect fare with input validation
        double fare = 0;
        while (true) {
            System.out.print("Fare ($): ");
            try {
                fare = Double.parseDouble(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
            }
        }

        System.out.print("Airline Type (LCC/FSA): ");
        String airlineType = scanner.nextLine().trim().toUpperCase();

        System.out.print("Departure Airport Code (e.g. IAH): ");
        String departureCode = scanner.nextLine().trim().toUpperCase();

        System.out.print("Arrival Airport Code (e.g. JFK): ");
        String arrivalCode = scanner.nextLine().trim().toUpperCase();

        // pass all details to the flight controller
        flightController.addFlight(flightID, airlineName, departureDateTime,
                                   arrivalDateTime, duration, numberOfStops,
                                   ticketClass, ticketClassCode, seatCapacity,
                                   fare, airlineType, departureCode, arrivalCode);
    }

    /**
     * Prompts staff to enter a flight ID and new fare
     * then passes the update to the FlightController.
     */
    private void updateFlightFare() {
        System.out.println("\n--- Update Flight Fare ---");

        System.out.print("Enter Flight ID: ");
        String flightID = scanner.nextLine().trim();

        double newFare = 0;
        while (true) {
            System.out.print("Enter New Fare ($): ");
            try {
                newFare = Double.parseDouble(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
            }
        }

        flightController.updateFlightFare(flightID, newFare);
    }

    /**
     * Prompts staff to enter a flight ID and new seat capacity
     * then passes the update to the FlightController.
     */
    private void updateFlightCapacity() {
        System.out.println("\n--- Update Flight Capacity ---");

        System.out.print("Enter Flight ID: ");
        String flightID = scanner.nextLine().trim();

        int newCapacity = 0;
        while (true) {
            System.out.print("Enter New Seat Capacity: ");
            try {
                newCapacity = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        flightController.updateFlightCapacity(flightID, newCapacity);
    }

    /**
     * Prompts staff to enter a flight ID to remove
     * then passes the request to the FlightController.
     */
    private void removeFlight() {
        System.out.println("\n--- Remove Flight ---");

        // show all flights first so staff can see available flight IDs
        flightController.displayAllFlights();

        System.out.print("Enter Flight ID to remove: ");
        String flightID = scanner.nextLine().trim();

        // confirm before removing
        System.out.print("Are you sure you want to remove flight " +
                         flightID + "? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("yes")) {
            flightController.removeFlight(flightID);
        } else {
            System.out.println("Removal cancelled.");
        }
    }
}
