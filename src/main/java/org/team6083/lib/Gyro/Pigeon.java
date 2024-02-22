package org.team6083.lib.Gyro;

import org.team6083.lib.drive.DriveGyro;

import com.ctre.phoenix6.hardware.Pigeon2;

public class Pigeon extends Pigeon2 implements DriveGyro  {
public Pigeon pigeon= new Pigeon();
    @Override
    public double getHeading() {
        pigeon.getHeading();
        throw new UnsupportedOperationException("Unimplemented method 'getHeading'");
    }

    @Override
    public void reset() {

        pigeon.reset();
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }
    
}
