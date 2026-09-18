package string.class_problems;

import java.util.Scanner;

public class BankTransactionReferenceValidator {

    // Trims spaces and uppercases only the first 3 characters
    public static String normalizeReference(String raw) {
        String trimmed = raw.trim();

        if (trimmed.length() < 3) {
            return trimmed; // too short to normalize properly, return as-is
        }

        String bankCode = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);

        return bankCode + rest;
    }

    // Validates the normalized reference and builds a formatted display line
    public static String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            return "Invalid: reference must be exactly 14 characters";
        }

        String bankCode = reference.substring(0, 3);
        String datePart = reference.substring(3, 9);
        String seqPart = reference.substring(9, 14);

        // Check bank code is all letters
        for (int i = 0; i < bankCode.length(); i++) {
            if (!Character.isLetter(bankCode.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        // Check the remaining 11 characters (date + sequence) are all digits
        String body = datePart + seqPart;
        for (int i = 0; i < body.length(); i++) {
            if (!Character.isDigit(body.charAt(i))) {
                return "Invalid: date and sequence must be digits";
            }
        }

        // Format date as dd/MM/yy
        String day = datePart.substring(0, 2);
        String month = datePart.substring(2, 4);
        String year = datePart.substring(4, 6);

        StringBuilder result = new StringBuilder();
        result.append("[").append(bankCode).append("] ");
        result.append("DATE: ").append(day).append("/").append(month).append("/").append(year);
        result.append(" | SEQ: ").append(seqPart);

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter raw transaction reference: ");
        String raw = scanner.nextLine();

        String normalized = normalizeReference(raw);
        String result = validateAndFormat(normalized);

        System.out.println(result);

        scanner.close();
    }
}