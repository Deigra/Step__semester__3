import java.util.Scanner;

class AnnouncerMember {
    protected String memberId;

    public AnnouncerMember(String memberId) {
        this.memberId = memberId;
    }

    public String displayInfo() {
        return "Standard | Sessions: 0";
    }
}

class AnnouncerPremiumMember extends AnnouncerMember {
    private String trainerName;

    public AnnouncerPremiumMember(String memberId, String trainerName) {
        super(memberId);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium | Trainer: " + trainerName + " | Sessions: 0";
    }
}

public class AttendanceAnnouncer {

    public static String batchPrint(AnnouncerMember[] members) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < members.length; i++) {
            AnnouncerMember m = members[i];

            // Polymorphic display info
            sb.append(m.displayInfo());

            // Safe downcast with instanceof guard
            if (m instanceof AnnouncerPremiumMember) {
                AnnouncerPremiumMember pm = (AnnouncerPremiumMember) m;
                sb.append(" [Trainer via downcast: ").append(pm.getTrainerName()).append("]");
            }

            if (i < members.length - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AnnouncerMember[] batch = {
                new AnnouncerMember("MEM6"),
                new AnnouncerPremiumMember("MEM7", "Coach Riya")
        };

        System.out.println("--- Monthly Announcement ---");
        System.out.println(batchPrint(batch));
    }
}