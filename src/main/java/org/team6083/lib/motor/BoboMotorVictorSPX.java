package org.team6083.lib.motor;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class BoboMotorVictorSPX extends com.ctre.phoenix.motorcontrol.can.VictorSPX implements MotorController{
    public BoboMotorVictorSPX(int deviceId){
        super(deviceId);
    }

    @Override
    public double get() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public void setInverted(boolean isInverted) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setInverted'");
    }

    @Override
    public boolean getInverted() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInverted'");
    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disable'");
    }

    @Override
    public void stopMotor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stopMotor'");
    }

    @Override
    public void set(double speed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }
}
