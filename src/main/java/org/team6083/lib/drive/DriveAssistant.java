package org.team6083.lib.drive;

import org.team6083.lib.auto.GyroWalker;
import org.team6083.lib.commands.differential.TankDriveInput;

/**
 * A class for controlling the drive base with some basic assistant feature.
 *
 * @author KennHuang
 * @since 0.1.0-alpha-4
 */
public class DriveAssistant {
    private GyroWalker gyroWalker;
    private DifferentialDrive drive;
    private TankDriveInput tankInput;

    private boolean headingLock = false;
    private double targetHeading = 0;

    /**
     * Constructor of DriveAssistant
     *
     * @param gyroWalker        {@link org.team6083.lib.auto.GyroWalker} instance that used to assistant the driver
     * @param differentialDrive Drive system
     * @param input             the controller that used to control the robot
     */
    public DriveAssistant(GyroWalker gyroWalker, DifferentialDrive differentialDrive, TankDriveInput input) {
        this.gyroWalker = gyroWalker;
        this.drive = differentialDrive;
        this.tankInput = input;
    }

    /**
     * Call this periodically to use DriveAssistant.
     */
    public void teleopLoop() {
        double left = drive.calculateTankSpeed(tankInput, DifferentialDrive.Side.kLeft);
        double right = drive.calculateTankSpeed(tankInput, DifferentialDrive.Side.kRight);

        if (headingLock) {
            gyroWalker.calculate(left, right);
            gyroWalker.setTargetAngle(targetHeading);

            left = gyroWalker.getLeftPower();
            right = gyroWalker.getRightPower();
        }

        drive.directControl(left, right);
    }
}
