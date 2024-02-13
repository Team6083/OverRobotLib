package org.team6083.lib.drive.swerve;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveModule extends SubsystemBase {
    /**
     * Creates a new SwerveModule.
     */

    protected final DriveMotor driveMotor;
    protected final TurningMotor turningMotor;
    protected final TurningEncoder turningEncoder;
    protected final DriveEncoder driveEncoder;
    protected final PIDController rotController;

    protected final double kDesireSpeedToMotorVoltage;

    public SwerveModule(
            DriveMotor driveMotor, TurningMotor turningMotor,
            DriveEncoder driveEncoder, TurningEncoder turningEncoder,
            double kPRot, double kIRot, double kDRot,
            double kDesireSpeedToMotorVoltage
    ) {

        this.driveMotor = driveMotor;
        this.turningMotor = turningMotor;
        this.driveEncoder = driveEncoder;
        this.turningEncoder = turningEncoder;

        rotController = new PIDController(kPRot, kIRot, kDRot);
        rotController.enableContinuousInput(-180.0, 180.0);

        this.kDesireSpeedToMotorVoltage = kDesireSpeedToMotorVoltage;

        driveEncoder.reset();
        stopModule();
    }

    // to get the drive distance
    public double getDriveDistance() {
        return driveEncoder.getDriveDistance();
    }

    // calculate the rate of the drive
    public double getDriveRate() {
        return driveEncoder.getDriveRate();
    }

    // to get rotation of turning motor
    public double getRotation() {
        return turningEncoder.getRotation();
    }

    // to get the single swerveModule speed and the turning rate
    public SwerveModuleState getState() {
        return new SwerveModuleState(
                getDriveRate(),
                new Rotation2d(Math.toRadians(getRotation()))
        );
    }

    // to the get the position by wpi function
    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(
                getDriveDistance(),
                new Rotation2d(Math.toRadians(getRotation()))
        );
    }

    protected double[] calculateOutputVoltage(SwerveModuleState goalState) {
        var currentTurningDegree = getRotation();

        goalState = SwerveModuleState.optimize(goalState, Rotation2d.fromDegrees(currentTurningDegree));

        double driveMotorVoltage = kDesireSpeedToMotorVoltage * goalState.speedMetersPerSecond;
        double turningMotorVoltage = rotController.calculate(currentTurningDegree, goalState.angle.getDegrees());

        return new double[]{driveMotorVoltage, turningMotorVoltage};
    }

    public void setDesiredState(SwerveModuleState desiredState) {
        var moduleState = calculateOutputVoltage(desiredState);

        driveMotor.setVoltage(moduleState[0]);
        turningMotor.setVoltage(moduleState[1]);
    }

    public void stopModule() {
        driveMotor.stopMotor();
        turningMotor.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}
