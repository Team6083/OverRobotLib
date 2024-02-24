package org.team6083.lib.Encoder;

import edu.wpi.first.math.geometry.Rotation2d;

public class SPXMagEncoder extends implements RobotEncoder{
    public SPXMagEncoder(){
        super.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);;

    }
    
    @Override
    public void reset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }

    @Override
    public double getDriveDisance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDriveDisance'");
    }

    @Override
    public double getDriveRate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDriveRate'");
    }

    @Override
    public Rotation2d getAbsRotation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAbsRotation'");
    }
    
}
