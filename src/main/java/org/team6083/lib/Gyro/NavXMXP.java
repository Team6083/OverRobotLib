package org.team6083.lib.Gyro;

import org.team6083.lib.drive.DriveGyro;

import edu.wpi.first.wpilibj.SPI.Port;

public class NavXMXP extends com.kauailabs.navx.frc.AHRS implements DriveGyro {
    public NavXMXP() {
        super(Port.kMXP);
    }

    @Override
    public double getHeading() {
        super.getHeading();
    }

    @Override
    public void reset() {
        super.reset();
    }

}
