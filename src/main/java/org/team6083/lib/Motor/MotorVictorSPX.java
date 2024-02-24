package org.team6083.lib.motor;


public class MotorVictorSPX extends com.ctre.phoenix.motorcontrol.can.VictorSPX implements Motor{
    public MotorVictorSPX(int deviceId){
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
