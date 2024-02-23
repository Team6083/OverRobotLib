package org.team6083.lib.Gyro;

import org.team6083.lib.drive.DriveGyro;

public class Pigeon extends com.ctre.phoenix6.hardware.Pigeon2 implements DriveGyro  {
    public Pigeon(int deviceId) {
        super(deviceId);
        //TODO Auto-generated constructor stub
    }

    @Override
    public double getHeading() {
        Pigeon.getHeading();
    }

    @Override
    public void reset() {
        Pigeon.reset();
    }
    
}
