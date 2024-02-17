package org.team6083.lib.drive;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import org.team6083.lib.drive.swerve.SwerveDriveEncoder;
import org.team6083.lib.drive.swerve.SwerveModule;
import org.team6083.lib.drive.swerve.SwerveTurningEncoder;

public class SwerveDriveBuilder {
    private MotorController[] driveMotors;
    private MotorController[] turningMotors;
    private SwerveDriveEncoder[] driveEncoders;
    private SwerveTurningEncoder[] turningEncoders;
    private double[][] turningPIDCoefficients;
    private double[] speedToMotorVoltage;
    private Translation2d[] locations;
    private DriveGyro driveGyro;

    // driveMotors
    public SwerveDriveBuilder setDriveMotors(MotorController[] driveMotors) {
        this.driveMotors = driveMotors;
        return this;
    }

    public SwerveDriveBuilder setDriveMotors(
            MotorController frontLeft, MotorController frontRight,
            MotorController backLeft, MotorController backRight
    ) {
        return setDriveMotors(new MotorController[]{frontLeft, frontRight, backLeft, backRight});
    }

    // turningMotors
    public SwerveDriveBuilder setTurningMotors(MotorController[] turningMotors) {
        this.turningMotors = turningMotors;
        return this;
    }

    public SwerveDriveBuilder setTurningMotors(
            MotorController frontLeft, MotorController frontRight,
            MotorController backLeft, MotorController backRight
    ) {
        return setTurningMotors(new MotorController[]{frontLeft, frontRight, backLeft, backRight});
    }

    // driveEncoders
    public SwerveDriveBuilder setDriveEncoders(SwerveDriveEncoder[] driveEncoders) {
        this.driveEncoders = driveEncoders;
        return this;
    }

    public SwerveDriveBuilder setDriveEncoders(
            SwerveDriveEncoder frontLeft, SwerveDriveEncoder frontRight,
            SwerveDriveEncoder backLeft, SwerveDriveEncoder backRight
    ) {
        return setDriveEncoders(new SwerveDriveEncoder[]{frontLeft, frontRight, backLeft, backRight});
    }

    // turningEncoders
    public SwerveDriveBuilder setTurningEncoders(SwerveTurningEncoder[] turningEncoders) {
        this.turningEncoders = turningEncoders;
        return this;
    }

    public SwerveDriveBuilder setTurningEncoders(
            SwerveTurningEncoder frontLeft, SwerveTurningEncoder frontRight,
            SwerveTurningEncoder backLeft, SwerveTurningEncoder backRight
    ) {
        return setTurningEncoders(new SwerveTurningEncoder[]{frontLeft, frontRight, backLeft, backRight});
    }

    // turningPID
    public SwerveDriveBuilder setTurningPIDCoefficients(double kP, double kI, double kD) {
        double[] tmp = new double[]{kP, kI, kD};

        this.turningPIDCoefficients = new double[][]{tmp, tmp, tmp, tmp};
        return this;
    }

    public SwerveDriveBuilder setSpeedToMotorVoltage(double speedToMotorVoltage) {
        this.speedToMotorVoltage = new double[]{
                speedToMotorVoltage, speedToMotorVoltage, speedToMotorVoltage, speedToMotorVoltage
        };
        return this;
    }

    // locations
    public SwerveDriveBuilder setLocations(Translation2d[] locations) {
        this.locations = locations;
        return this;
    }

    public SwerveDriveBuilder setLocations(
            Translation2d frontLeft, Translation2d frontRight,
            Translation2d backLeft, Translation2d backRight
    ) {
        this.locations = new Translation2d[]{frontLeft, frontRight, backLeft, backRight};
        return this;
    }

    public SwerveDriveBuilder setDriveGyro(DriveGyro driveGyro) {
        this.driveGyro = driveGyro;
        return this;
    }

    public SwerveDrive build() throws Exception {
        if (driveMotors.length != 4) throw new Exception("Number of drive motors must 4");
        if (turningMotors.length != 4) throw new Exception("Number of turning motors must 4");
        if (driveEncoders.length != 4) throw new Exception("Number of drive encoders must 4");
        if (turningEncoders.length != 4) throw new Exception("Number of turning encoders must 4");
        if (turningPIDCoefficients.length != 4) throw new Exception("Number of turning PID coefficients must be 4");

        if (locations.length != 4) throw new Exception("Number of locations must be 4");
        if (driveGyro == null) throw new Exception("Drive Gyro is null");

        SwerveModule[] swerveModules = new SwerveModule[4];
        for (int i = 0; i < 4; i++) {
            swerveModules[i] = new SwerveModule(
                    driveMotors[i],
                    turningMotors[i],
                    driveEncoders[i],
                    turningEncoders[i],
                    turningPIDCoefficients[i][0],
                    turningPIDCoefficients[i][1],
                    turningPIDCoefficients[i][2],
                    speedToMotorVoltage[i]
            );
        }

        return new SwerveDrive(
                swerveModules[0], locations[0],
                swerveModules[1], locations[1],
                swerveModules[2], locations[2],
                swerveModules[3], locations[3],
                driveGyro
        );
    }
}
