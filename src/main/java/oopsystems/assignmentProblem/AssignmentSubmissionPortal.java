package oopsystems.assignmentProblem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Abstract class for different assignment types
abstract class Assignment {

    private String title;
    private int maxMarks;
    private LocalDate dueDate;

    public Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    // Each assignment type provides its own penalty
    public abstract double calculateFinalMarks(
            double awardedMarks,
            long lateDays
    );
}

// Coding assignment
class CodingAssignment extends Assignment {

    public CodingAssignment(
            String title,
            int maxMarks,
            LocalDate dueDate) {

        super(title, maxMarks, dueDate);
    }

    @Override
    public double calculateFinalMarks(
            double awardedMarks,
            long lateDays) {

        double penalty = lateDays * 0.10;

        return awardedMarks * (1 - penalty);
    }
}

// Written assignment
class WrittenAssignment extends Assignment {

    public WrittenAssignment(
            String title,
            int maxMarks,
            LocalDate dueDate) {

        super(title, maxMarks, dueDate);
    }

    @Override
    public double calculateFinalMarks(
            double awardedMarks,
            long lateDays) {

        double penalty = lateDays * 0.20;

        return awardedMarks * (1 - penalty);
    }
}

// Student class
class SubmissionStudent {

    private String name;

    public SubmissionStudent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Submission class
class Submission {

    private SubmissionStudent student;
    private Assignment assignment;
    private LocalDate submissionDate;
    private String status;
    private double finalMarks;

    public Submission(
            SubmissionStudent student,
            Assignment assignment,
            LocalDate submissionDate) {

        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.status = "Submitted";
    }

    public void grade(double awardedMarks) {

        if (!status.equals("Submitted")) {
            System.out.println(
                    "Cannot grade: submission is not in Submitted status."
            );
            return;
        }

        long lateDays = 0;

        if (submissionDate.isAfter(assignment.getDueDate())) {
            lateDays = ChronoUnit.DAYS.between(
                    assignment.getDueDate(),
                    submissionDate
            );
        }

        finalMarks = assignment.calculateFinalMarks(
                awardedMarks,
                lateDays
        );

        status = "Graded";

        System.out.println(
                student.getName()
                        + " graded: "
                        + formatMarks(finalMarks)
                        + "/"
                        + assignment.getMaxMarks()
        );

        if (lateDays > 0) {
            System.out.println(
                    "Late by " + lateDays + " day(s)."
            );
        }

        System.out.println("Status: Graded.");
    }

    public boolean canResubmit() {
        return status.equals("Submitted");
    }

    public void resubmit(LocalDate newDate) {

        if (!canResubmit()) {
            System.out.println(
                    "Cannot resubmit: '"
                            + assignment.getTitle()
                            + "' has already been graded."
            );
            return;
        }

        submissionDate = newDate;

        System.out.println(
                student.getName()
                        + " resubmitted '"
                        + assignment.getTitle()
                        + "'."
        );
    }

    private String formatMarks(double marks) {

        if (marks == (int) marks) {
            return String.valueOf((int) marks);
        }

        return String.format("%.2f", marks);
    }
}

// Main class
public class AssignmentSubmissionPortal {

    public static void main(String[] args) {

        // Create students
        SubmissionStudent asha =
                new SubmissionStudent("Asha");

        SubmissionStudent ravi =
                new SubmissionStudent("Ravi");

        // Create assignments
        Assignment coding =
                new CodingAssignment(
                        "Linked List Lab",
                        50,
                        LocalDate.of(2026, 3, 10)
                );

        Assignment written =
                new WrittenAssignment(
                        "Design Essay",
                        50,
                        LocalDate.of(2026, 3, 12)
                );

        // Asha submits coding assignment on time
        Submission ashaSubmission =
                new Submission(
                        asha,
                        coding,
                        LocalDate.of(2026, 3, 10)
                );

        System.out.println(
                "Asha's submission for 'Linked List Lab' received (on time)."
        );
        System.out.println("Status: Submitted.");

        // Ravi submits written assignment 2 days late
        Submission raviSubmission =
                new Submission(
                        ravi,
                        written,
                        LocalDate.of(2026, 3, 14)
                );

        System.out.println(
                "Ravi's submission for 'Design Essay' received (2 days late)."
        );
        System.out.println("Status: Submitted.");

        // Faculty grades Asha
        ashaSubmission.grade(45);

        // Faculty grades Ravi
        raviSubmission.grade(40);

        // Asha tries to resubmit after grading
        ashaSubmission.resubmit(
                LocalDate.of(2026, 3, 11)
        );
    }
}