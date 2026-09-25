package Abstraction_Interface_.classProblem;

public abstract class DeliveryNote {

    public abstract String confirmDelivery();

    public String confirmDelivery(String signature) {
        return confirmDelivery() + ", signed by " + signature;
    }
}