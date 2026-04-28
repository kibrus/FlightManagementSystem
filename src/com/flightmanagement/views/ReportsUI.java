/**
 * ReportsUI.java
 * Displays the reports interface for the Flight Management System.
 * Allows system administrators to generate, view, and export
 * different types of reports including sales, revenue, and bookings.
 * Only accessible by authenticated system administrators.
 *
 * Author: Muhammad Moosa Khalid
 * Date: 04/25/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.views;

import com.flightmanagement.controllers.ReportController;

import java.util.Scanner;

public class ReportsUI {

    private ReportController reportController;
    private Scanner scanner;

    public ReportsUI(ReportController reportController) {
        this.reportController = reportController;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the reports menu and handles administrator
     * selection for generating and viewing reports.
     */
    public void display() {
        boolean running = true;

        while (running) {
            System.out.println("\n========== REPORTS MENU ==========");
            System.out.println("1. Generate sales report");
            System.out.println("2. Generate revenue report");
            System.out.println("3. Generate booking report");
            System.out.println("4. View all generated reports");
            System.out.println("5. Export latest report");
            System.out.println("6. Back to admin menu");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // generate and display a sales report
                    reportController.generateSalesReport();
                    askToExport();
                    break;

                case "2":
                    // generate and display a revenue report
                    reportController.generateRevenueReport();
                    askToExport();
                    break;

                case "3":
                    // generate and display a booking report
                    reportController.generateBookingReport();
                    askToExport();
                    break;

                case "4":
                    reportController.displayAllReports();
                    break;

                case "5":
                    reportController.exportReport();
                    break;

                case "6":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    /**
     * Asks the administrator if they want to export the
     * most recently generated report.
     */
    private void askToExport() {
        System.out.print("\nWould you like to export this report? (yes/no): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("yes")) {
            reportController.exportReport();
        }
    }
}
