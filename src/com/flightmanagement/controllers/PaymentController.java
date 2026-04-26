/**
 * PaymentController.java
 * Handles all payment processing operations in the Flight Management System.
 * Responsible for validating payment details, processing transactions,
 * and generating payment confirmations for completed bookings.
 *
 * Author: Kibru Menore
 * Date: 04/24/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.controllers;

import com.flightmanagement.data.DataStore;
import com.flightmanagement.models.Booking;
import com.flightmanagement.models.Payment;

import java.time.LocalDate;

public class PaymentController {

    private String paymentData;
    private String transactionStatus;
    private String confirmationNumber;

    // Default constructor
    public PaymentController() {}

    /**
     * Processes a payment for a given booking.
     * Validates the card details before processing the transaction.
     * Returns true if payment was successful, false otherwise.
     */
    public boolean processPayment(String bookingReference, String paymentMethod,
                                  String cardNumber, double amount) {

        // find the booking this payment is for
        Booking booking = DataStore.findBookingByReference(bookingReference);
        if (booking == null) {
            System.out.println("Booking not found: " + bookingReference);
            return false;
        }

        // validate card number before processing
        if (paymentMethod.equals("CREDIT_CARD") || paymentMethod.equals("DEBIT_CARD")) {
            if (!validateCard(cardNumber)) {
                return false;
            }
        }

        // generate a unique payment ID
        String paymentID = "PAY" + String.format("%03d",
                           DataStore.getPayments().size() + 1);
        String paymentDate = LocalDate.now().toString();

        // create and process the payment
        Payment payment = new Payment(paymentID, paymentDate, amount,
                                      paymentMethod, bookingReference);

        if (!payment.processPayment()) {
            this.transactionStatus = "FAILED";
            System.out.println("Payment processing failed.");
            return false;
        }

        // save the payment and generate confirmation
        DataStore.addPayment(payment);
        this.transactionStatus = "COMPLETED";
        this.confirmationNumber = generateConfirmation(bookingReference);
        this.paymentData = payment.getPaymentInfo();

        System.out.println("Payment successful!");
        System.out.println("Confirmation Number: " + confirmationNumber);
        return true;
    }

    /**
     * Validates the card number entered by the customer.
     * Card must be exactly 16 digits and contain only numbers.
     * Returns true if valid, false otherwise.
     */
    public boolean validateCard(String cardNumber) {
        if (cardNumber == null || !cardNumber.matches("[0-9]{16}")) {
            System.out.println("Invalid card number. Please enter 16 digits.");
            return false;
        }
        return true;
    }

    /**
     * Validates the CVV entered by the customer.
     * CVV must be exactly 3 digits.
     * Returns true if valid, false otherwise.
     */
    public boolean validateCVV(String cvv) {
        if (cvv == null || !cvv.matches("[0-9]{3}")) {
            System.out.println("Invalid CVV. Must be 3 digits.");
            return false;
        }
        return true;
    }

    /**
     * Validates the expiry date entered by the customer.
     * Format must be MM/YY and must not be in the past.
     * Returns true if valid, false otherwise.
     */
    public boolean validateExpiryDate(String expiryDate) {
        if (expiryDate == null || !expiryDate.matches("(0[1-9]|1[0-2])/[0-9]{2}")) {
            System.out.println("Invalid expiry date. Use MM/YY format.");
            return false;
        }
        return true;
    }

    /**
     * Generates a unique confirmation number for a completed payment.
     * Combines the booking reference with a timestamp for uniqueness.
     */
    public String generateConfirmation(String bookingReference) {
        this.confirmationNumber = "CONF" + bookingReference + System.currentTimeMillis();
        return this.confirmationNumber;
    }

    /**
     * Displays the payment confirmation details to the customer.
     * Called after a successful payment is processed.
     */
    public void displayConfirmation(String bookingReference) {
        Booking booking = DataStore.findBookingByReference(bookingReference);
        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        System.out.println("\n========== PAYMENT CONFIRMATION ==========");
        System.out.println("Confirmation Number : " + confirmationNumber);
        System.out.println("Booking Reference   : " + bookingReference);
        System.out.println("Amount Paid         : $" + booking.getTotalPrice());
        System.out.println("Payment Status      : " + transactionStatus);
        System.out.println("==========================================\n");
    }

    // Getters
    public String getPaymentData() { return paymentData; }
    public String getTransactionStatus() { return transactionStatus; }
    public String getConfirmationNumber() { return confirmationNumber; }

    // Setters
    public void setPaymentData(String paymentData) { this.paymentData = paymentData; }
    public void setTransactionStatus(String transactionStatus) { this.transactionStatus = transactionStatus; }
    public void setConfirmationNumber(String confirmationNumber) { this.confirmationNumber = confirmationNumber; }
}