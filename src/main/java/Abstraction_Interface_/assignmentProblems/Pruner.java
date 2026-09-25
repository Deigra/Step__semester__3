package Abstraction_Interface_.assignmentProblems;

public class Pruner extends CuttingTool {
    @Override
    public String use() {
        return super.use() + ", then trimming branches precisely";
    }
}