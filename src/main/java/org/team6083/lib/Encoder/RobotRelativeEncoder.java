package org.team6083.lib.Encoder;

import edu.wpi.first.math.geometry.Rotation2d;

public class RobotRelativeEncoder extends com.revrobotics.RelativeEncoder implements RobotEncoder{
    public RobotRelativeEncoder(){
    super();
    }
    @Override
    public void reset() {
        super.setPosition();
    }

    @Override
    public double getDriveDisance() {
        super.getDriveDisance();
    }

    @Override
    public double getDriveRate() {
        super.getDriveRate();
    }

    @Override
    public Rotation2d getAbsRotation() {
        super.getAbsolutePosition();
    }
    
}
