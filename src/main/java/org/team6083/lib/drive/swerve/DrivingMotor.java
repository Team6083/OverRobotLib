package org.team6083.lib.drive.swerve;

import edu.wpi.first.math.geometry.Rotation2d;

public interface DrivingMotor{
    public Rotation2d getAbsRotation();
    public void setVoltage();
    public void setPower();
}