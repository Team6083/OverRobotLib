package org.team6083.lib.Gyro;

import org.team6083.lib.drive.DriveGyro;

import edu.wpi.first.wpilibj.SPI.Port;

public class NavXMXP extends com.kauailabs.navx.frc.AHRS implements DriveGyro {
    public NavXMXP() {
        super(Port.kMXP);
    }

    @Override
    public double getHeading() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHeading'");
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }

}
