package org.team6083.lib.motor;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class MotorVictorSPX extends com.ctre.phoenix.motorcontrol.can.VictorSPX implements MotorController{
    public MotorVictorSPX(int deviceId){
        super(deviceId);
    }

    @Override
    public double get() {
        return super.getMotorOutputPercent();
    }

    @Override
    public void setInverted(boolean isInverted) {
        super.setInverted(isInverted);
    }

    @Override
    public boolean getInverted() {
        return super.getInverted();
    }

    @Override
    public void disable() {
        super.set(VictorSPXControlMode.PercentOutput,0);
    }

    @Override
    public void stopMotor() {
        super.set(VictorSPXControlMode.PercentOutput,0);
    }

    @Override
    public void set(double speed) {
        super.set(VictorSPXControlMode.PercentOutput,speed);
    }
}
