package oopsystems.classProblem;

// Abstract Vehicle class
abstract class Vehicle {

    private String vehicleId;
    private boolean available;

    public Vehicle(String vehicleId) {
        this.vehicleId = vehicleId;
        this.available = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Each vehicle type calculates its own rental charge
    public abstract double calculateRentalCharge(int days);

    public abstract String getVehicleType();
}


// Sedan
class Sedan extends Vehicle {

    private final double dailyRate = 50.0;

    public Sedan(String vehicleId) {
        super(vehicleId);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return dailyRate * days;
    }

    @Override
    public String getVehicleType() {
        return "Sedan";
    }
}


// SUV
class SUV extends Vehicle {

    private final double dailyRate = 70.0;

    public SUV(String vehicleId) {
        super(vehicleId);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return dailyRate * days;
    }

    @Override
    public String getVehicleType() {
        return "SUV";
    }
}


// Truck
class Truck extends Vehicle {

    private final double dailyRate = 90.0;

    public Truck(String vehicleId) {
        super(vehicleId);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return dailyRate * days;
    }

    @Override
    public String getVehicleType() {
        return "Truck";
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


// Rental class
class Rental {

    private Vehicle vehicle;
    private Customer customer;
    private int days;
    private boolean active;

    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        this.active = true;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalAmount() {
        return vehicle.calculateRentalCharge(days);
    }

    public boolean isActive() {
        return active;
    }

    public void completeRental() {
        active = false;
        vehicle.setAvailable(true);
    }
}


// Main class
public class VehicleRentalSystem {

    public static void main(String[] args) {

        // Create customers
        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");
        Customer customer3 = new Customer("Customer 3");


        // Create vehicles
        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");


        // Customer 1 rents Sedan A for 3 days
        Rental rental1 = rentVehicle(
                customer1,
                sedanA,
                3
        );


        // Customer 2 tries to rent the same Sedan A
        Rental rental2 = rentVehicle(
                customer2,
                sedanA,
                2
        );


        // Customer 1 returns Sedan A
        if (rental1 != null) {
            returnVehicle(rental1);
        }


        // Customer 3 rents SUV B for 5 days
        Rental rental3 = rentVehicle(
                customer3,
                suvB,
                5
        );
    }


    // Common rental processing logic
    public static Rental rentVehicle(
            Customer customer,
            Vehicle vehicle,
            int days) {

        if (!vehicle.isAvailable()) {

            System.out.println(
                    vehicle.getVehicleType()
                            + " "
                            + vehicle.getVehicleId()
                            + " is currently unavailable."
            );

            return null;
        }

        if (days <= 0) {

            System.out.println(
                    "Rental duration must be greater than 0 days."
            );

            return null;
        }

        // Mark vehicle as unavailable
        vehicle.setAvailable(false);

        // Create rental
        Rental rental =
                new Rental(vehicle, customer, days);

        System.out.println(
                vehicle.getVehicleType()
                        + " "
                        + vehicle.getVehicleId()
                        + " rented successfully by "
                        + customer.getName()
                        + "."
        );

        System.out.printf(
                "Rental charge: $%.2f%n",
                rental.getTotalAmount()
        );

        return rental;
    }


    // Return vehicle
    public static void returnVehicle(Rental rental) {

        if (!rental.isActive()) {

            System.out.println(
                    "This rental has already been completed."
            );

            return;
        }

        Vehicle vehicle = rental.getVehicle();

        rental.completeRental();

        System.out.println(
                vehicle.getVehicleType()
                        + " "
                        + vehicle.getVehicleId()
                        + " returned by "
                        + rental.getCustomer().getName()
                        + "."
        );
    }
}