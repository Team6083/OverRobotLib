package org.team6083.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashBoard {

    private static boolean init = false;
    private static boolean runLoop = true;

    private String partName;

    /**
     * Construct a DashBoard.
     *
     * @param name part name
     */
    public DashBoard(String name) {
        partName = name;
    }

    /**
     * Initialize DashBoard, only need to run on time once the robot start.
     */
    public static void init() {
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

    /**
     * Use to stop the DashBoard loop.
     */
    public static void stopLoop() {
        runLoop = false;
    }

    /**
     * Mark part ready.
     */
    public void putReady() {
        SmartDashboard.putNumber(partName + "/status", 0);
    }

    /**
     * Mark part warning.
     */
    public void putWarning() {
        SmartDashboard.putNumber(partName + "/status", 1);
    }

    /**
     * Mark part error.
     */
    public void putError() {
        SmartDashboard.putNumber(partName + "/status", 2);
    }

    public void putLeftDis(double leftDis) {
        SmartDashboard.putNumber("Left Dis", leftDis);
    }

    public void putRightDis(double rightDis) {
        SmartDashboard.putNumber("Right Dis", rightDis);
    }

    public static void putGyroAngle(double angle) {
        SmartDashboard.putNumber("Gyro/angle", angle);
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
