package org.team6083.lib.motor;


public class MotorVictorSP extends edu.wpi.first.wpilibj.motorcontrol.VictorSP{
    public MotorVictorSP(int deviceId){
       super(deviceId);
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
        super.set(0);
    }

    @Override
    public void stopMotor() {
        super.set(0);
    }

    @Override
    public void set(double speed) {
        super.set(speed);
    }

}
