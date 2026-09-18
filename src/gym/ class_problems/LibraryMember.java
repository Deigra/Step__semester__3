import java.util.Scanner;

class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    // Constructor with validation rules
    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid memberId: must be at least 4 non-whitespace characters.");
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    // Batch enrollment counting failures via try/catch
    public static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;

        for (String id : memberIds) {
            try {
                new LibraryMember(id, borrowLimit);
                enrolled++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }
}

// StudentMember subclass extending LibraryMember
class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Test Constructor Validation ---");
        System.out.print("Enter member ID (e.g., LB1 or STU10): ");
        String testId = scanner.nextLine();
        try {
            LibraryMember m = new LibraryMember(testId, 3);
            System.out.println("Member successfully created: " + testId);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        System.out.println("\n--- Test Batch Enrollment ---");
        System.out.print("Enter comma-separated member IDs for batch: ");
        String rawInput = scanner.nextLine();
        String[] batchIds = rawInput.split(",");
        for (int i = 0; i < batchIds.length; i++) {
            batchIds[i] = batchIds[i].trim();
        }

        System.out.println(LibraryMember.enrollBatch(batchIds, 3));
        scanner.close();
    }
}