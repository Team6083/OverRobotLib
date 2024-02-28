package org.team6083.lib.motor;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class MotorSparkMax extends com.revrobotics.CANSparkMax implements MotorController{
    public MotorSparkMax(int deviceId){
        super(deviceId, MotorType.kBrushless);
    }

    @Override
    public double get() {
        return super.get();
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
        super.disable();
    }

    @Override
    public void stopMotor() {
        super.stopMotor();
    }

    @Override
    public void set(double speed) {
        super.set(speed);
    }
}
