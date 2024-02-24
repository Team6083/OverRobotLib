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
        super.reset();
    }

    @Override
    public double getDriveDisance() {
        super.get
    }

    @Override
    public double getDriveRate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDriveRate'");
    }

    @Override
    public Rotation2d getAbsRotation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAbsRotation'");
    }
}
