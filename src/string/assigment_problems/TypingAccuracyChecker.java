package string.assigment_problems;

import java.util.Scanner;

public class TypingAccuracyChecker {

    // Compares two equal-length strings character by character
    public static void checkTypingAccuracy(String original, String typed) {
        int length = original.length();
        int matched = 0;
        int firstMismatchPosition = -1;

        for (int i = 0; i < length; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatchPosition == -1) {
                // Record only the FIRST mismatch position (1-based, as per sample output)
                firstMismatchPosition = i + 1;
            }
        }

        double accuracy = ((double) matched / length) * 100;

        System.out.printf("Matched: %d/%d | Accuracy: %.2f%%", matched, length, accuracy);

        if (firstMismatchPosition == -1) {
            System.out.println(" | No Mismatches");
        } else {
            char originalChar = original.charAt(firstMismatchPosition - 1);
            char typedChar = typed.charAt(firstMismatchPosition - 1);
            System.out.println(" | First Mismatch at position " + firstMismatchPosition +
                    " ('" + originalChar + "' vs '" + typedChar + "')");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter original passage: ");
        String original = scanner.nextLine();

        System.out.print("Enter typed text: ");
        String typed = scanner.nextLine();

        if (original.length() != typed.length()) {
            System.out.println("Error: Both strings must be of equal length.");
        } else {
            checkTypingAccuracy(original, typed);
        }

        scanner.close();
    }
}