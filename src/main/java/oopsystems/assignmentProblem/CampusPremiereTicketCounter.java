package oopsystems.assignmentProblem;

import java.util.ArrayList;
import java.util.List;

// Interface for different seat categories
interface Seat {

    String getSeatNumber();

    double getPrice();
}


// Regular seat
class RegularSeat implements Seat {

    private String seatNumber;

    public RegularSeat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String getSeatNumber() {
        return seatNumber;
    }

    @Override
    public double getPrice() {
        return 150.0;
    }
}


// Premium seat
class PremiumSeat implements Seat {

    private String seatNumber;

    public PremiumSeat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String getSeatNumber() {
        return seatNumber;
    }

    @Override
    public double getPrice() {
        return 250.0;
    }
}


// Recliner seat
class ReclinerSeat implements Seat {

    private String seatNumber;

    public ReclinerSeat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String getSeatNumber() {
        return seatNumber;
    }

    @Override
    public double getPrice() {
        return 400.0;
    }
}


// Customer class
class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


// Show class
class Show {

    private String movieName;
    private String showTime;
    private boolean started;

    // Stores seats already booked for this show
    private List<Seat> bookedSeats;

    public Show(String movieName, String showTime) {
        this.movieName = movieName;
        this.showTime = showTime;
        this.started = false;
        this.bookedSeats = new ArrayList<>();
    }

    public String getMovieName() {
        return movieName;
    }

    public String getShowTime() {
        return showTime;
    }

    public boolean isStarted() {
        return started;
    }

    public void startShow() {
        started = true;
    }

    // Check whether a seat is already booked
    public boolean isSeatBooked(String seatNumber) {

        for (Seat seat : bookedSeats) {

            if (seat.getSeatNumber().equals(seatNumber)) {
                return true;
            }
        }

        return false;
    }

    // Book a seat
    public boolean bookSeat(Seat seat) {

        if (isSeatBooked(seat.getSeatNumber())) {
            return false;
        }

        bookedSeats.add(seat);
        return true;
    }

    // Release a seat after cancellation
    public void releaseSeat(Seat seat) {
        bookedSeats.removeIf(
                bookedSeat ->
                        bookedSeat.getSeatNumber()
                                .equals(seat.getSeatNumber())
        );
    }
}


// Booking class
class Booking {

    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private boolean cancelled;

    public Booking(Customer customer, Show show) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>();
        this.cancelled = false;
    }

    // Add seats to booking
    public boolean addSeat(Seat seat) {

        // Maximum 6 seats
        if (seats.size() >= 6) {
            System.out.println(
                    "Cannot book more than 6 seats."
            );
            return false;
        }

        // Check whether seat is already booked
        if (!show.bookSeat(seat)) {

            System.out.println(
                    "Seat " + seat.getSeatNumber()
                            + " is already booked for this show."
            );

            return false;
        }

        seats.add(seat);
        return true;
    }

    // Calculate total price
    public double calculateTotal() {

        double total = 0;

        for (Seat seat : seats) {
            total += seat.getPrice();
        }

        return total;
    }

    // Confirm booking
    public void confirmBooking() {

        if (seats.isEmpty()) {
            System.out.println(
                    "Cannot confirm booking: no seats selected."
            );
            return;
        }

        System.out.print(
                "Booking confirmed for "
                        + customer.getName()
                        + ": "
        );

        for (int i = 0; i < seats.size(); i++) {

            System.out.print(
                    seats.get(i).getSeatNumber()
            );

            if (i < seats.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.printf(
                ". Total: ₹%.2f%n",
                calculateTotal()
        );
    }

    // Cancel booking
    public void cancelBooking() {

        // Cannot cancel after show starts
        if (show.isStarted()) {

            System.out.println(
                    "Cannot cancel booking: show has already started."
            );

            return;
        }

        if (cancelled) {
            System.out.println(
                    "Booking is already cancelled."
            );
            return;
        }

        // Release every seat
        for (Seat seat : seats) {
            show.releaseSeat(seat);
        }

        cancelled = true;

        System.out.println(
                customer.getName()
                        + "'s booking cancelled."
        );

        System.out.print("Seats ");

        for (int i = 0; i < seats.size(); i++) {

            System.out.print(
                    seats.get(i).getSeatNumber()
            );

            if (i < seats.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.println(" released.");
    }
}


// Main class
public class CampusPremiereTicketCounter {

    public static void main(String[] args) {

        // Create customers
        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        // Create the 7 PM movie show
        Show show =
                new Show(
                        "Campus Premiere",
                        "7 PM"
                );

        // Create seats
        Seat a1 = new RegularSeat("A1");
        Seat a2 = new RegularSeat("A2");
        Seat f5 = new PremiumSeat("F5");
        Seat r1 = new ReclinerSeat("R1");

        // --------------------------------
        // Asha books A1, A2 and F5
        // --------------------------------

        Booking ashaBooking =
                new Booking(asha, show);

        ashaBooking.addSeat(a1);
        ashaBooking.addSeat(a2);
        ashaBooking.addSeat(f5);

        ashaBooking.confirmBooking();

        // --------------------------------
        // Ravi tries to book A2
        // --------------------------------

        Booking raviBooking =
                new Booking(ravi, show);

        raviBooking.addSeat(a2);

        // --------------------------------
        // Ravi books R1
        // --------------------------------

        raviBooking.addSeat(r1);

        raviBooking.confirmBooking();

        // --------------------------------
        // Asha cancels her booking
        // --------------------------------

        ashaBooking.cancelBooking();

        // --------------------------------
        // Neha books A2
        // A2 is now available again
        // --------------------------------

        Booking nehaBooking =
                new Booking(neha, show);

        nehaBooking.addSeat(a2);

        nehaBooking.confirmBooking();
    }
}