package org.team6083.lib.Gyro;

import org.team6083.lib.drive.DriveGyro;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class NavXMXP implements DriveGyro{
    AHRS gyro = new AHRS(SPI.Port.kMXP);
    @Override
    public double getHeading() {
        // TODO Auto-generated method stub
        gyro.getAngle();
        throw new UnsupportedOperationException("Unimplemented method 'getHeading'");
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        gyro.reset();
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }
    
}
