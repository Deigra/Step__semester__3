package string.assigment_problems;

import java.util.Scanner;

public class ExamHallSeatChecker {

    // Checks the array for duplicate seat numbers using nested loops (no Collections)
    public static void checkDuplicateSeats(int[] seatNumbers) {
        boolean foundDuplicate = false;

        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    foundDuplicate = true;
                }
            }
        }

        if (!foundDuplicate) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of seats: ");
        int n = Integer.parseInt(scanner.nextLine().trim());

        int[] seatNumbers = new int[n];
        System.out.println("Enter " + n + " seat numbers, one per line:");
        for (int i = 0; i < n; i++) {
            seatNumbers[i] = Integer.parseInt(scanner.nextLine().trim());
        }

        checkDuplicateSeats(seatNumbers);

        scanner.close();
    }
}
