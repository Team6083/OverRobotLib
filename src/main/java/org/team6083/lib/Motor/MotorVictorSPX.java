package org.team6083.lib.Motor;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.Victor;

public class MotorVictorSPX extends WPI_VictorSPX implements Motor{
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
