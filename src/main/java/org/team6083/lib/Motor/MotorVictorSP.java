package org.team6083.lib.motor;

public class MotorVictorSP extends edu.wpi.first.wpilibj.motorcontrol.VictorSP{
    public MotorVictorSP(int deviceId){
       super(deviceId);
    }
    public void set(double speed, double kp){
        super.set(speed*kp);
    }

}
