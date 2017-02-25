
public class Joysticks {

    private double jv[] = { -1, -0.5, -0.04, 0.0, 0.04, 0.5, 1 };

    public double determine_max_joystick(double joy1, double joy2) {
        double deadzone_factor = 0.05;
        // manual deadzone setting
        if (Math.abs(joy1) < deadzone_factor) {
            joy1 = 0.0;
        }
        if (Math.abs(joy2) < deadzone_factor) {
            joy2 = 0.0;
        }

        // if joystick1 does not have same negative status as joystick2
        if ((joy1 < 0 && joy2 > 0) || (joy1 > 0 && joy2 < 0)) {
            return 0.0; // do nothing, really conflicting input.
        }

        // return greater magnitude
        if (Math.abs(joy1) > Math.abs(joy2)) {
            return joy1;
        } else {
            return joy2;
        }
    }

    public void straight(double gyro, double power) {
        // Leading and trailing motors, will be converted to L and R
        double leading = 0, trailing = 0;
        double leftM = 0, rightM = 0;
        // if needed, we can ignore changes based on a deadband
        // double deadband = 0.5;

        // Maximum off-axis of the robot
        double maxangle = 30;

        // Absolute value of gyro rotation
        double absgyro = Math.abs(gyro);

        // amount of power to subtract
        double subtractedPower = absgyro / maxangle;
        // can't have negative power
        if (subtractedPower > 1) {
            subtractedPower = 1;
        }

        // trailing gets max power
        trailing = power;
        // leading gets a percentage of max power
        leading = power * (1 - subtractedPower);

        // assume negative is left and positive is right IF we are moving forwards
        if (gyro < 0 && power > 0) {
            leftM = trailing;
            rightM = leading;
        } else {
            rightM = trailing;
            leftM = leading;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Gyro:").append(gyro).append("|Power:").append(power);
        sb.append(" -> ");
        sb.append("Left:").append(leftM).append("|Right:").append(rightM);
        System.out.println(sb.toString());
    }

    public void straightTest() {
        for (double j = -1; j <= 1; j += 0.5) {
            for (int i = -45; i <= 45; i += 15) {

                straight(i, j);
            }
        }
    }

    public void maxTest() {
        for (int i = 0; i < jv.length; i++) {
            for (int j = 0; j < jv.length; j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(jv[i]);
                sb.append(" | ");
                sb.append(jv[j]);
                sb.append(" -> ");
                sb.append(determine_max_joystick(jv[i], jv[j]));
                System.out.println(sb.toString());
            }
        }

    }
}
