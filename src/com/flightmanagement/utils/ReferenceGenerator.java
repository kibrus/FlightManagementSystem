/**
 * ReferenceGenerator.java
 * Provides unique reference number generation utilities for the
 * Flight Management System. Used to generate booking references,
 * payment confirmation numbers, and reservation IDs to ensure
 * every transaction has a unique identifier.
 *
 * Author: Adrian Salgado
 * Date: 04/27/2026
 * Course: CS 3321 - Software Engineering
 */

package com.flightmanagement.utils;

import java.util.Random;

public class ReferenceGenerator {

    private static Random random = new Random();

    /**
     * Generates a unique booking reference number.
     * Format: BK followed by 6 random uppercase letters and digits.
     * Example: BKAX72KP
     */
    public static String generateBookingReference() {
        return "BK" + generateRandomString(6);
    }

    /**
     * Generates a unique payment confirmation number.
     * Format: CONF followed by 8 random uppercase letters and digits.
     * Example: CONFAX72KP9Z
     */
    public static String generateConfirmationNumber() {
        return "CONF" + generateRandomString(8);
    }

    /**
     * Generates a unique reservation ID.
     * Format: RES followed by 6 random uppercase letters and digits.
     * Example: RESAX72KP
     */
    public static String generateReservationID() {
        return "RES" + generateRandomString(6);
    }

    /**
     * Generates a unique transaction reference number.
     * Format: TXN followed by 10 random uppercase letters and digits.
     * Example: TXNAX72KP9ZQW
     */
    public static String generateTransactionReference() {
        return "TXN" + generateRandomString(10);
    }

    /**
     * Generates a random alphanumeric string of the given length.
     * Uses uppercase letters and digits for readability.
     * Used internally by all reference generation methods.
     */
    private static String generateRandomString(int length) {
        // characters to use in reference numbers
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            // pick a random character from the allowed set
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }

        return result.toString();
    }
}
