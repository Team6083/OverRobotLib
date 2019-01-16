package org.team6083.lib.drive;

import org.team6083.lib.auto.EncoderWalker;
import org.team6083.lib.auto.GyroWalker;
import org.team6083.lib.drive.inputs.TankInput;

public class DriveAssistant {
    private GyroWalker gyroWalker;
    private EncoderWalker encoderWalker;
    private DifferentialDrive drive;
    private TankInput tankInput;

    private boolean headingLock = false;
    private double targetHeading = 0;

    public DriveAssistant(GyroWalker gyroWalker, EncoderWalker encoderWalker, DifferentialDrive differentialDrive, TankInput input) {
        this.gyroWalker = gyroWalker;
        this.encoderWalker = encoderWalker;
        this.drive = differentialDrive;
        this.tankInput = input;
    }

    public void teleopLoop() {
        double left = drive.calculateTankSpeed(tankInput, TankInput.Hand.LEFT);
        double right = drive.calculateTankSpeed(tankInput, TankInput.Hand.RIGHT);

        if (headingLock) {
            gyroWalker.calculate(left, right);
            gyroWalker.setTargetAngle(targetHeading);

            left = gyroWalker.getLeftPower();
            right = gyroWalker.getRightPower();
        }

        drive.directControl(left, right);
    }
}
