package org.team6083.lib.Gyro;

import org.team6083.lib.drive.DriveGyro;

import com.ctre.phoenix6.hardware.Pigeon2;

public class Pigeon extends Pigeon2 implements DriveGyro  {
    public Pigeon(int deviceId) {
        super(deviceId);
        //TODO Auto-generated constructor stub
    }

    @Override
    public double getHeading() {
        throw new UnsupportedOperationException("Unimplemented method 'getHeading'");
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }
    
}
