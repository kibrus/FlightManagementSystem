/**
 * SearchController.java
 * Handles all flight search operations in the Flight Management System.
 * Responsible for validating IATA codes, searching for available flights,
 * and filtering/sorting results based on customer preferences.
 *
 * Author: Kibru Menore
 * Date: 04/23/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.controllers;

import com.flightmanagement.data.DataStore;
import com.flightmanagement.models.Airport;
import com.flightmanagement.models.Flight;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SearchController {

    private String searchCriteria;
    private List<Flight> searchResults;

    // Default constructor initializes empty search results
    public SearchController() {
        this.searchResults = new ArrayList<>();
    }

    /**
     * Searches for available flights between two airports on a given date.
     * Validates both IATA codes before searching the DataStore.
     * Returns a list of matching flights or empty list if none found.
     */
    public List<Flight> searchFlights(String departureCode, String arrivalCode,
                                      String date, int passengerCount) {
        searchResults = new ArrayList<>();

        // validate both airport codes before searching
        if (!validateLocation(departureCode)) {
            System.out.println("Invalid departure airport code: " + departureCode);
            return searchResults;
        }
        if (!validateLocation(arrivalCode)) {
            System.out.println("Invalid arrival airport code: " + arrivalCode);
            return searchResults;
        }

        // search DataStore for flights matching the route
        List<Flight> matches = DataStore.searchFlights(departureCode, arrivalCode);

        // filter out flights that don't have enough seats
        for (Flight flight : matches) {
            if (flight.isAvailable(passengerCount)) {
                searchResults.add(flight);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("No flights found from " + departureCode +
                               " to " + arrivalCode + " on " + date);
        } else {
            System.out.println(searchResults.size() + " flight(s) found.");
        }

        return searchResults;
    }

    /**
     * Validates that the given IATA code exists in the DataStore.
     * Returns true if the airport is found, false otherwise.
     */
    public boolean validateLocation(String iataCode) {
        Airport airport = DataStore.findAirportByCode(iataCode);
        return airport != null;
    }

    /**
     * Filters the current search results by airline type.
     * Type can be "LCC" for low cost or "FSA" for full service.
     */
    public List<Flight> filterByAirlineType(String airlineType) {
        List<Flight> filtered = new ArrayList<>();
        for (Flight flight : searchResults) {
            if (flight.getAirlineType().equalsIgnoreCase(airlineType)) {
                filtered.add(flight);
            }
        }
        return filtered;
    }

    /**
     * Filters the current search results by ticket class.
     * Class can be "Economy", "Business", or "First".
     */
    public List<Flight> filterByTicketClass(String ticketClass) {
        List<Flight> filtered = new ArrayList<>();
        for (Flight flight : searchResults) {
            if (flight.getTicketClass().equalsIgnoreCase(ticketClass)) {
                filtered.add(flight);
            }
        }
        return filtered;
    }

    /**
     * Sorts the current search results by price from lowest to highest.
     * Returns the sorted list of flights.
     */
    public List<Flight> sortByPrice() {
        List<Flight> sorted = new ArrayList<>(searchResults);
        sorted.sort(Comparator.comparingDouble(Flight::getFare));
        System.out.println("Flights sorted by price (lowest to highest).");
        return sorted;
    }

    /**
     * Sorts the current search results by duration from shortest to longest.
     * Returns the sorted list of flights.
     */
    public List<Flight> sortByDuration() {
        List<Flight> sorted = new ArrayList<>(searchResults);
        sorted.sort(Comparator.comparing(Flight::getDuration));
        System.out.println("Flights sorted by duration (shortest to longest).");
        return sorted;
    }

    /**
     * Displays all flights in the given list in a formatted layout.
     * Used to print search results to the console.
     */
    public void displayFlights(List<Flight> flights) {
        if (flights.isEmpty()) {
            System.out.println("No flights to display.");
            return;
        }
        System.out.println("\n========== FLIGHT RESULTS ==========");
        for (int i = 0; i < flights.size(); i++) {
            System.out.println((i + 1) + ". " + flights.get(i).getFlightDetails());
        }
        System.out.println("=====================================\n");
    }

    // Getters
    public String getSearchCriteria() { return searchCriteria; }
    public List<Flight> getSearchResults() { return searchResults; }

    // Setters
    public void setSearchCriteria(String searchCriteria) { this.searchCriteria = searchCriteria; }
}