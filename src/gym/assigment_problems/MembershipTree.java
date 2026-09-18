import java.util.Scanner;

class BaseLibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public BaseLibraryMember(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {
        booksBorrowed++;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void displayInfo() {
        System.out.println("General Member | Books Borrowed: " + booksBorrowed);
    }
}

class BaseStudentMember extends BaseLibraryMember {
    protected String course;

    public BaseStudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    public void displayInfo() {
        System.out.println("Student Member | Course: " + course + " | Books Borrowed: " + booksBorrowed);
    }
}

class HonorsStudentMember extends BaseStudentMember {
    private int bonusLimit;

    public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public void displayInfo() {
        System.out.println("Honors Student Member | Course: " + course + " | Bonus Limit: " + bonusLimit + " | Books Borrowed: " + booksBorrowed);
    }
}

class FacultyMember extends BaseLibraryMember {
    private String department;

    public FacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    @Override
    public void displayInfo() {
        System.out.println("Faculty Member | Department: " + department + " | Books Borrowed: " + booksBorrowed);
    }
}

public class MembershipTree {

    public static String classifyGeneration(BaseLibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof BaseStudentMember) {
            return "Direct student subclass (2 generations deep)";
        } else {
            return "Base Library Member";
        }
    }

    public static int getTotalBooksBorrowed(BaseLibraryMember[] members) {
        int total = 0;
        for (BaseLibraryMember m : members) {
            if (m != null) {
                total += m.getBooksBorrowed(); // Polymorphic call
            }
        }
        return total;
    }

    public static void main(String[] args) {
        BaseStudentMember student = new BaseStudentMember("STU2", 3, "CSE");
        HonorsStudentMember honors = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember faculty = new FacultyMember("STU4", 5, "Physics");

        // Simulate borrowed books
        student.borrowBook(); student.borrowBook(); // 2
        honors.borrowBook();                        // 1
        faculty.borrowBook(); faculty.borrowBook(); faculty.borrowBook(); // 3

        System.out.println("--- Classifications ---");
        System.out.println("Honors: " + classifyGeneration(honors));
        System.out.println("Faculty: " + classifyGeneration(faculty));

        System.out.println("\n--- Polymorphic Books Sum ---");
        BaseLibraryMember[] array = { student, honors, faculty };
        System.out.println("Total Books Borrowed: " + getTotalBooksBorrowed(array));
    }
}