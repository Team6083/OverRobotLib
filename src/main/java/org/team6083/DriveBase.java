package org.team6083;

import edu.wpi.first.wpilibj.SpeedController;

public class DriveBase {
	private SpeedController leftMotor1, leftMotor2, rightMotor1, rightMotor2;
	
	public DriveBase(SpeedController left1, SpeedController left2, SpeedController right1, SpeedController right2) {
		leftMotor1 = left1;
		leftMotor2 = left2;
		rightMotor1 = right1;
		rightMotor2 = right2;
	}

	// Let them override this
	public void tankDrive(){
		leftMotor1.set(0);
		rightMotor1.set(0);
		leftMotor2.set(0);
		rightMotor2.set(0);
	}

	public void directControl(double leftSpeed, double rightSpeed) {
        leftMotor1.set(leftSpeed);
        leftMotor2.set(leftSpeed);
        rightMotor1.set(rightSpeed);
        rightMotor2.set(rightSpeed);
	}


}
