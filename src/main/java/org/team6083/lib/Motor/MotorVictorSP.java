package org.team6083.lib.Motor;

public class MotorVictorSP extends edu.wpi.first.wpilibj.motorcontrol.VictorSP implements Motor{
    public MotorVictorSP(int deviceId){
       super(deviceId);
    }
   
    @Override
    public void set(double speed){
        super.set(speed);
    }
    @Override
    public void stop(){
        super.set(0);
    }
}
