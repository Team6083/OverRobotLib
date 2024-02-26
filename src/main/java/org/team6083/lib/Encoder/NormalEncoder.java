package org.team6083.lib.Encoder;

import com.ctre.phoenix6.StatusSignal;

import edu.wpi.first.math.geometry.Rotation2d;

public class NormalEncoder extends edu.wpi.first.wpilibj.Encoder implements RobotEncoder{
    private double offsetPosition = 0.0;;
    private double encoderPerPulse = 2048.0;
    public NormalEncoder(int DIOID,int DIOID2){
        super(DIOID,DIOID2);
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public double getDriveDisanceDouble() {
        return super.getDistance();
    }

    @Override
    public double getDriveRate() {
        return super.getRate();
    }

    @Override
    public Rotation2d getAbsRotation() {
        return Rotation2d.fromDegrees((super.getDistance()+offsetPosition)%encoderPerPulse);
    }

    public void getOffsetPosition(double offsetPosition){
        this.offsetPosition = offsetPosition;
    }

    public void setEncoderPerPulse(double encoderPerPulse){
        this.encoderPerPulse = encoderPerPulse;
    }
}

