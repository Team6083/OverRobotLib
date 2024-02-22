package org.team6083.lib.drive.swerve;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.*;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.team6083.lib.drive.DriveGyro;
import org.team6083.lib.drive.swerve.SwerveModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwerveDrive extends SubsystemBase {

    protected final Translation2d frontLeftLocation;
    protected final Translation2d frontRightLocation;
    protected final Translation2d backLeftLocation;
    protected final Translation2d backRightLocation;

    protected final SwerveModule frontLeft;
    protected final SwerveModule frontRight;
    protected final SwerveModule backLeft;
    protected final SwerveModule backRight;
    private final SwerveModule[] swerveModules;
    protected final DriveGyro gyro;

    protected final SwerveDriveKinematics kinematics;
    protected final SwerveDriveOdometry odometry;

    protected double kMaxSpeed;

    protected SwerveModuleState[] swerveModuleStates = new SwerveModuleState[4];

    public SwerveDrive(
            SwerveModule frontLeft, Translation2d frontLeftLocation,
            SwerveModule frontRight, Translation2d frontRightLocation,
            SwerveModule backLeft, Translation2d backLeftLocation,
            SwerveModule backRight, Translation2d backRightLocation,
            DriveGyro gyro
    ) {
        this.frontLeft = frontLeft;
        this.frontLeftLocation = frontLeftLocation;

        this.frontRight = frontRight;
        this.frontRightLocation = frontRightLocation;

        this.backLeft = backLeft;
        this.backLeftLocation = backLeftLocation;

        this.backRight = backRight;
        this.backRightLocation = backRightLocation;

        swerveModules = new SwerveModule[]{frontLeft, frontRight, backLeft, backRight};

        this.gyro = gyro;

        kinematics = new SwerveDriveKinematics(
                frontLeftLocation, frontRightLocation, backLeftLocation, backRightLocation
        );

        odometry = new SwerveDriveOdometry(
                kinematics,
                getRotation2d(),
                new SwerveModulePosition[]{
                        frontLeft.getPosition(),
                        frontRight.getPosition(),
                        backLeft.getPosition(),
                        backRight.getPosition()
                }
        );
    }

    public Rotation2d getRotation2d() {
        return Rotation2d.fromDegrees(gyro.getHeading());
    }

    public Pose2d getPose2d() {
        return odometry.getPoseMeters();
    }

    /**
     * Method to drive the robot using joystick info.
     *
     * @param xSpeed        Speed of the robot in the x direction (forward).
     * @param ySpeed        Speed of the robot in the y direction (sideways).
     * @param rot           Angular rate of the robot.
     * @param fieldRelative Whether the provided x and y speeds are relative to the
     *                      field.
     *                      <p>
     *                      using the wpi function to set the speed of the swerve
     */
    public Command driveCommand(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
        swerveModuleStates = kinematics.toSwerveModuleStates(
                fieldRelative
                        ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, getRotation2d())
                        : new ChassisSpeeds(xSpeed, ySpeed, rot)
        );

        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, kMaxSpeed);

        var cmd = Commands.parallel(
                frontLeft.setDesiredStateCommand(swerveModuleStates[0]),
                frontRight.setDesiredStateCommand(swerveModuleStates[1]),
                backLeft.setDesiredStateCommand(swerveModuleStates[2]),
                backRight.setDesiredStateCommand(swerveModuleStates[3])
        );

        cmd.addRequirements(this);

        return cmd;
    }

    protected void updateOdometry() {
        odometry.update(
                getRotation2d(),
                new SwerveModulePosition[]{
                        frontLeft.getPosition(),
                        frontRight.getPosition(),
                        backLeft.getPosition(),
                        backRight.getPosition()
                }
        );
    }

    public void stop() {
        for (var module : swerveModules) {
            module.stopModule();
        }
    }

    public Command stopCommand() {
        var cmd = new ParallelCommandGroup();

        for (var module : swerveModules) {
            cmd.addCommands(module.stopModuleCommand());
        }

        cmd.addRequirements(this);

        return cmd;
    }

    public void resetGyro() {
        gyro.reset();
    }

    @Override
    public void periodic() {
        updateOdometry();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);

        builder.addDoubleProperty("poseX", () -> getPose2d().getX(), null);
        builder.addDoubleProperty("poseY", () -> getPose2d().getY(), null);
        builder.addDoubleProperty("poseDeg", () -> getPose2d().getRotation().getDegrees(), null);
    }
}