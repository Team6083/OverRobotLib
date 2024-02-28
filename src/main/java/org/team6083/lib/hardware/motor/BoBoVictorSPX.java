package org.team6083.lib.hardware.motor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

import java.util.function.Supplier;

public class BoBoVictorSPX extends VictorSPX implements CurrentLimit, MotorController {
    protected final Supplier<Double> motorCurrentSupplier;
    protected double currentLimit = -1;

    public BoBoVictorSPX(int deviceNumber) {
        super(deviceNumber);
        this.motorCurrentSupplier = null;
    }

    public BoBoVictorSPX(int deviceNumber, Supplier<Double> motorCurrentSupplier) {
        super(deviceNumber);
        this.motorCurrentSupplier = motorCurrentSupplier;
    }

    @Override
    public void set(double speed) {
        this.set(ControlMode.PercentOutput, speed);
    }

    @Override
    public void set(VictorSPXControlMode mode, double value) {
        this.set(mode, value, DemandType.Neutral, 0.0);
    }

    @Override
    public void set(VictorSPXControlMode mode, double demand0, DemandType demand1Type, double demand1) {
        if (motorCurrentSupplier != null && currentLimit >= 0 && motorCurrentSupplier.get() >= currentLimit) {
            stopMotor();
        } else {
            super.set(mode, demand0, demand1Type, demand1);
        }
    }

    @Override
    public void setVoltage(double outputVolts) {
        this.set(outputVolts / getBusVoltage());
    }

    @Override
    public double get() {
        return getMotorOutputPercent();
    }

    @Override
    public void disable() {
        stopMotor();
    }

    @Override
    public void stopMotor() {
        set(0);
    }

    // Current Limit
    @Override
    public void setCurrentLimit(double limit) {
        currentLimit = limit;
    }

    @Override
    public double getCurrentLimit() {
        return currentLimit;
    }
}
