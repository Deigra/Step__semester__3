package string.class_problems;

import java.util.Scanner;

public class VIPCustomerCheck {

    // Checks if a customer ID follows the VIP naming convention
    public static String validateCustomerId(String customerId) {
        if (customerId.startsWith("VIP-")) {
            return "VIP Customer";
        } else {
            return "Regular Customer";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine().trim();

        String result = validateCustomerId(customerId);
        System.out.println(result);

        scanner.close();
    }
}