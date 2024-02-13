package org.team6083.lib.commands.swerve;

import edu.wpi.first.wpilibj2.command.Command;
import org.team6083.lib.drive.SwerveDrive;

public class SwerveJoystickCmd extends Command {

    protected final SwerveDrive drive;

    protected final SwerveDriveInput inputController;

    protected final boolean fieldRelative;

    public SwerveJoystickCmd(SwerveDrive drive, SwerveDriveInput inputController) {
        this.drive = drive;
        this.inputController = inputController;
        fieldRelative = false;

        setupRequirements();
    }

    protected SwerveJoystickCmd(
            SwerveDrive drive, SwerveDriveInput inputController,
            boolean fieldRelative
    ) {
        this.drive = drive;
        this.inputController = inputController;
        this.fieldRelative = fieldRelative;

        setupRequirements();
    }

    private void setupRequirements() {
        addRequirements(this.drive);
    }

    @Override
    public void execute() {
        var cmd = drive.driveCommand(
                inputController.xSpeed(),
                inputController.ySpeed(),
                inputController.rotSpeed(),
                fieldRelative
        );
    }

    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }
}
