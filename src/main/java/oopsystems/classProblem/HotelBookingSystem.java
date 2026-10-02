package oopsystems.classProblem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


// Customer for Hotel Booking System
class HotelCustomer {

    private String name;

    public HotelCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


// Abstract Room class
abstract class Room {

    private String roomNumber;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    // Each room type calculates its own price
    public abstract double calculatePrice(long numberOfNights);

    public abstract String getRoomType();
}


// Standard Room
class StandardRoom extends Room {

    private final double nightlyRate = 100.0;

    public StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(long numberOfNights) {
        return nightlyRate * numberOfNights;
    }

    @Override
    public String getRoomType() {
        return "Standard Room";
    }
}


// Deluxe Room
class DeluxeRoom extends Room {

    private final double nightlyRate = 150.0;

    public DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(long numberOfNights) {
        return nightlyRate * numberOfNights;
    }

    @Override
    public String getRoomType() {
        return "Deluxe Room";
    }
}


// Suite
class Suite extends Room {

    private final double nightlyRate = 250.0;

    public Suite(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(long numberOfNights) {
        return nightlyRate * numberOfNights;
    }

    @Override
    public String getRoomType() {
        return "Suite";
    }
}


// Reservation
class Reservation {

    private HotelCustomer customer;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;

    public Reservation(
            HotelCustomer customer,
            Room room,
            LocalDate startDate,
            LocalDate endDate) {

        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = true;
    }

    public HotelCustomer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean isActive() {
        return active;
    }

    public long getNumberOfNights() {
        return ChronoUnit.DAYS.between(
                startDate,
                endDate
        );
    }

    public double getPrice() {
        return room.calculatePrice(getNumberOfNights());
    }

    public void cancel() {
        active = false;
    }
}


// Main class
public class HotelBookingSystem {

    // Store all reservations
    private static List<Reservation> reservations =
            new ArrayList<>();


    // Check whether a room is available
    public static boolean isRoomAvailable(
            Room room,
            LocalDate startDate,
            LocalDate endDate) {

        for (Reservation reservation : reservations) {

            // Ignore cancelled reservations
            if (!reservation.isActive()) {
                continue;
            }

            // Only compare reservations for the same room
            if (!reservation.getRoom()
                    .getRoomNumber()
                    .equals(room.getRoomNumber())) {
                continue;
            }

            // Check for overlapping dates
            boolean overlaps =
                    startDate.isBefore(
                            reservation.getEndDate()
                    )
                            &&
                            endDate.isAfter(
                                    reservation.getStartDate()
                            );

            if (overlaps) {
                return false;
            }
        }

        return true;
    }


    // Make a reservation
    public static Reservation makeReservation(
            HotelCustomer customer,
            Room room,
            LocalDate startDate,
            LocalDate endDate) {

        if (!endDate.isAfter(startDate)) {

            System.out.println(
                    "Invalid reservation dates."
            );

            return null;
        }

        if (!isRoomAvailable(
                room,
                startDate,
                endDate)) {

            System.out.println(
                    room.getRoomType()
                            + " "
                            + room.getRoomNumber()
                            + " is not available from "
                            + formatDate(startDate)
                            + " to "
                            + formatDate(endDate)
                            + "."
            );

            return null;
        }

        Reservation reservation =
                new Reservation(
                        customer,
                        room,
                        startDate,
                        endDate
                );

        reservations.add(reservation);

        System.out.println(
                "Reservation confirmed for "
                        + customer.getName()
                        + ", "
                        + room.getRoomType()
                        + " "
                        + room.getRoomNumber()
                        + " ("
                        + formatDate(startDate)
                        + "-"
                        + formatDate(endDate)
                        + ")."
        );

        System.out.printf(
                "Price: $%.2f%n",
                reservation.getPrice()
        );

        return reservation;
    }


    // Cancel reservation
    // Example policy: cancellation allowed
    // at least 1 day before check-in.
    public static void cancelReservation(
            Reservation reservation,
            LocalDate cancellationDate) {

        if (!reservation.isActive()) {

            System.out.println(
                    "Reservation is already cancelled."
            );

            return;
        }

        long daysBeforeStart =
                ChronoUnit.DAYS.between(
                        cancellationDate,
                        reservation.getStartDate()
                );

        if (daysBeforeStart < 1) {

            System.out.println(
                    "Reservation cannot be cancelled: "
                            + "cancellation deadline has passed."
            );

            return;
        }

        reservation.cancel();

        System.out.println(
                "Reservation for "
                        + reservation.getCustomer().getName()
                        + ", "
                        + reservation.getRoom().getRoomType()
                        + " "
                        + reservation.getRoom().getRoomNumber()
                        + " ("
                        + formatDate(
                        reservation.getStartDate()
                )
                        + "-"
                        + formatDate(
                        reservation.getEndDate()
                )
                        + ") cancelled successfully."
        );
    }


    // Format dates for output
    private static String formatDate(
            LocalDate date) {

        return date.getMonth()
                .toString()
                .substring(0, 1)
                + date.getMonth()
                .toString()
                .substring(1, 3)
                .toLowerCase()
                + " "
                + date.getDayOfMonth();
    }


    // Main method
    public static void main(String[] args) {

        // Customers
        HotelCustomer customerA =
                new HotelCustomer("Customer A");

        HotelCustomer customerB =
                new HotelCustomer("Customer B");

        HotelCustomer customerC =
                new HotelCustomer("Customer C");


        // Rooms
        Room standard101 =
                new StandardRoom("101");

        Room deluxe201 =
                new DeluxeRoom("201");


        // Dates
        LocalDate jan1 =
                LocalDate.of(2026, 1, 1);

        LocalDate jan5 =
                LocalDate.of(2026, 1, 5);

        LocalDate jan3 =
                LocalDate.of(2026, 1, 3);

        LocalDate jan7 =
                LocalDate.of(2026, 1, 7);

        LocalDate feb10 =
                LocalDate.of(2026, 2, 10);

        LocalDate feb12 =
                LocalDate.of(2026, 2, 12);


        // Customer A checks availability
        if (isRoomAvailable(
                standard101,
                jan1,
                jan5)) {

            System.out.println(
                    "Standard Room 101 is available "
                            + "from Jan 1 to Jan 5."
            );
        }


        // Customer A reserves Room 101
        Reservation reservationA =
                makeReservation(
                        customerA,
                        standard101,
                        jan1,
                        jan5
                );


        // Customer B tries overlapping reservation
        makeReservation(
                customerB,
                standard101,
                jan3,
                jan7
        );


        // Customer A cancels before deadline
        if (reservationA != null) {

            cancelReservation(
                    reservationA,
                    LocalDate.of(2025, 12, 30)
            );
        }


        // Customer C reserves Deluxe Room 201
        makeReservation(
                customerC,
                deluxe201,
                feb10,
                feb12
        );
    }
}