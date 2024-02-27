package org.team6083.lib.drive.swerve;

import org.team6083.lib.encoder.RobotEncoder;

import com.ctre.phoenix6.StatusSignal;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveModule extends SubsystemBase {
    /**
     * Creates a new SwerveModule.
     */

    protected final MotorController driveMotor;
    protected final MotorController turningMotor;
    protected final RobotEncoder turningEncoder;
    protected final RobotEncoder driveEncoder;
    protected final PIDController rotController;

    protected final double kDesireSpeedToMotorVoltage;

    public SwerveModule(
            MotorController driveMotor, MotorController turningMotor,
            RobotEncoder driveEncoder, RobotEncoder turningEncoder,
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
        return driveEncoder.getDriveDistanceDouble();
    }

    // calculate the rate of the drive
    public double getDriveRate() {
        return driveEncoder.getDriveRate();
    }

    // to get rotation of turning motor
    public Rotation2d getRotation() {
        return turningEncoder.getAbsRotation();
    }

    // to get the single swerveModule speed and the turning rate
    public SwerveModuleState getState() {
        return new SwerveModuleState(
                getDriveRate(),
                getRotation()
        );
    }

    // to the get the position by wpi function
    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(
                getDriveDistance(),
                getRotation()
        );
    }

    protected double[] calculateOutputVoltage(SwerveModuleState goalState) {
        var currentTurningDegree = getRotation().getDegrees();

        goalState = SwerveModuleState.optimize(goalState, Rotation2d.fromDegrees(currentTurningDegree));

        double driveMotorVoltage = kDesireSpeedToMotorVoltage * goalState.speedMetersPerSecond;
        double turningMotorVoltage = rotController.calculate(currentTurningDegree, goalState.angle.getDegrees());

        return new double[]{driveMotorVoltage, turningMotorVoltage};
    }

    protected void setDesiredState(SwerveModuleState desiredState) {
        var moduleState = calculateOutputVoltage(desiredState);

        driveMotor.setVoltage(moduleState[0]);
        turningMotor.setVoltage(moduleState[1]);
    }

    public Command setDesiredStateCommand(SwerveModuleState desiredState) {
        return this.runOnce(() -> setDesiredState(desiredState));
    }

    public void stopModule() {
        driveMotor.stopMotor();
        turningMotor.stopMotor();
    }

    public Command stopModuleCommand() {
        return this.runOnce(this::stopModule);
    }

    public Command turnToCommand(Rotation2d angle) {
        return new TurnToCommand(this, angle, Rotation2d.fromDegrees(1));
    }

    public Command motorRunCommand(double turningPower, double drivePower) {
        return this.run(() -> {
            turningMotor.set(turningPower);
            driveMotor.set(drivePower);
        });
    }

    private static class TurnToCommand extends Command {
        Rotation2d targetAngle, admissibleError;

        SwerveModule swerveModule;

        public TurnToCommand(SwerveModule swerveModule, Rotation2d angle, Rotation2d admissibleError) {
            this.swerveModule = swerveModule;
            this.targetAngle = angle;
            this.admissibleError = admissibleError;

            addRequirements(this.swerveModule);
        }

        @Override
        public void execute() {
            swerveModule.driveMotor.stopMotor();

            var turnVolt = swerveModule.rotController.calculate(
                    swerveModule.getRotation().getDegrees(),
                    targetAngle.getDegrees()
            );

            swerveModule.turningMotor.setVoltage(turnVolt);
        }

        @Override
        public boolean isFinished() {
            var errorAngle = targetAngle.minus(swerveModule.getRotation());

            return Math.abs(errorAngle.getDegrees()) < admissibleError.getDegrees();
        }
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);

        builder.addDoubleProperty("driveDist", this::getDriveDistance, null);
        builder.addDoubleProperty("driveRate", this::getDriveRate, null);
        builder.addDoubleProperty("rotation", () -> getRotation().getDegrees(), null);

        builder.addDoubleProperty("driveMotor", driveMotor::get, driveMotor::set);
        builder.addDoubleProperty("turningMotor", turningMotor::get, turningMotor::set);
    }
}