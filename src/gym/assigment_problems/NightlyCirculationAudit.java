import java.util.Scanner;

class AuditLibraryMember {
    private static int membersEnrolled = 100; // Static counter starting prefix
    private final String memberNumber;        // Final immutable ID
    private int booksBorrowed;

    public AuditLibraryMember(int borrowLimit) {
        membersEnrolled++;
        this.memberNumber = "LIB-" + membersEnrolled;
        this.booksBorrowed = 0;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled - 100;
    }

    // Overloaded borrowBook method 1 (No-arg)
    public void borrowBook() {
        booksBorrowed++;
    }

    // Overloaded borrowBook method 2 (Delegates to no-arg version)
    public void borrowBook(String genre) {
        // Record genre logic if needed...
        borrowBook(); // Internal delegation
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    // Format check: exact format "R" + 2 digits + 1 uppercase letter (e.g. "R12A")
    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        return code.charAt(0) == 'R' &&
                Character.isDigit(code.charAt(1)) &&
                Character.isDigit(code.charAt(2)) &&
                Character.isUpperCase(code.charAt(3));
    }
}

class AuditFacultyMember extends AuditLibraryMember {
    private String department;

    public AuditFacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }
}

public class NightlyCirculationAudit {

    public static String processNightlyAudit(AuditLibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int facultyCount = 0;
        int regularCount = 0;

        for (AuditLibraryMember m : members) {
            if (m == null) {
                nullSkipped++;
                continue; // Skip null entries safely
            }

            processed++;
            if (m instanceof AuditFacultyMember) {
                facultyCount++;
            } else {
                regularCount++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " +
                facultyCount + " faculty | " + regularCount + " regular";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter renewal code to validate (e.g. R12A): ");
        String code = scanner.nextLine();
        System.out.println("Code valid: " + AuditLibraryMember.isValidRenewalCode(code));

        // Test nightly audit with null element present
        AuditLibraryMember[] auditBatch = {
                new AuditFacultyMember(5, "Physics"),
                null,
                new AuditLibraryMember(3)
        };

        System.out.println("\n--- Audit Summary ---");
        System.out.println(processNightlyAudit(auditBatch));
        scanner.close();
    }
}