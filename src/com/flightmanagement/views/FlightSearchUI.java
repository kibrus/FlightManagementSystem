/**
 * FlightSearchUI.java
   * Displays the flight search interface for the Flight Management System.
      * Handles user input for searching available flights by departure city,
      * destination city, date, and number of passengers.
      * Passes search criteria to the SearchController for processing.
      *
      * Author: Edwin Olvera
      * Date: 04/27/2026
      * Course: CS 3321 - Software Engineering
      */

package com.flightmanagement.views;

import com.flightmanagement.controllers.SearchController;
import com.flightmanagement.models.Flight;
import java.util.List;
import java.util.Scanner;

public class FlightSearchUI {
      private SearchController searchController;
      private Scanner scanner;

    public FlightSearchUI(SearchController searchController) {
              this.searchController = searchController;
              this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the flight search form and collects departure,
       * destination, date, and passenger count from the user.
       * Returns the list of matching flights found.
       */
    public List<Flight> display() {
              System.out.println("\n========== SEARCH FLIGHTS ==========");
              System.out.print("Departure Airport Code (e.g. IAH): ");
              String departureCode = scanner.nextLine().trim().toUpperCase();

          System.out.print("Arrival Airport Code (e.g. JFK): ");
              String arrivalCode = scanner.nextLine().trim().toUpperCase();

          System.out.print("Departure Date (YYYY-MM-DD): ");
              String date = scanner.nextLine().trim();

          // keep asking until a valid passenger count is entered
          int passengerCount = 0;
              while (passengerCount < 1) {
                            System.out.print("Number of Passengers: ");
                            try {
                                              passengerCount = Integer.parseInt(scanner.nextLine().trim());
                                              if (passengerCount < 1) {
                                                                    System.out.println("Please enter at least 1 passenger.");
                                              }
                            } catch (NumberFormatException e) {
                                              // handle non-numeric input gracefully
                                System.out.println("Invalid input. Please enter a number.");
                            }
              }

          // pass search criteria to the controller
          List<Flight> results = searchController.searchFlights(
                            departureCode, arrivalCode, date, passengerCount);

          // display results if any were found
          if (!results.isEmpty()) {
                        searchController.displayFlights(results);
                        displayFilterOptions(results);
          }

          return results;
    }

    /**
     * Displays filter and sort options after search results are shown.
       * Allows the user to refine results by price, duration,
       * airline type, or ticket class.
       */
    private void displayFilterOptions(List<Flight> results) {
              System.out.println("\n========== FILTER / SORT OPTIONS ==========");
              System.out.println("1. Sort by price");
              System.out.println("2. Sort by duration");
              System.out.println("3. Filter by airline type (LCC or FSA)");
              System.out.println("4. Filter by ticket class");
              System.out.println("5. Continue without filtering");
              System.out.print("Select an option: ");
              String choice = scanner.nextLine().trim();

          switch (choice) {
            case "1":
                              // sort results by price and display
                      List<Flight> byPrice = searchController.sortByPrice();
                              searchController.displayFlights(byPrice);
                              break;
            case "2":
                              // sort results by duration and display
                      List<Flight> byDuration = searchController.sortByDuration();
                              searchController.displayFlights(byDuration);
                              break;
            case "3":
                              System.out.print("Enter airline type (LCC or FSA): ");
                              String airlineType = scanner.nextLine().trim().toUpperCase();
                              List<Flight> byType = searchController.filterByAirlineType(airlineType);
                              searchController.displayFlights(byType);
                              break;
            case "4":
                              System.out.print("Enter ticket class (Economy, Business, First): ");
                              String ticketClass = scanner.nextLine().trim();
                              List<Flight> byClass = searchController.filterByTicketClass(ticketClass);
                              searchController.displayFlights(byClass);
                              break;
            case "5":
                              // user chose to skip filtering
                      System.out.println("Continuing with original results.");
                              break;
            default:
                              System.out.println("Invalid option. Continuing with original results.");
                              break;
          }
    }
}
