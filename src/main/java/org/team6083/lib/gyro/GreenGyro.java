package org.team6083.lib.gyro;

public class GreenGyro extends edu.wpi.first.wpilibj.ADXRS450_Gyro implements DriveGyro {
    public GreenGyro(){
        super();
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
