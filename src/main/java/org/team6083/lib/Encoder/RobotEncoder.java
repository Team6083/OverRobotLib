package org.team6083.lib.Encoder;


import edu.wpi.first.math.geometry.Rotation2d;

public interface RobotEncoder {
    void reset();

    double getDriveDistanceDouble();
    double getDriveRate();
    Rotation2d getAbsRotation();
}
