package org.team6083.lib.Gyro;

public class Pigeon extends com.ctre.phoenix6.hardware.Pigeon2 implements DriveGyro  {
    public Pigeon(int deviceId) {
        super(deviceId);
        //TODO Auto-generated constructor stub
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
