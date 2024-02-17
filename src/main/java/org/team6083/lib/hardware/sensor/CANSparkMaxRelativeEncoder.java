package org.team6083.lib.hardware.sensor;

import com.revrobotics.RelativeEncoder;
import org.team6083.lib.drive.swerve.SwerveDriveEncoder;

public class CANSparkMaxRelativeEncoder implements SwerveDriveEncoder {
    private final RelativeEncoder encoder;

    public CANSparkMaxRelativeEncoder(RelativeEncoder encoder) {
        this.encoder = encoder;
    }

    public RelativeEncoder getEncoder() {
        return encoder;
    }

    @Override
    public void reset() {
        encoder.setPosition(0);
    }

    @Override
    public double getDriveDistance() {
        return encoder.getPosition() / 6.75;
    }

    @Override
    public double getDriveRate() {
        return encoder.getVelocity() / 60.0 / 6.75;
    }
}
