package org.team6083.lib.Encoder;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.drive.RobotDriveBase.MotorType;
import com.revrobotics.CANSparkLowLevel.MotorType;
public class RobotCANcoder extends com.ctre.phoenix6.hardware.CANcode implements RobotEncoder{
    public RobotCANcoder(int Channel){
        super(Channel);
    }

    @Override
    public void reset() {
        super.setPosition(0);
    }

    @Override
    public double getDriveDisance() {
        super.getDriveDistance();
    }

    @Override
    public double getDriveRate() {
        return super.getDriveRate();
    }

    @Override
    public Rotation2d getAbsRotation() {
        return super.getAbsolutePosition().getValue() * 360.0;
    }
}
