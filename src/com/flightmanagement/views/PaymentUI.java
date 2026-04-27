/**
 * PaymentUI.java
   * Displays the payment interface for the Flight Management System.
      * Handles user input for selecting a payment method and entering
      * payment details. Passes all payment information to the
      * PaymentController for processing.
      *
      * Author: Edwin Olvera
      * Date: 04/27/2026
      * Course: CS 3321 - Software Engineering
      */

package com.flightmanagement.views;

import com.flightmanagement.controllers.PaymentController;
import com.flightmanagement.models.Booking;
import java.util.Scanner;

public class PaymentUI {
      private PaymentController paymentController;
      private Scanner scanner;

    public PaymentUI(PaymentController paymentController) {
              this.paymentController = paymentController;
              this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the payment form for the confirmed booking.
       * Prompts the customer to select a payment method and
       * enter their payment details.
       * Returns true if payment was successful.
       */
    public boolean display(Booking booking) {
              if (booking == null) {
                            System.out.println("No booking found to process payment.");
                            return false;
              }

          System.out.println("\n========== PAYMENT ==========");
              System.out.println("Booking Reference : " + booking.getBookingReference());
              System.out.println("Total Amount      : $" + booking.getTotalPrice());
              System.out.println("==============================\n");

          // display payment method options
          System.out.println("Select Payment Method:");
              System.out.println("1. Credit Card");
              System.out.println("2. Debit Card");
              System.out.println("3. PayPal");
              System.out.print("Select an option: ");
              String choice = scanner.nextLine().trim();

          String paymentMethod = "";
              // map user selection to payment method string
          switch (choice) {
            case "1":
                              paymentMethod = "CREDIT_CARD";
                              break;
            case "2":
                              paymentMethod = "DEBIT_CARD";
                              break;
            case "3":
                              paymentMethod = "PAYPAL";
                              break;
            default:
                              System.out.println("Invalid option. Defaulting to Credit Card.");
                              paymentMethod = "CREDIT_CARD";
                              break;
          }

          // collect card details for card payments
          String cardNumber = "";
              if (paymentMethod.equals("CREDIT_CARD") || paymentMethod.equals("DEBIT_CARD")) {
                            cardNumber = collectCardDetails();
                            if (cardNumber == null) {
                                              System.out.println("Payment cancelled.");
                                              return false;
                            }
              } else {
                            // for PayPal just collect the email address
                  System.out.print("Enter PayPal Email: ");
                            String paypalEmail = scanner.nextLine().trim();
                            System.out.println("PayPal account: " + paypalEmail + " selected.");
                            cardNumber = "PAYPAL";
              }

          // process the payment through the controller
          boolean success = paymentController.processPayment(
                            booking.getBookingReference(),
                            paymentMethod,
                            cardNumber,
                            booking.getTotalPrice());

          if (success) {
                        paymentController.displayConfirmation(booking.getBookingReference());
          } else {
                        System.out.println("Payment failed. Please try again.");
          }

          return success;
    }

    /**
     * Collects and validates card details from the customer.
       * Keeps prompting until valid details are entered.
       * Returns the card number or null if user cancels.
       */
    private String collectCardDetails() {
              System.out.println("\n--- Card Details ---");
              String cardholderName = scanner.nextLine().trim();
              System.out.print("Cardholder Name: " + cardholderName);

          // keep asking until a valid 16 digit card number is entered
          String cardNumber = "";
              while (true) {
                            System.out.print("Card Number (16 digits): ");
                            cardNumber = scanner.nextLine().trim();
                            if (paymentController.validateCard(cardNumber)) {
                                              break;
                            }
              }

          // keep asking until a valid expiry date is entered
          while (true) {
                        System.out.print("Expiry Date (MM/YY): ");
                        String expiryDate = scanner.nextLine().trim();
                        if (paymentController.validateExpiryDate(expiryDate)) {
                                          break;
                        }
          }

          // keep asking until a valid CVV is entered
          while (true) {
                        System.out.print("CVV (3 digits): ");
                        String cvv = scanner.nextLine().trim();
                        if (paymentController.validateCVV(cvv)) {
                                          break;
                        }
          }

          System.out.print("Discount Coupon (optional, press Enter to skip): ");
              String coupon = scanner.nextLine().trim();
              if (!coupon.isEmpty()) {
                            // coupon handling can be expanded in future versions
                  System.out.println("Coupon " + coupon + " applied.");
              }

          return cardNumber;
    }
}
