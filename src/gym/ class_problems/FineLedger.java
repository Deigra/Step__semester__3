import java.util.Arrays;
import java.util.Scanner;

class FineLibraryMember {
    protected String memberId;
    private int[] fineHistory;
    private int fineCount;

    public FineLibraryMember(String memberId) {
        this.memberId = memberId;
        this.fineHistory = new int[10];
        this.fineCount = 0;
    }

    protected void chargeFine(int amount) {
        if (fineCount < fineHistory.length) {
            fineHistory[fineCount++] = amount;
        }
    }

    // Defensive copy return
    public int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {
        int sum = 0;
        for (int i = 0; i < fineCount; i++) {
            sum += fineHistory[i];
        }
        return sum;
    }
}

class DiscountStudentMember extends FineLibraryMember {

    public DiscountStudentMember(String memberId) {
        super(memberId);
    }

    @Override
    protected void chargeFine(int amount) {
        // Halve the fine amount and let super handle recording
        super.chargeFine(amount / 2);
    }
}

public class FineLedger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter member ID: ");
        String id = scanner.nextLine();
        DiscountStudentMember student = new DiscountStudentMember(id);

        System.out.print("Enter fine amount to charge: ");
        int amount = scanner.nextInt();

        student.chargeFine(amount);
        System.out.println("Total Fine Charged: " + student.getTotalFine());

        int[] history = student.getFineHistory();
        System.out.println("Fine History Copy: " + Arrays.toString(history));

        // Test defensive copy encapsulation
        if (history.length > 0) {
            history[0] = 999; // Tamper with array copy
        }
        System.out.println("Fine History After External Tampering: " + Arrays.toString(student.getFineHistory()));
        scanner.close();
    }
}