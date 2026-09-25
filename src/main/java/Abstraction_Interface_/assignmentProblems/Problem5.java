package Abstraction_Interface_.assignmentProblems;

public class Problem5 {
    public static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            Trackable trackableObj = (Trackable) o;
            return trackableObj.getLocation();
        } else {
            return "Tracking not available";
        }
    }

    public static void main(String[] args) {
        DeliveryDrone d = new DeliveryDrone("DR-1");
        ScoutDrone s = new ScoutDrone("SC-1");
        GroundRobot g = new GroundRobot("GR-1");

        System.out.println(getLocationIfTrackable(d));
        System.out.println(getLocationIfTrackable(s));
        System.out.println(getLocationIfTrackable(g));
    }
}