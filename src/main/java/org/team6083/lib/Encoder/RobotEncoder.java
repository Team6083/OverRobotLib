package org.team6083.lib.Encoder;

import com.ctre.phoenix6.StatusSignal;

import edu.wpi.first.math.geometry.Rotation2d;

public interface RobotEncoder {
    void reset();

    double getDriveDisanceDouble();
    double getDriveRate();
    Rotation2d getAbsRotation();
}
