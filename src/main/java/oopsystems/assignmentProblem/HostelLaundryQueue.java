package oopsystems.assignmentProblem;

// Interface for different wash types
interface WashType {

    String getName();

    int getDuration();

    double getCharge();
}


// Quick Wash
class QuickWash implements WashType {

    @Override
    public String getName() {
        return "Quick";
    }

    @Override
    public int getDuration() {
        return 30;
    }

    @Override
    public double getCharge() {
        return 20.0;
    }
}


// Normal Wash
class NormalWash implements WashType {

    @Override
    public String getName() {
        return "Normal";
    }

    @Override
    public int getDuration() {
        return 45;
    }

    @Override
    public double getCharge() {
        return 30.0;
    }
}


// Heavy Wash
class HeavyWash implements WashType {

    @Override
    public String getName() {
        return "Heavy";
    }

    @Override
    public int getDuration() {
        return 60;
    }

    @Override
    public double getCharge() {
        return 45.0;
    }
}


// Student for Laundry System
// Named LaundryStudent to avoid conflict with Q5 Student class
class LaundryStudent {

    private String name;

    public LaundryStudent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


// Washing Machine
class WashingMachine {

    private String machineId;
    private boolean busy;

    public WashingMachine(String machineId) {
        this.machineId = machineId;
        this.busy = false;
    }

    public String getMachineId() {
        return machineId;
    }

    public boolean isBusy() {
        return busy;
    }

    public void startWash() {
        busy = true;
    }

    public void completeWash() {
        busy = false;
    }
}


// Wash Cycle
class WashCycle {

    private LaundryStudent student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(
            LaundryStudent student,
            WashingMachine machine,
            WashType washType) {

        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public void start() {

        // Check whether machine is already busy
        if (machine.isBusy()) {

            System.out.println(
                    "Machine " + machine.getMachineId()
                            + " is currently busy."
            );

            return;
        }

        // Start the machine
        machine.startWash();

        System.out.println(
                washType.getName()
                        + " wash started on "
                        + machine.getMachineId()
                        + " for "
                        + student.getName()
                        + " ("
                        + washType.getDuration()
                        + " min)."
        );

        System.out.println(
                "Charge: ₹" + washType.getCharge()
        );
    }

    public void complete() {

        machine.completeWash();

        System.out.println(
                "Machine "
                        + machine.getMachineId()
                        + " cycle completed."
        );

        System.out.println(
                "Machine "
                        + machine.getMachineId()
                        + " is now free."
        );
    }
}


// Main Class
public class HostelLaundryQueue {

    public static void main(String[] args) {

        // Students
        LaundryStudent asha =
                new LaundryStudent("Asha");

        LaundryStudent ravi =
                new LaundryStudent("Ravi");

        LaundryStudent neha =
                new LaundryStudent("Neha");


        // Washing machines
        WashingMachine m1 =
                new WashingMachine("M1");

        WashingMachine m2 =
                new WashingMachine("M2");


        // Asha uses Quick Wash on M1
        WashCycle cycle1 =
                new WashCycle(
                        asha,
                        m1,
                        new QuickWash()
                );

        cycle1.start();


        // Ravi tries to use busy M1
        WashCycle cycle2 =
                new WashCycle(
                        ravi,
                        m1,
                        new HeavyWash()
                );

        cycle2.start();


        // Ravi uses M2 instead
        WashCycle cycle3 =
                new WashCycle(
                        ravi,
                        m2,
                        new HeavyWash()
                );

        cycle3.start();


        // Asha's wash on M1 is completed
        cycle1.complete();


        // Neha now uses the free M1
        WashCycle cycle4 =
                new WashCycle(
                        neha,
                        m1,
                        new NormalWash()
                );

        cycle4.start();
    }
}