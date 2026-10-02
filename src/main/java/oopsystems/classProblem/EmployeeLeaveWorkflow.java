package oopsystems.classProblem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Abstract Employee class
abstract class Employee {

    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Each employee type can define its own leave rule
    public abstract boolean canTakeLeave(long numberOfDays);
}


// Full-time employee
class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(long numberOfDays) {
        return numberOfDays <= 30;
    }
}


// Part-time employee
class PartTimeEmployee extends Employee {

    public PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(long numberOfDays) {
        return numberOfDays <= 15;
    }
}


// Contractor
class Contractor extends Employee {

    public Contractor(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(long numberOfDays) {
        return numberOfDays <= 10;
    }
}


// Leave Request
class LeaveRequest {

    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public LeaveRequest(
            Employee employee,
            LocalDate startDate,
            LocalDate endDate) {

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "Pending";
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public long getNumberOfDays() {
        return ChronoUnit.DAYS.between(
                startDate,
                endDate
        ) + 1;
    }

    // Approve the request
    public void approve() {

        if (!status.equals("Pending")) {
            System.out.println(
                    "Cannot approve: leave request is already "
                            + status + "."
            );
            return;
        }

        if (!employee.canTakeLeave(getNumberOfDays())) {
            System.out.println(
                    "Leave request rejected: leave duration "
                            + "exceeds the employee's allowed limit."
            );
            return;
        }

        status = "Approved";

        System.out.println(
                employee.getName()
                        + "'s leave request ("
                        + startDate
                        + "-"
                        + endDate
                        + ") approved."
        );

        System.out.println("Status: Approved.");
    }


    // Reject the request
    public void reject() {

        if (!status.equals("Pending")) {
            System.out.println(
                    "Cannot reject: leave request is already "
                            + status + "."
            );
            return;
        }

        status = "Rejected";

        System.out.println(
                employee.getName()
                        + "'s leave request ("
                        + startDate
                        + "-"
                        + endDate
                        + ") rejected."
        );

        System.out.println("Status: Rejected.");
    }


    // Attempt to change status back to Pending
    public void changeToPending() {

        if (!status.equals("Pending")) {

            System.out.println(
                    "Cannot change leave request status from "
                            + status
                            + " to Pending."
            );

            return;
        }

        System.out.println(
                "Leave request is already Pending."
        );
    }
}


// Reviewer / Manager
class Reviewer {

    private String name;

    public Reviewer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void approveRequest(LeaveRequest request) {
        System.out.println(
                "Manager "
                        + name
                        + " reviews the request."
        );

        request.approve();
    }

    public void rejectRequest(LeaveRequest request) {
        System.out.println(
                "Manager "
                        + name
                        + " reviews the request."
        );

        request.reject();
    }
}


// Main class
public class EmployeeLeaveWorkflow {

    public static void main(String[] args) {

        // Employees
        Employee john =
                new FullTimeEmployee("John");

        Employee jane =
                new PartTimeEmployee("Jane");


        // Managers
        Reviewer alice =
                new Reviewer("Alice");

        Reviewer bob =
                new Reviewer("Bob");


        // John's leave request
        LeaveRequest johnLeave =
                new LeaveRequest(
                        john,
                        LocalDate.of(2026, 1, 1),
                        LocalDate.of(2026, 1, 5)
                );

        System.out.println(
                "Leave request submitted for "
                        + john.getName()
                        + " (Jan 1-5)."
        );

        System.out.println(
                "Status: " + johnLeave.getStatus() + "."
        );


        // Alice approves John's request
        alice.approveRequest(johnLeave);


        System.out.println();


        // Jane's leave request
        LeaveRequest janeLeave =
                new LeaveRequest(
                        jane,
                        LocalDate.of(2026, 2, 10),
                        LocalDate.of(2026, 2, 11)
                );

        System.out.println(
                "Leave request submitted for "
                        + jane.getName()
                        + " (Feb 10-11)."
        );

        System.out.println(
                "Status: " + janeLeave.getStatus() + "."
        );


        // Bob rejects Jane's request
        bob.rejectRequest(janeLeave);


        System.out.println();


        // John tries to change Approved back to Pending
        johnLeave.changeToPending();
    }
}