package string.class_problems;

import java.util.Scanner;

public class FileExtensionValidator {

    // Validates whether the filename has an accepted extension
    public static String validateFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');

        if (dotIndex == -1 || dotIndex == filename.length() - 1) {
            return "Rejected — invalid file type";
        }

        String extension = filename.substring(dotIndex + 1);

        if (extension.equalsIgnoreCase("pdf") ||
                extension.equalsIgnoreCase("docx") ||
                extension.equalsIgnoreCase("zip")) {
            return "Accepted";
        } else {
            return "Rejected — invalid file type";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter filename: ");
        String filename = scanner.nextLine().trim();

        String result = validateFileExtension(filename);
        System.out.println(result);

        scanner.close();
    }
}
