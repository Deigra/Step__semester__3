package oopsystems.classProblem;

import java.util.ArrayList;
import java.util.List;


// Customer
class PaymentCustomer {

    private String name;

    public PaymentCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


// Product
class Product {

    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}


// Order Item
class OrderItem {

    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}


// Payment Method interface
interface PaymentMethod {

    boolean processPayment(double amount);
}


// Credit Card Payment
class CreditCardPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {

        System.out.printf(
                "Credit Card payment processing: $%.2f%n",
                amount
        );

        return true;
    }
}


// PayPal Payment
class PayPalPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {

        System.out.printf(
                "PayPal payment processing: $%.2f%n",
                amount
        );

        // Simulate failed PayPal payment
        return false;
    }
}


// Bank Transfer Payment
class BankTransferPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {

        System.out.printf(
                "Bank Transfer payment processing: $%.2f%n",
                amount
        );

        return true;
    }
}


// Order
class PaymentOrder {

    private String orderId;
    private PaymentCustomer customer;
    private List<OrderItem> items;
    private String status;

    public PaymentOrder(
            String orderId,
            PaymentCustomer customer) {

        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = "Pending";
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    // Add product to order
    public void addProduct(
            Product product,
            int quantity) {

        if (quantity <= 0) {

            System.out.println(
                    "Quantity must be greater than 0."
            );

            return;
        }

        items.add(
                new OrderItem(product, quantity)
        );
    }

    // Calculate total order amount
    public double calculateTotal() {

        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    // Process payment
    public void pay(PaymentMethod paymentMethod) {

        // Order must contain at least one item
        if (items.isEmpty()) {

            System.out.println(
                    "Cannot process payment for an empty order."
            );

            return;
        }

        System.out.println(
                "Payment initiated via "
                        + paymentMethod.getClass()
                        .getSimpleName()
                        + " for Order "
                        + orderId
                        + "."
        );

        boolean paymentSuccessful =
                paymentMethod.processPayment(
                        calculateTotal()
                );

        if (paymentSuccessful) {

            status = "Paid";

            System.out.println(
                    "Payment for Order "
                            + orderId
                            + " successful."
            );

        } else {

            // Status remains Pending
            System.out.println(
                    "Payment for Order "
                            + orderId
                            + " failed."
            );
        }

        System.out.println(
                "Order status: "
                        + status
                        + "."
        );
    }
}


// Main class
public class PaymentProcessingSystem {

    public static void main(String[] args) {

        // Products
        Product productA =
                new Product("Product A", 100);

        Product productB =
                new Product("Product B", 50);

        Product productC =
                new Product("Product C", 200);


        // Customer X
        PaymentCustomer customerX =
                new PaymentCustomer("Customer X");

        PaymentOrder orderX =
                new PaymentOrder(
                        "X",
                        customerX
                );

        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);

        System.out.println(
                "Order created for "
                        + customerX.getName()
                        + "."
        );

        orderX.pay(
                new CreditCardPayment()
        );


        System.out.println();


        // Customer Y creates an empty order
        PaymentCustomer customerY =
                new PaymentCustomer("Customer Y");

        PaymentOrder orderY =
                new PaymentOrder(
                        "Y",
                        customerY
                );

        System.out.println(
                "Order created for "
                        + customerY.getName()
                        + "."
        );

        orderY.pay(
                new CreditCardPayment()
        );


        System.out.println();


        // Customer Z
        PaymentCustomer customerZ =
                new PaymentCustomer("Customer Z");

        PaymentOrder orderZ =
                new PaymentOrder(
                        "Z",
                        customerZ
                );

        orderZ.addProduct(productC, 1);

        System.out.println(
                "Order created for "
                        + customerZ.getName()
                        + "."
        );

        orderZ.pay(
                new PayPalPayment()
        );
    }
}