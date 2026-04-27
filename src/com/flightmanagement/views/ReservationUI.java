/**
 * ReservationUI.java
   * Displays the reservation management interface for the Flight Management System.
      * Allows customers to view their booking history and cancel existing reservations.
      * Passes all reservation operations to the ReservationController for processing.
      *
      * Author: Edwin Olvera
      * Date: 04/27/2026
      * Course: CS 3321 - Software Engineering
      */

package com.flightmanagement.views;

import com.flightmanagement.controllers.ReservationController;
import java.util.Scanner;

public class ReservationUI {
      private ReservationController reservationController;
      private Scanner scanner;

    public ReservationUI(ReservationController reservationController) {
              this.reservationController = reservationController;
              this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the reservation management menu and handles
       * customer selection for viewing or cancelling reservations.
       */
    public void display() {
              boolean running = true;
              while (running) {
                            System.out.println("\n========== MY RESERVATIONS ==========");
                            System.out.println("1. View my reservations");
                            System.out.println("2. Cancel a reservation");
                            System.out.println("3. Back to main menu");
                            System.out.print("Select an option: ");
                            String choice = scanner.nextLine().trim();

                  switch (choice) {
                    case "1":
                                          // display all reservations for the logged in customer
                                    reservationController.viewCustomerReservations();
                                          break;
                    case "2":
                                          cancelReservation();
                                          break;
                    case "3":
                                          running = false;
                                          break;
                    default:
                                          System.out.println("Invalid option. Please try again.");
                                          break;
                  }
              }
    }

    /**
     * Prompts the customer to enter a reservation ID to cancel.
       * Passes the ID to the ReservationController for processing.
       */
    private void cancelReservation() {
              // first show all reservations so customer can see their IDs
          reservationController.viewCustomerReservations();

          System.out.print("\nEnter Reservation ID to cancel (or press Enter to go back): ");
              String reservationID = scanner.nextLine().trim();

          if (reservationID.isEmpty()) {
                        System.out.println("Cancellation cancelled.");
                        return;
          }

          // confirm before cancelling
          System.out.print("Are you sure you want to cancel reservation " + reservationID + "? (yes/no): ");
              String confirm = scanner.nextLine().trim().toLowerCase();

          if (confirm.equals("yes")) {
                        reservationController.cancelReservation(reservationID);
          } else {
                        System.out.println("Cancellation aborted.");
          }
    }
}
