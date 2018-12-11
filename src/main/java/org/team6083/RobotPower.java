package org.team6083;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import org.team6083.util.DashBoard;

public class RobotPower {
    private static PowerDistributionPanel pdp;
    private static int PDP_CANID = 1;
    private static boolean inited = false;

    private int devicePort;

    public static void init(int pdp_CANId) {
        if(!inited) {
            PDP_CANID = pdp_CANId;
            pdp = new PowerDistributionPanel(PDP_CANID);
        }
    }

    public RobotPower(int port) {
        devicePort = port;
    }

    public double getPortCurrent() {
        return pdp.getCurrent(devicePort);
    }

    public static double getTotalVoltage() {
        return pdp.getVoltage();
    }

    public static double getTotalCurrent() {
        return pdp.getTotalCurrent();
    }

    public static double getRobotVoltage() {
        return pdp.getVoltage();
    }

}
