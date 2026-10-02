package oopsystems.assignmentProblem;

// Interface for membership plans
interface MembershipPlan {

    double calculateFee();

    String getPlanName();
}


// Monthly plan
class MonthlyPlan implements MembershipPlan {

    private final double baseRate = 1000;

    @Override
    public double calculateFee() {
        return baseRate;
    }

    @Override
    public String getPlanName() {
        return "Monthly";
    }
}


// Quarterly plan
class QuarterlyPlan implements MembershipPlan {

    private final double baseRate = 1000;

    @Override
    public double calculateFee() {
        return baseRate * 3 * 0.90;
    }

    @Override
    public String getPlanName() {
        return "Quarterly";
    }
}


// Annual plan
class AnnualPlan implements MembershipPlan {

    private final double baseRate = 1000;

    @Override
    public double calculateFee() {
        return baseRate * 12 * 0.75;
    }

    @Override
    public String getPlanName() {
        return "Annual";
    }
}


// Member class
class Member {

    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


// Membership class
class Membership {

    private Member member;
    private MembershipPlan plan;
    private String status;

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.status = "Active";

        System.out.printf(
                "%s membership created for %s. Fee: ₹%.2f. Status: Active.%n",
                plan.getPlanName(),
                member.getName(),
                plan.calculateFee()
        );
    }

    public void checkIn() {

        if (status.equals("Active")) {

            System.out.println(
                    member.getName()
                            + " checked in successfully."
            );

        } else {

            System.out.println(
                    "Check-in denied: "
                            + member.getName()
                            + "'s membership is "
                            + status
                            + "."
            );
        }
    }

    public void freeze() {

        if (status.equals("Active")) {

            status = "Frozen";

            System.out.println(
                    member.getName()
                            + "'s membership frozen."
            );

            System.out.println("Status: Frozen.");

        } else if (status.equals("Expired")) {

            System.out.println(
                    "Cannot freeze an Expired membership."
            );

        } else {

            System.out.println(
                    member.getName()
                            + "'s membership is already Frozen."
            );
        }
    }

    public void unfreeze() {

        if (status.equals("Frozen")) {

            status = "Active";

            System.out.println(
                    member.getName()
                            + "'s membership unfrozen."
            );

            System.out.println("Status: Active.");

        } else if (status.equals("Expired")) {

            System.out.println(
                    "Cannot unfreeze an Expired membership."
            );

        } else {

            System.out.println(
                    "Cannot unfreeze an Active membership."
            );
        }
    }

    public void expire() {

        status = "Expired";

        System.out.println(
                member.getName()
                        + "'s membership expired."
        );

        System.out.println("Status: Expired.");
    }
}


// Main class
public class MembershipDesk {

    public static void main(String[] args) {

        // Create members
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        // Asha buys Quarterly membership
        Membership ashaMembership =
                new Membership(
                        asha,
                        new QuarterlyPlan()
                );

        // Ravi buys Monthly membership
        Membership raviMembership =
                new Membership(
                        ravi,
                        new MonthlyPlan()
                );

        // Asha checks in
        ashaMembership.checkIn();

        // Asha freezes membership
        ashaMembership.freeze();

        // Asha tries to check in while Frozen
        ashaMembership.checkIn();

        // Ravi's membership expires
        raviMembership.expire();

        // Ravi tries to freeze after expiry
        raviMembership.freeze();
    }
}