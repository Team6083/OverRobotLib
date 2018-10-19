package org.team6083.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashBoard {

    private static boolean init = false;
    private static boolean runLoop = true;

    public void init() {
        if (!init) {
            new Thread(() -> {
                Thread.currentThread().setName("DashboardLooper");
                while (runLoop) {
                    dashboardLoop();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            init = true;
        }
    }

    public static void putReady(String name) {
        SmartDashboard.putNumber(name + "/status", 0);
    }

    public static void putWarning(String name) {
        SmartDashboard.putNumber(name + "/status", 1);
    }

    public static void putError(String name) {
        SmartDashboard.putNumber(name + "/status", 2);
    }

    private static void dashboardLoop() {
        DriverStation ds = DriverStation.getInstance();

        SmartDashboard.putBoolean("ds/isFMSAtt", ds.isFMSAttached());
        SmartDashboard.putNumber("ds/matchTime", ds.getMatchTime());

        int mode;
        if (ds.isDisabled()) {
            mode = 0;
        } else if (ds.isOperatorControl()) {
            mode = 2;
        } else if (ds.isTest()) {
            mode = 3;
        } else if (ds.isAutonomous()) {
            mode = 1;
        } else {
            mode = -1;
        }

        SmartDashboard.putNumber("ds/mode", mode);

    }
}
