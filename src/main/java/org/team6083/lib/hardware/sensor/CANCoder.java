package org.team6083.lib.hardware.sensor;

import com.ctre.phoenix6.hardware.CANcoder;
import edu.wpi.first.math.geometry.Rotation2d;
import org.team6083.lib.drive.swerve.SwerveTurningEncoder;

public class CANCoder extends CANcoder implements SwerveTurningEncoder {
    public CANCoder(int deviceId) {
        super(deviceId);
    }

    public CANCoder(int deviceId, String canbus) {
        super(deviceId, canbus);
    }

    @Override
    public Rotation2d getAbsRotation() {
        return Rotation2d.fromDegrees(getAbsolutePosition().getValueAsDouble() * 360.0);
    }
}
