/**
 * Payment.java
 * Represents a payment transaction in the Flight Management System.
 * Handles payment processing and validation for flight bookings.
 * Supports multiple payment methods including credit/debit cards
 * and digital payment options.
 *
 * Author: Kibru Menore
 * Date: 04/22/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.models;

public class Payment {

    private String paymentID;
    private String paymentDate;
    private double paymentAmount;
    private String paymentMethod;  // "CREDIT_CARD", "DEBIT_CARD", "PAYPAL"
    private String paymentStatus;  // "PENDING", "COMPLETED", "FAILED"
    private String transactionReference;
    private String bookingReference; // links this payment to a booking

    // Default constructor sets payment status to PENDING
    public Payment() {
        this.paymentStatus = "PENDING";
    }

    // Parameterized constructor for a fully initialized payment
    public Payment(String paymentID, String paymentDate, double paymentAmount,
                   String paymentMethod, String bookingReference) {
        this.paymentID = paymentID;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = "PENDING";
        this.bookingReference = bookingReference;
    }

    /**
     * Processes the payment by validating the amount and updating
     * the payment status to COMPLETED.
     * Returns true if payment was successful, false otherwise.
     */
    public boolean processPayment() {
        // check that payment amount is valid before processing
        if (paymentAmount <= 0) {
            System.out.println("Payment failed: invalid amount.");
            this.paymentStatus = "FAILED";
            return false;
        }
        // generate a transaction reference and mark as completed
        this.transactionReference = "TXN" + paymentID + System.currentTimeMillis();
        this.paymentStatus = "COMPLETED";
        System.out.println("Payment of $" + paymentAmount + " processed successfully.");
        System.out.println("Transaction Reference: " + transactionReference);
        return true;
    }

    /**
     * Validates the card number by checking it is exactly 16 digits.
     * Returns true if valid, false otherwise.
     */
    public boolean validateCard(String cardNumber) {
        // card number must be exactly 16 digits and contain only numbers
        if (cardNumber == null || !cardNumber.matches("[0-9]{16}")) {
            System.out.println("Invalid card number. Must be 16 digits.");
            return false;
        }
        System.out.println("Card validated successfully.");
        return true;
    }

    /**
     * Returns a formatted summary of this payment.
     * Used when displaying payment confirmation to the customer.
     */
    public String getPaymentInfo() {
        return "Payment ID: " + paymentID +
               " | Date: " + paymentDate +
               " | Amount: $" + paymentAmount +
               " | Method: " + paymentMethod +
               " | Status: " + paymentStatus +
               " | Transaction Ref: " + transactionReference;
    }

    // Getters
    public String getPaymentID() { return paymentID; }
    public String getPaymentDate() { return paymentDate; }
    public double getPaymentAmount() { return paymentAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getPaymentStatus() { return paymentStatus; }
    public String getTransactionReference() { return transactionReference; }
    public String getBookingReference() { return bookingReference; }

    // Setters
    public void setPaymentID(String paymentID) { this.paymentID = paymentID; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }
    public void setPaymentAmount(double paymentAmount) { this.paymentAmount = paymentAmount; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public void setTransactionReference(String transactionReference) { this.transactionReference = transactionReference; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
}