package org.team6083.lib.encoder;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.geometry.Rotation2d;

public class RobotRelativeEncoder implements RobotEncoder {
    
    private final double offsetPosition = 0;
    private final double encoderPerPulse = 2048.0;
    private final RelativeEncoder relativeEncoder;
    public RobotRelativeEncoder(RelativeEncoder relativeEncoder){
        this.relativeEncoder = relativeEncoder;
    }
    @Override
    public void reset() {
        relativeEncoder.setPosition(0);
    }

    @Override
    public double getDriveDistanceDouble() {
        return relativeEncoder.getPosition();
    }

    @Override
    public double getDriveRate() {
        return relativeEncoder.getPosition();
    }

    @Override
    public Rotation2d getAbsRotation() {
       return Rotation2d.fromDegrees((relativeEncoder.getPosition()+offsetPosition)%encoderPerPulse);
    }
    
    
}

