package string.class_problems;

import java.util.Scanner;

public class StudentRecordParser {

    // Parses a CSV line into Name, RollNumber, Department
    public static void parseStudentRecord(String csvLine) {
        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String name = fields[0].trim();
        String rollNumber = fields[1].trim();
        String department = fields[2].trim();

        System.out.println("Name: " + name + " | Roll No: " + rollNumber + " | Dept: " + department);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter CSV line (Name,RollNumber,Department): ");
        String csvLine = scanner.nextLine();

        parseStudentRecord(csvLine);

        scanner.close();
    }
}