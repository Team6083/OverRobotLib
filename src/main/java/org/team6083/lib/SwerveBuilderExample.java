package org.team6083.lib;

import org.team6083.lib.drive.NEO_SDS_ServeDriveBuilder;

public class SwerveBuilderExample {

    public static void main(String[] args) throws Exception {

        var builder = new NEO_SDS_ServeDriveBuilder();

        var swerve = builder
                .setDriveMotorIDs(0, 1, 2, 3)
                .setTurningMotorIDs(4, 5, 6, 7)
                .setCANCoderIDs(8, 9, 10, 11)
                .build();
    }

}
