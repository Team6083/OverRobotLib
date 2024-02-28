package org.team6083.lib.encoder;

import org.team6083.lib.motor.BoboMotorSparkMax;

import edu.wpi.first.math.geometry.Rotation2d;

public class RobotRelativeEncoder implements com.revrobotics.RelativeEncoder, RobotEncoder {
    
    private final double offsetPosition = 0;
    private final double encoderPerPulse = 2048.0;

    public RobotRelativeEncoder(){
        super();
    }
    @Override
    public void reset() {
        super.setPosition(0);
    }

    @Override
    public double getDriveDistanceDouble() {
        return super.getPositon();
    }

    @Override
    public double getDriveRate() {
        return super.getPosition();
    }

    @Override
    public Rotation2d getAbsRotation() {
       return Rotation2d.fromDegrees((super.getPosition()+offsetPosition)%encoderPerPulse);
    }

    
}

