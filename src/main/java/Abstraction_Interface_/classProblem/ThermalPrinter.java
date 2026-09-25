package Abstraction_Interface_.classProblem;

public class ThermalPrinter implements LabelPrinter {

    @Override
    public void printLabel(String text) {
        System.out.println(text);
    }
}