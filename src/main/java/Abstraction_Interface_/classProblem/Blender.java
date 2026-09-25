package Abstraction_Interface_.classProblem;

public class Blender extends KitchenTool implements Washable {

    @Override
    public String prepare() {
        return "Blending at speed " + getSpeedLevel();
    }

    @Override
    public String clean() {
        return "Blender rinsed and dried";
    }
}