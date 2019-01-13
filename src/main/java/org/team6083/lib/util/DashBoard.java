package org.team6083.lib.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A class for communicating with OverDashboard.
 *
 * @since 0.1.0-alpha
 */
public class DashBoard {

    private static boolean init = false;
    private static boolean runLoop = true;

    private String partName;

    /**
     * Construct a DashBoard.
     *
     * @param name the name of the part
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
    public void markReady() {
        SmartDashboard.putNumber(partName + "/status", 0);
    }

    /**
     * Mark part warning.
     */
    public void markWarning() {
        SmartDashboard.putNumber(partName + "/status", 1);
    }

    /**
     * Mark part error.
     */
    public void markError() {
        SmartDashboard.putNumber(partName + "/status", 2);
    }

    /**
     * Update left displacement.
     *
     * @param leftDis current displacement of left drive
     */
    public void putLeftDis(double leftDis) {
        SmartDashboard.putNumber("Left Dis", leftDis);
    }

    /**
     * Update right displacement.
     *
     * @param rightDis current displacement of right drive
     */
    public void putRightDis(double rightDis) {
        SmartDashboard.putNumber("Right Dis", rightDis);
    }

    /**
     * @param angle current robot heading
     */
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
