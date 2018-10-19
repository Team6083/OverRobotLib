package org.team6083;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

public class Drivebase {
	private SpeedController leftMotor1, leftMotor2, rightMotor1, rightMotor2;
	
	public Drivebase(SpeedController left1, SpeedController left2, SpeedController right1, SpeedController right2) {
		leftMotor1 = left1;
		leftMotor2 = left2;
		rightMotor1 = right1;
		rightMotor2 = right2;
	}
	
	public void tankDrive() {
		
	}


}
