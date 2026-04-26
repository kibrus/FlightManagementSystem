/**
 * Flight.java
 * Represents a flight listing in the Flight Management System.
 * Stores all flight details including route, schedule, pricing,
 * and seat availability.
 *
 * Author: Kibru Menore
 * Date: 04/21/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.models;

public class Flight {

    private String flightID;
    private String airlineName;
    private String departureDateTime;
    private String arrivalDateTime;
    private String duration;
    private int numberOfStops;
    private String ticketClass;      // "Economy", "Business", "First"
    private String ticketClassCode;  // one letter code: "B", "C", "F"
    private int seatCapacity;
    private int availableSeats;
    private double fare;
    private String airlineType;           // "LCC" (low cost) or "FSA" (full service)
    private String departureAirportCode;  // IATA code for departure airport
    private String arrivalAirportCode;    // IATA code for arrival airport

    // Default constructor
    public Flight() {}

    // Parameterized constructor to create a fully initialized flight
    public Flight(String flightID, String airlineName, String departureDateTime,
                  String arrivalDateTime, String duration, int numberOfStops,
                  String ticketClass, String ticketClassCode, int seatCapacity,
                  double fare, String airlineType, String departureAirportCode,
                  String arrivalAirportCode) {
        this.flightID = flightID;
        this.airlineName = airlineName;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.duration = duration;
        this.numberOfStops = numberOfStops;
        this.ticketClass = ticketClass;
        this.ticketClassCode = ticketClassCode;
        this.seatCapacity = seatCapacity;
        this.availableSeats = seatCapacity; // all seats available when flight is created
        this.fare = fare;
        this.airlineType = airlineType;
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirportCode = arrivalAirportCode;
    }

    /**
     * Returns a formatted string of all flight details.
     * Used to display flight information in search results.
     */
    public String getFlightDetails() {
        return "Flight ID: " + flightID +
               " | Airline: " + airlineName +
               " | From: " + departureAirportCode +
               " To: " + arrivalAirportCode +
               " | Departure: " + departureDateTime +
               " | Arrival: " + arrivalDateTime +
               " | Duration: " + duration +
               " | Stops: " + numberOfStops +
               " | Class: " + ticketClass + " (" + ticketClassCode + ")" +
               " | Fare: $" + fare +
               " | Seats Available: " + availableSeats +
               " | Type: " + airlineType;
    }

    /**
     * Reduces available seats by the number of passengers booking.
     * Called when a booking is confirmed.
     */
    public void updateCapacity(int seatsBooked) {
        if (seatsBooked > availableSeats) {
            System.out.println("Not enough seats available on flight " + flightID);
            return;
        }
        this.availableSeats -= seatsBooked;
        System.out.println(seatsBooked + " seat(s) booked on flight " + flightID +
                           ". Remaining seats: " + availableSeats);
    }

    /**
     * Checks if the flight has enough seats for the requested number.
     * Returns true if seats are available, false otherwise.
     */
    public boolean isAvailable(int requestedSeats) {
        return availableSeats >= requestedSeats;
    }

    // Getters
    public String getFlightID() { return flightID; }
    public String getAirlineName() { return airlineName; }
    public String getDepartureDateTime() { return departureDateTime; }
    public String getArrivalDateTime() { return arrivalDateTime; }
    public String getDuration() { return duration; }
    public int getNumberOfStops() { return numberOfStops; }
    public String getTicketClass() { return ticketClass; }
    public String getTicketClassCode() { return ticketClassCode; }
    public int getSeatCapacity() { return seatCapacity; }
    public int getAvailableSeats() { return availableSeats; }
    public double getFare() { return fare; }
    public String getAirlineType() { return airlineType; }
    public String getDepartureAirportCode() { return departureAirportCode; }
    public String getArrivalAirportCode() { return arrivalAirportCode; }

    // Setters
    public void setFlightID(String flightID) { this.flightID = flightID; }
    public void setAirlineName(String airlineName) { this.airlineName = airlineName; }
    public void setDepartureDateTime(String departureDateTime) { this.departureDateTime = departureDateTime; }
    public void setArrivalDateTime(String arrivalDateTime) { this.arrivalDateTime = arrivalDateTime; }
    public void setDuration(String duration) { this.duration = duration; }
    public void setNumberOfStops(int numberOfStops) { this.numberOfStops = numberOfStops; }
    public void setTicketClass(String ticketClass) { this.ticketClass = ticketClass; }
    public void setTicketClassCode(String ticketClassCode) { this.ticketClassCode = ticketClassCode; }
    public void setSeatCapacity(int seatCapacity) { this.seatCapacity = seatCapacity; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
    public void setFare(double fare) { this.fare = fare; }
    public void setAirlineType(String airlineType) { this.airlineType = airlineType; }
    public void setDepartureAirportCode(String departureAirportCode) { this.departureAirportCode = departureAirportCode; }
    public void setArrivalAirportCode(String arrivalAirportCode) { this.arrivalAirportCode = arrivalAirportCode; }
}