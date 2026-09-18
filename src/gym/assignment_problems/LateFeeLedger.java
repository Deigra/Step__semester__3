import java.util.Arrays;
import java.util.Scanner;

class FeeGymMember {
    protected String memberId;
    private int[] lateFeeHistory;
    private int feeCount;

    public FeeGymMember(String memberId) {
        this.memberId = memberId;
        this.lateFeeHistory = new int[10];
        this.feeCount = 0;
    }

    protected void chargeLateFee(int amount) {
        if (feeCount < lateFeeHistory.length) {
            lateFeeHistory[feeCount++] = amount;
        }
    }

    // Return a defensive copy to protect internal state
    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }

    public int getTotalLateFees() {
        int sum = 0;
        for (int i = 0; i < feeCount; i++) {
            sum += lateFeeHistory[i];
        }
        return sum;
    }
}

class DiscountPremiumMember extends FeeGymMember {

    public DiscountPremiumMember(String memberId) {
        super(memberId);
    }

    @Override
    protected void chargeLateFee(int amount) {
        // Halve the fee and forward to super
        super.chargeLateFee(amount / 2);
    }
}

public class LateFeeLedger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Member ID: ");
        String id = scanner.nextLine();
        DiscountPremiumMember premiumMember = new DiscountPremiumMember(id);

        System.out.print("Enter late fee amount: ");
        int amount = scanner.nextInt();

        premiumMember.chargeLateFee(amount);
        System.out.println("Total Late Fees: " + premiumMember.getTotalLateFees());

        int[] history = premiumMember.getLateFeeHistory();
        System.out.println("Recorded Fee History: " + Arrays.toString(history));

        // Test array tampering protection
        if (history.length > 0) {
            history[0] = 999;
        }
        System.out.println("History after external tampering attempt: " + Arrays.toString(premiumMember.getLateFeeHistory()));
        scanner.close();
    }
}