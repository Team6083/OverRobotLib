package org.team6083.lib;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class SensorHub {
    private Encoder leftEnc, rightEnc;
    private Gyro robotGyro;

    public SensorHub() {
    }

    public SensorHub(Encoder l, Encoder r, Gyro gyro) {
        leftEnc = l;
        rightEnc = r;
        robotGyro = gyro;
    }

    public boolean isRobotGyroAttached() {
        return robotGyro != null;
    }


}
