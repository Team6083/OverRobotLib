package org.team6083.lib.drive.swerve;

public interface DriveEncoder {
    void reset();

    double getDriveDistance();

    double getDriveRate();
}
