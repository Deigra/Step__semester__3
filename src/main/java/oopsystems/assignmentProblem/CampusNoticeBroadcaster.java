package oopsystems.assignmentProblem;

import java.util.ArrayList;
import java.util.List;

// Interface for different notification channels
interface NotificationChannel {
    void send(Student student, Notice notice);
}

// Email notification
class EmailChannel implements NotificationChannel {

    @Override
    public void send(Student student, Notice notice) {
        System.out.println("[Email → " + student.getName() + "] " + notice.getTitle());
    }
}

// SMS notification
class SmsChannel implements NotificationChannel {

    @Override
    public void send(Student student, Notice notice) {
        System.out.println("[SMS → " + student.getName() + "] " + notice.getTitle());
    }
}

// App notification
class AppChannel implements NotificationChannel {

    @Override
    public void send(Student student, Notice notice) {
        System.out.println("[App → " + student.getName() + "] " + notice.getTitle());
    }
}

// Student class
class Student {

    private String name;
    private String department;
    private List<NotificationChannel> preferredChannels;

    public Student(String name, String department) {
        this.name = name;
        this.department = department;
        this.preferredChannels = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    // Student chooses a notification channel
    public void addPreferredChannel(NotificationChannel channel) {
        preferredChannels.add(channel);
    }

    public List<NotificationChannel> getPreferredChannels() {
        return preferredChannels;
    }
}

// Notice class
class Notice {

    private String title;
    private List<String> targetDepartments;

    public Notice(String title, List<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = targetDepartments;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTargetDepartments() {
        return targetDepartments;
    }

    // Validate the notice
    public boolean isValid() {

        if (title == null || title.trim().isEmpty()) {
            System.out.println("Cannot post notice: Title is required.");
            return false;
        }

        if (targetDepartments == null || targetDepartments.isEmpty()) {
            System.out.println(
                    "Cannot post notice: At least one target department is required."
            );
            return false;
        }

        return true;
    }
}

// NoticeBoard class
class NoticeBoard {

    private List<Student> students;

    public NoticeBoard() {
        students = new ArrayList<>();
    }

    // Add student to notice board
    public void addStudent(Student student) {
        students.add(student);
    }

    // Post notice
    public void postNotice(Notice notice) {

        if (!notice.isValid()) {
            return;
        }

        System.out.print("Notice '" + notice.getTitle() + "' posted to ");

        for (int i = 0; i < notice.getTargetDepartments().size(); i++) {
            System.out.print(notice.getTargetDepartments().get(i));

            if (i < notice.getTargetDepartments().size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.println(".");

        // Find students in target departments
        for (Student student : students) {

            if (notice.getTargetDepartments()
                    .contains(student.getDepartment())) {

                // Send through every preferred channel
                for (NotificationChannel channel :
                        student.getPreferredChannels()) {

                    channel.send(student, notice);
                }
            }
        }
    }
}

// Main class
public class CampusNoticeBroadcaster {

    public static void main(String[] args) {

        // Create students
        Student asha = new Student("Asha", "CSE");
        Student ravi = new Student("Ravi", "ECE");

        // Students choose their preferred channels
        asha.addPreferredChannel(new EmailChannel());
        asha.addPreferredChannel(new AppChannel());

        ravi.addPreferredChannel(new SmsChannel());

        // Create notice board
        NoticeBoard noticeBoard = new NoticeBoard();

        noticeBoard.addStudent(asha);
        noticeBoard.addStudent(ravi);

        // Notice 1
        List<String> cse = new ArrayList<>();
        cse.add("CSE");

        Notice notice1 = new Notice(
                "Lab Closed Tomorrow",
                cse
        );

        noticeBoard.postNotice(notice1);

        // Notice 2
        List<String> cseAndEce = new ArrayList<>();
        cseAndEce.add("CSE");
        cseAndEce.add("ECE");

        Notice notice2 = new Notice(
                "Fee Deadline Extended",
                cseAndEce
        );

        noticeBoard.postNotice(notice2);

        // Notice 3 - invalid because no department
        List<String> noDepartment = new ArrayList<>();

        Notice notice3 = new Notice(
                "Sports Day",
                noDepartment
        );

        noticeBoard.postNotice(notice3);
    }
}