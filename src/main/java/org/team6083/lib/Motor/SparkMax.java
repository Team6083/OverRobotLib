package org.team6083.lib.motor;

import com.revrobotics.CANSparkMax;
public class SparkMax extends com.revrobotics.CANSparkMax implements Motor{
    public SparkMax(int deviceId,String motortype){
        super(deviceId,motortype);
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
