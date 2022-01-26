package org.team6083.lib;

import edu.wpi.first.wpilibj.PowerDistribution;

public class RobotPower {
    private static PowerDistribution pdp;
    private static int PDP_CANID = 1;
    private static boolean inited = false;

    private int devicePort;

    public static void init(int pdp_CANId) {
        if(!inited) {
            PDP_CANID = pdp_CANId;
            pdp = new PowerDistribution(PDP_CANID, PowerDistribution.ModuleType.kCTRE);
        }
    }

    public RobotPower(int port) {
        devicePort = port;
    }

    public double getPortCurrent() {
        return pdp.getCurrent(devicePort);
    }

    public static double getTotalCurrent() {
        return pdp.getTotalCurrent();
    }

    public static double getRobotVoltage() {
        return pdp.getVoltage();
    }

}
