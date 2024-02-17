package org.team6083.lib.hardware.motor;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

public class CANSparkMaxBuilder {
    private int id = -1;
    private CANSparkLowLevel.MotorType motorType;
    private boolean inverted;
    private CANSparkBase.IdleMode idleMode;

    public CANSparkMaxBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public CANSparkMaxBuilder setMotorType(CANSparkLowLevel.MotorType motorType) {
        this.motorType = motorType;
        return this;
    }

    public CANSparkMaxBuilder setInverted(boolean inverted) {
        this.inverted = inverted;
        return this;
    }

    public CANSparkMaxBuilder setIdleMode(CANSparkBase.IdleMode idleMode) {
        this.idleMode = idleMode;
        return this;
    }

    public CANSparkMax build() throws Exception {
        if (id == -1) throw new Exception("id is required");
        if (motorType == null) throw new Exception("motor type is required");

        var canSparkMax = new CANSparkMax(id, motorType);

        if (inverted) canSparkMax.setInverted(true);
        if (idleMode != null) canSparkMax.setIdleMode(idleMode);

        return canSparkMax;
    }
}
