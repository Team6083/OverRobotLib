package org.team6083.lib.drive.swerve;

public interface SwerveDriveEncoder {
    void reset();

    double getDriveDistance();

    double getDriveRate();
}
