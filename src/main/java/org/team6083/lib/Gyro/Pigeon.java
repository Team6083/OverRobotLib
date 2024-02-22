package org.team6083.lib.Gyro;

import org.team6083.lib.drive.DriveGyro;

public class Pigeon extends Pigeon2 implements DriveGyro  {
public Pigeon pigeon= new Pigeon();
    @Override
    public double getHeading() {
        // TODO Auto-generated method stub
        pigeon.getHeading();
        throw new UnsupportedOperationException("Unimplemented method 'getHeading'");
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        pigeon.reset();
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }
    
}
