/**
 * AncillaryService.java
 * Represents an optional add-on service in the Flight Management System.
 * Examples include baggage, seat selection, and priority boarding.
 * These services can be added to a booking at an additional cost.
 *
 * Author: Kibru Menore
 * Date: 04/22/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.models;

public class AncillaryService {

    private String serviceID;
    private String serviceName;        // e.g. "Extra Baggage", "Seat Selection"
    private String serviceDescription;
    private double servicePrice;

    // Default constructor
    public AncillaryService() {}

    // Parameterized constructor to create a fully initialized service
    public AncillaryService(String serviceID, String serviceName,
                            String serviceDescription, double servicePrice) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.servicePrice = servicePrice;
    }

    /**
     * Returns the price of this service.
     * Used when calculating the total booking price.
     */
    public double getPrice() {
        return servicePrice;
    }

    /**
     * Adds this service to a booking by its booking reference.
     * The actual price update is handled in Booking.addService().
     */
    public void addToBooking(String bookingReference) {
        System.out.println("Service '" + serviceName + "' added to booking " +
                           bookingReference + " for $" + servicePrice);
    }

    /**
     * Returns a formatted summary of this service.
     * Used when displaying available services to the customer.
     */
    public String getServiceInfo() {
        return "Service ID: " + serviceID +
               " | Name: " + serviceName +
               " | Description: " + serviceDescription +
               " | Price: $" + servicePrice;
    }

    // Getters
    public String getServiceID() { return serviceID; }
    public String getServiceName() { return serviceName; }
    public String getServiceDescription() { return serviceDescription; }
    public double getServicePrice() { return servicePrice; }

    // Setters
    public void setServiceID(String serviceID) { this.serviceID = serviceID; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public void setServiceDescription(String serviceDescription) { this.serviceDescription = serviceDescription; }
    public void setServicePrice(double servicePrice) { this.servicePrice = servicePrice; }
}