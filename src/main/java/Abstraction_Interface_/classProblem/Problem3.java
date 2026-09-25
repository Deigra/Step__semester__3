package Abstraction_Interface_.classProblem;

public class Problem3 {

    public static void main(String[] args) {

        StringInstrument s = new StringInstrument();

        System.out.println(s.play());

        Violin v = new Violin();

        System.out.println(v.play());
    }
}