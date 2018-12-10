package org.team6083.drive;

import edu.wpi.first.wpilibj.SpeedController;


/**
 * A class for controlling robot drive
 * @author KennHuang
 * @since 0.1.0
 */
public class DifferentialDrive {
    private SpeedController leftMotor1, leftMotor2, rightMotor1, rightMotor2;

    /**
     * Construct a DifferentialDrive.
     * @param left1 left motor controller 1
     * @param left2 left motor controller 2
     * @param right1 right motor controller 1
     * @param right2 right motor controller 2
     */
    public DifferentialDrive(SpeedController left1, SpeedController left2, SpeedController right1, SpeedController right2) {
        leftMotor1 = left1;
        leftMotor2 = left2;
        rightMotor1 = right1;
        rightMotor2 = right2;
    }

    /**
     * Stop the driv e
     */
    public void stop() {
        leftMotor1.set(0);
        rightMotor1.set(0);
        leftMotor2.set(0);
        rightMotor2.set(0);
    }

    /**
     * Use this to control the drive directly without any limit.
     *
     * @param leftSpeed  the left side speed of the robot
     * @param rightSpeed the right side speed of the robot
     */
    public void directControl(double leftSpeed, double rightSpeed) {
        leftMotor1.set(leftSpeed);
        leftMotor2.set(leftSpeed);
        rightMotor1.set(rightSpeed);
        rightMotor2.set(rightSpeed);
    }


}
