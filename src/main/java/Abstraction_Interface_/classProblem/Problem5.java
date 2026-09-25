package Abstraction_Interface_.classProblem;

public class Problem5 {

    public static void main(String[] args) {

        ParcelNote p = new ParcelNote("TRK-1");

        System.out.println(p.confirmDelivery());

        System.out.println(p.confirmDelivery("J. Smith"));

        DeliveryNote ref = p;

        logAll(new DeliveryNote[]{
                ref,
                new LetterNote("TRK-2")
        });
    }

    public static void logAll(DeliveryNote[] notes) {

        for (DeliveryNote note : notes) {
            System.out.println(note.confirmDelivery());
        }
    }
}