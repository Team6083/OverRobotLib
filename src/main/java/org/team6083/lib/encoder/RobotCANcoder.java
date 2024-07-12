package org.team6083.lib.encoder;


import edu.wpi.first.math.geometry.Rotation2d;

public class RobotCANcoder extends com.ctre.phoenix6.hardware.CANcoder implements RobotEncoder {
    public RobotCANcoder(int Channel) {
        super(Channel);
    }

    @Override
    public void reset() {
        super.setPosition(0);
    }

    @Override
    public double getDriveRate() {
        return super.getPosition().getValueAsDouble();
    }

    @Override
    public Rotation2d getAbsRotation() {
        return new Rotation2d(super.getAbsolutePosition().getValue() * 360.0);
    }


    @Override
    public double getDriveDistanceDouble() {
        return super.getPosition().getValueAsDouble();
    }

}
