package string.class_problems;

import java.util.Scanner;

public class MaskedPhoneNumberFormatter {

    // Validates and masks a 10-digit phone number
    public static String maskPhoneNumber(String phone) {
        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        // Check that all characters are digits
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        String lastFourDigits = phone.substring(6); // last 4 digits

        StringBuilder masked = new StringBuilder();
        masked.append("XXXXXX");
        masked.append("-");
        masked.append(lastFourDigits);

        return masked.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine().trim();

        String result = maskPhoneNumber(phone);
        System.out.println(result);

        scanner.close();
    }
}
