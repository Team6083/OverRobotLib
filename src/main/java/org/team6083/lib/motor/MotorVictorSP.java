package org.team6083.lib.motor;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class MotorVictorSP extends edu.wpi.first.wpilibj.motorcontrol.VictorSP implements MotorController{
    public MotorVictorSP(int deviceId){
       super(deviceId);
    }
    public void set(double speed, double kp){
        super.set(speed*kp);
    }
    

}
