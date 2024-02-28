package org.team6083.lib.hardware.motor;

import com.revrobotics.CANSparkMax;

public class BoBoCANSparkMAX extends CANSparkMax implements CurrentLimit {
    public BoBoCANSparkMAX(int deviceId, MotorType type) {
        super(deviceId, type);
    }

    private int currentLimit;

    @Override
    public void setCurrentLimit(double limit) {
        currentLimit = (int) limit;
        setSmartCurrentLimit(currentLimit);
    }

    @Override
    public double getCurrentLimit() {
        return currentLimit;
    }
}
