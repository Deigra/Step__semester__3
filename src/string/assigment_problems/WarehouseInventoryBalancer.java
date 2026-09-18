package string.assigment_problems;

import java.util.Scanner;

public class WarehouseInventoryBalancer {

    // Compares totals of two sections and finds the highest quantity overall
    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0, totalB = 0;

        for (int qty : sectionA) {
            totalA += qty;
        }
        for (int qty : sectionB) {
            totalB += qty;
        }

        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";

        // Track the highest quantity and where it was found
        int highestQty = sectionA[0];
        String highestSection = "Section A";
        int highestIndex = 0;

        for (int i = 0; i < sectionA.length; i++) {
            if (sectionA[i] > highestQty) {
                highestQty = sectionA[i];
                highestSection = "Section A";
                highestIndex = i;
            }
        }
        for (int i = 0; i < sectionB.length; i++) {
            if (sectionB[i] > highestQty) {
                highestQty = sectionB[i];
                highestSection = "Section B";
                highestIndex = i;
            }
        }

        System.out.println("Section A Total: " + totalA + " | Section B Total: " + totalB +
                " | Status: " + status + " | Highest Quantity: " + highestQty +
                " (" + highestSection + ", Item " + (highestIndex + 1) + ")");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of items per section: ");
        int n = Integer.parseInt(scanner.nextLine().trim());

        int[] sectionA = new int[n];
        int[] sectionB = new int[n];

        System.out.println("Enter " + n + " quantities for Section A, one per line:");
        for (int i = 0; i < n; i++) {
            sectionA[i] = Integer.parseInt(scanner.nextLine().trim());
        }

        System.out.println("Enter " + n + " quantities for Section B, one per line:");
        for (int i = 0; i < n; i++) {
            sectionB[i] = Integer.parseInt(scanner.nextLine().trim());
        }

        analyzeInventory(sectionA, sectionB);

        scanner.close();
    }
}