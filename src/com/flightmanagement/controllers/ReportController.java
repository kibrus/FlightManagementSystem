/**
 * ReportController.java
 * Handles all report generation operations in the Flight Management System.
 * Responsible for compiling sales, revenue, and booking data into
 * formatted reports for system administrators.
 * Only accessible by authenticated system administrators.
 *
 * Author: Adrian Salgado
 * Date: 04/27/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.controllers;

import com.flightmanagement.data.DataStore;
import com.flightmanagement.models.Booking;
import com.flightmanagement.models.Payment;
import com.flightmanagement.models.Report;

import java.time.LocalDate;
import java.util.List;

public class ReportController {

    private String selectedReportType;
    private Report currentReport;
    private String generationStatus;

    // Default constructor
    public ReportController() {}

    /**
     * Generates a sales report by compiling all booking and
     * payment data currently stored in the DataStore.
     * Returns the generated report.
     */
    public Report generateSalesReport() {
        List<Booking> bookings = DataStore.getBookings();
        List<Payment> payments = DataStore.getPayments();

        // calculate total sales and revenue from all payments
        double totalSales = 0;
        double totalRevenue = 0;
        int bookingCount = 0;

        for (Payment payment : payments) {
            // only count completed payments
            if (payment.getPaymentStatus().equals("COMPLETED")) {
                totalSales += payment.getPaymentAmount();
                totalRevenue += payment.getPaymentAmount();
            }
        }

        // count only confirmed bookings
        for (Booking booking : bookings) {
            if (booking.getBookingStatus().equals("CONFIRMED")) {
                bookingCount++;
            }
        }

        // generate a unique report ID
        String reportID = "RPT" + String.format("%03d",
                          DataStore.getReports().size() + 1);
        String generatedDate = LocalDate.now().toString();

        // create and save the report
        currentReport = new Report(reportID, "SALES", generatedDate,
                                   totalSales, totalRevenue, bookingCount);
        DataStore.addReport(currentReport);
        this.selectedReportType = "SALES";
        this.generationStatus = "COMPLETED";

        currentReport.generate();
        return currentReport;
    }

    /**
     * Generates a revenue report showing total income
     * broken down by payment method.
     * Returns the generated report.
     */
    public Report generateRevenueReport() {
        List<Payment> payments = DataStore.getPayments();

        double totalRevenue = 0;
        double creditCardRevenue = 0;
        double debitCardRevenue = 0;
        double paypalRevenue = 0;

        // break down revenue by payment method
        for (Payment payment : payments) {
            if (payment.getPaymentStatus().equals("COMPLETED")) {
                totalRevenue += payment.getPaymentAmount();

                if (payment.getPaymentMethod().equals("CREDIT_CARD")) {
                    creditCardRevenue += payment.getPaymentAmount();
                } else if (payment.getPaymentMethod().equals("DEBIT_CARD")) {
                    debitCardRevenue += payment.getPaymentAmount();
                } else if (payment.getPaymentMethod().equals("PAYPAL")) {
                    paypalRevenue += payment.getPaymentAmount();
                }
            }
        }

        String reportID = "RPT" + String.format("%03d",
                          DataStore.getReports().size() + 1);
        String generatedDate = LocalDate.now().toString();

        currentReport = new Report(reportID, "REVENUE", generatedDate,
                                   totalRevenue, totalRevenue,
                                   DataStore.getBookings().size());
        DataStore.addReport(currentReport);
        this.selectedReportType = "REVENUE";
        this.generationStatus = "COMPLETED";

        // display the detailed revenue breakdown
        System.out.println("\n========== REVENUE REPORT ==========");
        System.out.println("Total Revenue    : $" + totalRevenue);
        System.out.println("Credit Card      : $" + creditCardRevenue);
        System.out.println("Debit Card       : $" + debitCardRevenue);
        System.out.println("PayPal           : $" + paypalRevenue);
        System.out.println("=====================================\n");

        return currentReport;
    }

    /**
     * Generates a booking report showing total bookings,
     * confirmed bookings, and cancelled bookings.
     * Returns the generated report.
     */
    public Report generateBookingReport() {
        List<Booking> bookings = DataStore.getBookings();

        int totalBookings = bookings.size();
        int confirmedBookings = 0;
        int cancelledBookings = 0;
        int pendingBookings = 0;
        double totalRevenue = 0;

        for (Booking booking : bookings) {
            switch (booking.getBookingStatus()) {
                case "CONFIRMED":
                    confirmedBookings++;
                    totalRevenue += booking.getTotalPrice();
                    break;
                case "CANCELLED":
                    cancelledBookings++;
                    break;
                case "PENDING":
                    pendingBookings++;
                    break;
            }
        }

        String reportID = "RPT" + String.format("%03d",
                          DataStore.getReports().size() + 1);
        String generatedDate = LocalDate.now().toString();

        currentReport = new Report(reportID, "BOOKING", generatedDate,
                                   totalRevenue, totalRevenue, totalBookings);
        DataStore.addReport(currentReport);
        this.selectedReportType = "BOOKING";
        this.generationStatus = "COMPLETED";

        // display detailed booking breakdown
        System.out.println("\n========== BOOKING REPORT ==========");
        System.out.println("Total Bookings    : " + totalBookings);
        System.out.println("Confirmed         : " + confirmedBookings);
        System.out.println("Cancelled         : " + cancelledBookings);
        System.out.println("Pending           : " + pendingBookings);
        System.out.println("Total Revenue     : $" + totalRevenue);
        System.out.println("=====================================\n");

        return currentReport;
    }

    /**
     * Exports the most recently generated report as a string.
     * Used when administrator wants to save or print a report.
     */
    public void exportReport() {
        if (currentReport == null) {
            System.out.println("No report has been generated yet.");
            return;
        }
        System.out.println(currentReport.export());
    }

    /**
     * Displays all previously generated reports stored in the DataStore.
     * Used by administrators to view report history.
     */
    public void displayAllReports() {
        List<Report> reports = DataStore.getReports();

        if (reports.isEmpty()) {
            System.out.println("No reports have been generated yet.");
            return;
        }

        System.out.println("\n========== ALL REPORTS ==========");
        for (int i = 0; i < reports.size(); i++) {
            System.out.println((i + 1) + ". " + reports.get(i).getReportSummary());
        }
        System.out.println("==================================\n");
    }

    // Getters
    public String getSelectedReportType() { return selectedReportType; }
    public Report getCurrentReport() { return currentReport; }
    public String getGenerationStatus() { return generationStatus; }

    // Setters
    public void setSelectedReportType(String selectedReportType) { this.selectedReportType = selectedReportType; }
    public void setCurrentReport(Report currentReport) { this.currentReport = currentReport; }
    public void setGenerationStatus(String generationStatus) { this.generationStatus = generationStatus; }
}
