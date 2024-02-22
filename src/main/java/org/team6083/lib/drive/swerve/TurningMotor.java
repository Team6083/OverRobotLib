package org.team6083.lib.drive.swerve;

public interface TurningMotor {
    public void setPIDControl(double measurement);
    public void setPIDControl(double measurement, double setPoint);
    public void setSetPoint(double setPoint);
    public void setPower();
    public void setVoltage();
}
