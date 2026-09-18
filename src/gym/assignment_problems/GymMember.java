import java.util.Scanner;

public class GymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    // Constructor with validation rule
    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid memberId: must be at least 4 characters.");
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        this.sessionsAttended++;
    }

    public int getSessionsAttended() {
        return this.sessionsAttended;
    }

    // Process array entries and count construction rejections
    public static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;

        for (String id : memberIds) {
            try {
                new GymMember(id, monthlyFee);
                signedUp++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Individual Constructor Validation Test
        System.out.print("Enter member ID (e.g. GM1 or MEM01): ");
        String id = scanner.nextLine();
        try {
            GymMember m = new GymMember(id, 1000);
            System.out.println("Member created successfully: " + id);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        // Batch Sign-up Test
        System.out.print("Enter comma-separated member IDs for batch sign-up: ");
        String input = scanner.nextLine();
        String[] batch = input.split(",");
        for (int i = 0; i < batch.length; i++) {
            batch[i] = batch[i].trim();
        }

        System.out.println(GymMember.signUpBatch(batch, 1000));
        scanner.close();
    }
}

// Subclass extending GymMember directly
class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }
}