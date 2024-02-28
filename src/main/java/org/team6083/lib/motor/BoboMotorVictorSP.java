package org.team6083.lib.motor;

public class BoboMotorVictorSP extends edu.wpi.first.wpilibj.motorcontrol.VictorSP{
    public BoboMotorVictorSP(int deviceId){
       super(deviceId);
    }
    public void set(double speed, double kp){
        super.set(speed*kp);
    }

}
