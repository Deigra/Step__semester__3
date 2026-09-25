package Abstraction_Interface_.classProblem;

class Problem1 {

    public static void main(String[] args) {

        ToyCar c = new ToyCar("Speedster");

        System.out.println(c.makeSound());

        ToyRobot r = new ToyRobot("Bolt");

        System.out.println(r.makeSound());

        System.out.println(c.getToyId());
        System.out.println(r.getToyId());
    }
}