package org.team6083.drive;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * A class for controlling robot drive
 * @author KennHuang
 * @since 0.1.0
 */
public class DifferentialDrive {
    private SpeedController leftMotor1, leftMotor2, rightMotor1, rightMotor2;
    private boolean reverseDrive = false;
    private double speedDown;

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

        speedDown = 2.0;
        dashboard(0,0);
    }

    /**
     * Stop the drive
     */
    public void stop() {
        leftMotor1.set(0);
        rightMotor1.set(0);
        leftMotor2.set(0);
        rightMotor2.set(0);

        dashboard(0,0);
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

        dashboard(leftSpeed,rightSpeed);
    }

    /**
     * Get speed of left drive.
     * @return The set value of left drive.
     */
    public double getLeftPower() {
        return leftMotor1.get();
    }

    /**
     * Get speed of right speed.
     * @return The set value of right drive.
     */
    public double getRightPower() {
        return rightMotor1.get();
    }

    private void dashboard(double l_speed, double r_speed){
        SmartDashboard.putBoolean("drive/reverse", reverseDrive);
        SmartDashboard.putNumber("drive/leftSpeed", l_speed);
        SmartDashboard.putNumber("drive/rightSpeed", r_speed);
        SmartDashboard.putNumber("drive/speedDown", speedDown);
    }
}
