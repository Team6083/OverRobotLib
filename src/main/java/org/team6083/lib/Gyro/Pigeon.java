package org.team6083.lib.gyro;

import com.ctre.phoenix6.hardware.Pigeon2;

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
