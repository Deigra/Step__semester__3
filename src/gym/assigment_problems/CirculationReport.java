import java.util.Scanner;

class ReportMember {
    protected String memberId;

    public ReportMember(String memberId) {
        this.memberId = memberId;
    }

    public String displayInfo() {
        return "General Books: 0";
    }
}

class ReportStudentMember extends ReportMember {
    private String course;

    public ReportStudentMember(String memberId, String course) {
        super(memberId);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String displayInfo() {
        return "Student | Course: " + course + " | Books: 0";
    }
}

public class CirculationReport {

    public static String batchPrint(ReportMember[] members) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < members.length; i++) {
            ReportMember m = members[i];

            // Append polymorphic info
            sb.append(m.displayInfo());

            // Safe downcast guarded by instanceof
            if (m instanceof ReportStudentMember) {
                ReportStudentMember sm = (ReportStudentMember) m;
                sb.append(" [Course via downcast: ").append(sm.getCourse()).append("]");
            }

            if (i < members.length - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ReportMember[] batch = {
                new ReportMember("LB5"),
                new ReportStudentMember("STU6", "ECE")
        };

        System.out.println("--- Generated Report ---");
        System.out.println(batchPrint(batch));
    }
}