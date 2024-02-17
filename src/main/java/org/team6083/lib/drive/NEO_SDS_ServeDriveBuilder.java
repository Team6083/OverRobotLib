package org.team6083.lib.drive;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.CAN;
import org.team6083.lib.drive.swerve.SwerveDriveEncoder;
import org.team6083.lib.hardware.motor.CANSparkMaxBuilder;
import org.team6083.lib.hardware.sensor.CANCoder;
import org.team6083.lib.hardware.sensor.CANSparkMaxRelativeEncoder;

import java.util.HashMap;
import java.util.Map;

public class NEO_SDS_ServeDriveBuilder extends SwerveDriveBuilder {
    private int[] driveIDs, turningIDs, CANCoderIDs;
    private Map<Integer, Boolean> driveInverted, turningInverted;

    private CANSparkMax[] driveMotors, turningMotors;
    private CANCoder[] turningEncoders;

    public NEO_SDS_ServeDriveBuilder() {
        driveInverted = new HashMap<>();
        turningInverted = new HashMap<>();
    }

    public void setDriveMotorIDs(int[] driveMotorIDs) {
        driveIDs = driveMotorIDs;
    }

    public void setDriveMotorInverted(int idx, boolean inverted) {
        driveInverted.put(idx, inverted);
    }

    public void setTurningMotorIDs(int[] turningMotorIDs) {
        turningIDs = turningMotorIDs;
    }

    public void setTurningMotorInverted(int idx, boolean inverted) {
        turningInverted.put(idx, inverted);
    }

    public void setCANCoderIDs(int[] CANCoderIDs) {
        this.CANCoderIDs = CANCoderIDs;
    }

    @Override
    public SwerveDrive build() throws Exception {
        CANSparkMaxBuilder builder = new CANSparkMaxBuilder()
                .setIdleMode(CANSparkBase.IdleMode.kBrake)
                .setMotorType(CANSparkLowLevel.MotorType.kBrushless);

        driveMotors = new CANSparkMax[driveIDs.length];
        SwerveDriveEncoder[] driveEncoders = new SwerveDriveEncoder[driveIDs.length];
        for (int i = 0; i < driveMotors.length; i++) {
            var dMotor = builder
                    .setId(driveIDs[i])
                    .setInverted(driveInverted.get(i))
                    .build();

            driveEncoders[i] = new CANSparkMaxRelativeEncoder(dMotor.getEncoder());
            driveMotors[i] = dMotor;
        }
        super.setDriveMotors(driveMotors);
        super.setDriveEncoders(driveEncoders);

        turningMotors = new CANSparkMax[turningIDs.length];
        for (int i = 0; i < turningMotors.length; i++) {
            turningMotors[i] = builder
                    .setId(turningIDs[i])
                    .setInverted(turningInverted.get(i))
                    .build();
        }
        super.setTurningMotors(turningMotors);

        turningEncoders = new CANCoder[CANCoderIDs.length];
        for (int i = 0; i < turningEncoders.length; i++) {
            turningEncoders[i] = new CANCoder(CANCoderIDs[i]);
        }
        super.setTurningEncoders(turningEncoders);

        return super.build();
    }
}
