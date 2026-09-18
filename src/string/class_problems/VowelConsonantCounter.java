package string.class_problems;

import java.util.Scanner;

public class VowelConsonantCounter {

    // Counts vowels and consonants, ignoring spaces
    public static void countVowelsAndConsonants(String text) {
        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));

            if (c == ' ') {
                continue; // ignore spaces
            }

            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowels++;
            } else {
                consonants++;
            }
        }

        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String text = scanner.nextLine();

        countVowelsAndConsonants(text);

        scanner.close();
    }
}
