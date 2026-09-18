package string.class_problems;

import java.util.Scanner;

public class ShortestLongestWordFinder {

    // Returns an array: [0] = shortest word, [1] = longest word
    public static String[] findShortestAndLongestWord(String text) {
        // Split on one or more spaces, so extra spaces don't create empty words
        String[] words = text.trim().split("\\s+");

        String shortest = words[0];
        String longest = words[0];

        for (String word : words) {
            if (word.length() < shortest.length()) {
                shortest = word;
            }
            if (word.length() > longest.length()) {
                longest = word;
            }
        }

        return new String[] { shortest, longest };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String input = scanner.nextLine();

        String[] result = findShortestAndLongestWord(input);
        String shortest = result[0];
        String longest = result[1];

        System.out.println("Shortest: \"" + shortest + "\" (" + shortest.length() + ")");
        System.out.println("Longest: \"" + longest + "\" (" + longest.length() + ")");

        scanner.close();
    }
}
