public class Main {

    public static void main(String[] args) {
        Joysticks j = new Joysticks();
        System.out.println("Drive Straight Test");
        j.demonstrate_driving_straight();

        System.out.println(" ");
        System.out.println("Normalize Joystick Input Test");
        j.demonstrate_maximum_joystick_power_selection();

    }
}
