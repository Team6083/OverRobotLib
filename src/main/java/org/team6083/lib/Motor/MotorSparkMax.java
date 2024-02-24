package org.team6083.lib.motor;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class MotorSparkMax extends com.revrobotics.CANSparkMax implements MotorController{
    public MotorSparkMax(int deviceId){
        super(deviceId, MotorType.kBrushless);
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
    }

    @Override
    public void set(double speed) {
    }
}
