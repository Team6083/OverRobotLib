package org.team6083.lib.Gyro;

import edu.wpi.first.wpilibj.SPI.Port;

public class NavXMXP extends com.kauailabs.navx.frc.AHRS implements DriveGyro {
    public NavXMXP() {
        super(Port.kMXP);
    }

    @Override
    public double getHeading() {
        return super.getAngle();
    }

    @Override
    public void reset() {
        super.reset();
    }

}
