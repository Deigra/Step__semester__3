import java.util.Scanner;

class AuditGymMember {
    private static int membersEnrolled = 2000; // Static counter initialized
    private final String membershipNumber;    // Final unique membership ID
    private int feesPaid;

    public AuditGymMember(int monthlyFee) {
        membersEnrolled++;
        this.membershipNumber = "GYM-" + membersEnrolled;
        this.feesPaid = 0;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled - 2000;
    }

    // Overloaded payFee method (1 argument)
    public void payFee(int amount) {
        this.feesPaid += amount;
    }

    // Overloaded payFee method (2 arguments - delegates internally to 1 argument version)
    public void payFee(int amount, String mode) {
        // Option to log mode...
        payFee(amount); // Internal delegation
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    // Exact format check: "G" + 2 digits + 1 uppercase letter (e.g. "G45B")
    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        return code.charAt(0) == 'G' &&
                Character.isDigit(code.charAt(1)) &&
                Character.isDigit(code.charAt(2)) &&
                Character.isUpperCase(code.charAt(3));
    }
}

class AuditGroupClassMember extends AuditGymMember {
    private String className;

    public AuditGroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }
}

public class WeeklyCheckinSettlement {

    public static String processWeeklyCheckIn(AuditGymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (AuditGymMember m : members) {
            if (m == null) {
                nullSkipped++;
                continue; // Null safety check
            }

            processed++;
            if (m instanceof AuditGroupClassMember) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " +
                groupCount + " group | " + individualCount + " individual";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Code format validation test
        System.out.print("Enter referral code to check (e.g., G45B): ");
        String code = scanner.nextLine();
        System.out.println("Valid: " + AuditGymMember.isValidReferralCode(code));

        // Overloaded payFee test
        AuditGymMember m1 = new AuditGymMember(1000);
        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println("Generated ID: " + m1.getMembershipNumber());
        System.out.println("Total Fees Paid: " + m1.getFeesPaid());

        // Weekly batch check-in test containing a null value
        AuditGymMember[] checkInBatch = {
                new AuditGroupClassMember(1500, "Zumba"),
                null,
                new AuditGymMember(1000)
        };

        System.out.println("\n--- Settlement Summary ---");
        System.out.println(processWeeklyCheckIn(checkInBatch));
        scanner.close();
    }
}