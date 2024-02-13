package org.team6083.lib.commands.swerve;

import org.team6083.lib.drive.SwerveDrive;

public class RelativeSwerveJoystickCmd extends SwerveJoystickCmd {
    public RelativeSwerveJoystickCmd(SwerveDrive drive, SwerveDriveInput inputController) {
        super(drive, inputController, true);
    }
}
