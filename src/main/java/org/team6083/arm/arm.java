package org.team6083.arm;

import org.team6083.lib.encoder.RobotEncoder;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class arm {
    protected final MotorController upMotor;
    protected final MotorController downMotor;
    protected final MotorController rotateMotor;
    protected final RobotEncoder upEncoder;
    protected final RobotEncoder downEncoder;
    protected final RobotEncoder rotateEncoder;

    public arm(MotorController upMotor, MotorController downMotor, MotorController rotateMotor,
            double powerDistirbution, RobotEncoder upEncoder, RobotEncoder downEncoder, RobotEncoder rotateEncoder) {
        this.upMotor = upMotor;
        this.downMotor = downMotor;
        this.rotateMotor = rotateMotor;
        this.upEncoder = upEncoder;
        this.downEncoder = downEncoder;
        this.rotateEncoder = rotateEncoder;
    }

    protected void setVoltage(double upMotorVoltage, double downMotorVoltage) {
        upMotor.setVoltage(upMotorVoltage);
        downMotor.setVoltage(downMotorVoltage);
    }

    protected void setVoltage(double rotateMotorVoltage) {
        rotateMotor.setVoltage(rotateMotorVoltage);
    }

    protected double getRotateAngle(boolean rotateEncoderInverted, double rotateOffset){
        double degree = (rotateEncoderInverted ? -1.0 : 1.0)
            *((rotateEncoder.getDriveRate()*360.0) - rotateOffset);
            return degree;
    }

    protected void resetEncoder(){
        upEncoder.reset();
        downEncoder.reset();
    }
    protected double getUpEncoderRate(){
        return upEncoder.getDriveRate();
    }

}
