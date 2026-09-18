package string.class_problems;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FirstNonRepeatingChar {

    // Finds the first character in the text that appears exactly once
    public static Character findFirstNonRepeatingChar(String text) {
        // LinkedHashMap keeps insertion order, so we can scan left to right later
        Map<Character, Integer> frequency = new LinkedHashMap<>();

        // Count how many times each character appears
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        // Scan left to right, return the first character with frequency 1
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (frequency.get(c) == 1) {
                return c;
            }
        }

        return null; // No non-repeating character found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word or sentence: ");
        String input = scanner.nextLine();

        Character result = findFirstNonRepeatingChar(input);

        if (result != null) {
            System.out.println("First Non-Repeating Character: '" + result + "'");
        } else {
            System.out.println("No Non-Repeating Character Found");
        }

        scanner.close();
    }
}