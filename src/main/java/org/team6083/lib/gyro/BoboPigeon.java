package org.team6083.lib.gyro;

public class BoboPigeon extends com.ctre.phoenix6.hardware.Pigeon2 implements DriveGyro  {
    public BoboPigeon(int deviceId) {
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
