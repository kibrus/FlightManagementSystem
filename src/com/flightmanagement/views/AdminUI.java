/**
 * AdminUI.java
 * Displays the administrator interface for the Flight Management System.
 * Allows system administrators to manage user accounts and generate
 * reports. Only accessible by authenticated system administrators.
 *
 * Author: Adrian Salgado
 * Date: 04/27/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.views;

import com.flightmanagement.controllers.ReportController;
import com.flightmanagement.controllers.UserController;

import java.util.Scanner;

public class AdminUI {

    private UserController userController;
    private ReportController reportController;
    private Scanner scanner;

    public AdminUI(UserController userController, ReportController reportController) {
        this.userController = userController;
        this.reportController = reportController;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the admin main menu and handles administrator
     * selection for managing users and generating reports.
     */
    public void display() {
        boolean running = true;

        while (running) {
            System.out.println("\n========== ADMIN MENU ==========");
            System.out.println("1. View all users");
            System.out.println("2. View users by role");
            System.out.println("3. Activate user account");
            System.out.println("4. Suspend user account");
            System.out.println("5. Delete user account");
            System.out.println("6. Generate sales report");
            System.out.println("7. Generate revenue report");
            System.out.println("8. Generate booking report");
            System.out.println("9. View all reports");
            System.out.println("10. Export latest report");
            System.out.println("11. Logout");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    userController.displayAllUsers();
                    break;

                case "2":
                    viewUsersByRole();
                    break;

                case "3":
                    manageUserAccount("ACTIVATE");
                    break;

                case "4":
                    manageUserAccount("SUSPEND");
                    break;

                case "5":
                    manageUserAccount("DELETE");
                    break;

                case "6":
                    reportController.generateSalesReport();
                    break;

                case "7":
                    reportController.generateRevenueReport();
                    break;

                case "8":
                    reportController.generateBookingReport();
                    break;

                case "9":
                    reportController.displayAllReports();
                    break;

                case "10":
                    reportController.exportReport();
                    break;

                case "11":
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
     * Prompts the administrator to select a role and displays
     * all users with that role.
     */
    private void viewUsersByRole() {
        System.out.println("\nSelect role to view:");
        System.out.println("1. Customers");
        System.out.println("2. Airline Staff");
        System.out.println("3. Administrators");
        System.out.print("Select an option: ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                userController.displayUsersByRole("CUSTOMER");
                break;
            case "2":
                userController.displayUsersByRole("STAFF");
                break;
            case "3":
                userController.displayUsersByRole("ADMIN");
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }

    /**
     * Prompts the administrator to enter a user ID and performs
     * the requested account action (activate, suspend, or delete).
     */
    private void manageUserAccount(String action) {
        // show all users first so admin can see user IDs
        userController.displayAllUsers();

        System.out.print("\nEnter User ID to " + action.toLowerCase() + ": ");
        String userID = scanner.nextLine().trim();

        if (userID.isEmpty()) {
            System.out.println("No user ID entered. Returning to menu.");
            return;
        }

        // confirm before performing the action
        System.out.print("Are you sure you want to " + action.toLowerCase() +
                         " user " + userID + "? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (!confirm.equals("yes")) {
            System.out.println("Action cancelled.");
            return;
        }

        // perform the selected action through the user controller
        switch (action) {
            case "ACTIVATE":
                userController.activateUser(userID);
                break;
            case "SUSPEND":
                userController.suspendUser(userID);
                break;
            case "DELETE":
                userController.deleteUser(userID);
                break;
        }
    }
}
