import java.util.Scanner;

class BaseGymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public BaseGymMember(String memberId, int monthlyFee) {
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

    public void displayInfo() {
        System.out.println("Standard Member | Sessions: " + sessionsAttended);
    }
}

class BasePremiumMember extends BaseGymMember {
    protected String trainerName;

    public BasePremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    public void displayInfo() {
        System.out.println("Premium Member | Trainer: " + trainerName + " | Sessions: " + sessionsAttended);
    }
}

class EliteMember extends BasePremiumMember {
    private String lockerNumber;

    public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    @Override
    public void displayInfo() {
        System.out.println("Elite Member | Trainer: " + trainerName + " | Locker: " + lockerNumber + " | Sessions: " + sessionsAttended);
    }
}

class GroupClassMember extends BaseGymMember {
    private String className;

    public GroupClassMember(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    @Override
    public void displayInfo() {
        System.out.println("Group Class Member | Class: " + className + " | Sessions: " + sessionsAttended);
    }
}

public class GymMembershipTree {

    public static String classifyGeneration(BaseGymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof BasePremiumMember) {
            return "Direct premium subclass (2 generations deep)";
        } else {
            return "Base Gym Member";
        }
    }

    public static int getTotalSessionsAttended(BaseGymMember[] members) {
        int total = 0;
        for (BaseGymMember m : members) {
            if (m != null) {
                total += m.getSessionsAttended(); // Polymorphic method call
            }
        }
        return total;
    }

    public static void main(String[] args) {
        BasePremiumMember premium = new BasePremiumMember("MEM2", 2000, "Coach Riya");
        EliteMember elite = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember group = new GroupClassMember("MEM4", 1500, "Zumba");

        // Simulate session attendance
        for (int i = 0; i < 3; i++) premium.attendSession();
        for (int i = 0; i < 2; i++) elite.attendSession();
        for (int i = 0; i < 4; i++) group.attendSession();

        System.out.println("--- Classifications ---");
        System.out.println("Elite: " + classifyGeneration(elite));
        System.out.println("Group Class: " + classifyGeneration(group));

        System.out.println("\n--- Total Sessions (Polymorphic) ---");
        BaseGymMember[] members = { premium, elite, group };
        System.out.println("Total Sessions Attended: " + getTotalSessionsAttended(members));
    }
}
