package org.team6083.lib.drive.SwerveBuilder;

import org.team6083.lib.drive.swerve.SwerveDrive;
import org.team6083.lib.drive.swerve.SwerveModule;
import org.team6083.lib.encoder.RobotCANcoder;
import org.team6083.lib.encoder.RobotEncoder;
import org.team6083.lib.encoder.RobotRelativeEncoder;
import org.team6083.lib.gyro.Pigeon;
import org.team6083.lib.motor.MotorSparkMax;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class SwerveBuilder {
    public SwerveBuilder(
            int driveMotorID[], int turningMotorID[], int turningEncoderID[], int gyroID, double kPRot,
            double kIRot, double kDRot, double kDesireSpeedToMotorVoltage) {
        MotorSparkMax frontLeftDriveMotor = new MotorSparkMax(driveMotorID[0]);
        MotorSparkMax frontRightDriveMotor = new MotorSparkMax(driveMotorID[1]);
        MotorSparkMax backLeftDriveMotor = new MotorSparkMax(driveMotorID[2]);
        MotorSparkMax backRightDriveMotor = new MotorSparkMax(driveMotorID[3]);

        MotorSparkMax frontLeftTurningMotor = new MotorSparkMax(turningMotorID[0]);
        MotorSparkMax frontRightTurningMotor = new MotorSparkMax(turningMotorID[1]);
        MotorSparkMax backLeftTurningMotor = new MotorSparkMax(turningMotorID[2]);
        MotorSparkMax backRightTurningMotor = new MotorSparkMax(turningMotorID[3]);

        RelativeEncoder frontLeftRelative = frontLeftDriveMotor.getEncoder();
        RelativeEncoder frontRightRelative = frontRightDriveMotor.getEncoder();
        RelativeEncoder backLeftRelative = backLeftDriveMotor.getEncoder();
        RelativeEncoder backRightRelative = backRightDriveMotor.getEncoder();
        RobotRelativeEncoder frontLeftDiveEncoder = new RobotRelativeEncoder(frontLeftRelative);
        RobotRelativeEncoder frontRightDriveEncoder = new RobotRelativeEncoder(frontRightRelative);
        RobotRelativeEncoder backLeftDriveEncoder = new RobotRelativeEncoder(backLeftRelative);
        RobotRelativeEncoder backRightDriveEncoder = new RobotRelativeEncoder(backRightRelative);

        RobotCANcoder frontLeftTurningEncoder = new RobotCANcoder(turningEncoderID[0]);
        RobotCANcoder frontRightTurningEncoder = new RobotCANcoder(turningEncoderID[1]);
        RobotCANcoder backLeftTurningEncoder = new RobotCANcoder(turningEncoderID[2]);
        RobotCANcoder backRightTurningEncoder = new RobotCANcoder(turningEncoderID[3]);

        Translation2d frontLeftLocation = new Translation2d();
        Translation2d frontRightLocation = new Translation2d();
        Translation2d backLeftLocation = new Translation2d();
        Translation2d backRightLocation = new Translation2d();

        Pigeon gyro = new Pigeon(0);

        SwerveModule frontLeft = new SwerveModule(frontLeftDriveMotor, frontLeftTurningMotor, frontLeftDiveEncoder,
                frontLeftTurningEncoder, kPRot, kIRot, kDRot, kDesireSpeedToMotorVoltage);
        SwerveModule frontRight = new SwerveModule(frontRightDriveMotor, frontRightTurningMotor, frontRightDriveEncoder,
                frontRightTurningEncoder, kPRot, kIRot, kDRot, kDesireSpeedToMotorVoltage);
        SwerveModule backLeft = new SwerveModule(backLeftDriveMotor, backLeftTurningMotor, backLeftDriveEncoder,
                backLeftTurningEncoder, kPRot, kIRot, kDRot, kDesireSpeedToMotorVoltage);
        SwerveModule backRight = new SwerveModule(backRightDriveMotor, backRightTurningMotor, backRightDriveEncoder,
                backRightTurningEncoder, kPRot, kIRot, kDRot, kDesireSpeedToMotorVoltage);

        SwerveDrive swerveDrive = new SwerveDrive(frontLeft, frontLeftLocation, frontRight, frontRightLocation, backLeft, backLeftLocation, backRight, backRightLocation,
                gyro);
    }
}