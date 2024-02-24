package org.team6083.lib.auto;

import org.team6083.lib.gyro.DriveGyro;

import edu.wpi.first.math.controller.PIDController;


/**
 * Correcting the heading of the drive with gyroscope
 *
 * @author Alex-Lai, Kenn Huang, Tasi Yun-Shing
 * @since 0.1.0-alpha
 */
public class GyroWalker {
    private DriveGyro gyro;
    private PIDController pidController;

    private double currentSourceAngle, currentAngle;
    private double targetAngle;

    private double leftPower, rightPower;
    private double maxPower;
    private double maxEdit;

    private boolean useTranslateAngle = false;

    /**
     * Construct a GyroWalker
     *
     * @param gyro the gyroscope that used to correction the heading
     */
    public GyroWalker(DriveGyro gyro) {
        this.gyro = gyro;
        leftPower = 0;
        rightPower = 0;
        targetAngle = 0;

        pidController = new PIDController(0, 0, 0);

        maxPower = 0.7;
        maxEdit = 0.5;
    }

    /**
     * @param leftSetPower  original power of left
     * @param rightSetPower original power of right
     */
    public void calculate(double leftSetPower, double rightSetPower) {
        currentSourceAngle = gyro.getHeading();
        currentAngle = useTranslateAngle ? translateAngle(currentSourceAngle) : currentSourceAngle;

        double angle = currentAngle;
        if (angle > 180) {
            angle = angle - 360;
        }

        if (Math.abs(targetAngle) > 140) {
            angle = currentAngle;
        }
        //translate angle to -180~180

        double editPower = 0;

        editPower = calculate_in_frc_api(angle, targetAngle);

        if (editPower > maxEdit) {
            editPower = (editPower > 0) ? maxEdit : -maxEdit;
        }
        //limit the max output diff

        leftPower = leftSetPower + editPower;
        rightPower = rightSetPower - editPower;
        //calculate final output

        if (Math.abs(leftPower) > maxPower) {
            if (leftPower >= 0) {
                leftPower = maxPower;
            } else {
                leftPower = -maxPower;
            }
        }


        if (Math.abs(rightPower) > maxPower) {
            if (rightPower >= 0) {
                rightPower = maxPower;
            } else {
                rightPower = -maxPower;
            }
        }
        //limit max output
    }

    /**
     * Returns the next output of the PID controller.
     *
     * @param measurement The current measurement of the process variable.
     * @param setPoint    The new setpoint of the controller.
     */
    public double calculate_in_frc_api(double measurement, double setPoint) {
        return pidController.calculate(measurement, setPoint);
    }

    /**
     * set three pid term in order to calculate
     *
     * @param Kp Proportional term for the PID controller
     * @param Ki Integral term for the PID controller
     * @param Kd derivative term for the PID controller
     */
    public void setPID(double Kp, double Ki, double Kd) {
        setP(Kp);
        setI(Ki);
        setD(Kd);
    }

    /**
     * Sets the Proportional coefficient of the PID controller gain.
     *
     * @param Kp proportional coefficient
     */
    public void setP(double Kp) {
        pidController.setP(Kp);
    }

    /**
     * Sets the Integral coefficient of the PID controller gain.
     *
     * @param Ki integral coefficient
     */
    public void setI(double Ki) {
        pidController.setI(Ki);
    }

    /**
     * Sets the Differential coefficient of the PID controller gain.
     *
     * @param Kd differential coefficient
     */
    public void setD(double Kd) {
        pidController.setD(Kd);
    }

    /**
     * Set a heading for GyroWalker to follow.
     *
     * @param angle target heading of the robot
     */
    public void setTargetAngle(double angle) {
        pidController.setSetpoint(angle);
        targetAngle = angle;
    }

    /**
     * Convert the angle that will continue from 360 to 361 degrees to the range of -180~180 degrees.
     *
     * @param sourceAngle the angle that get from the gyro output
     * @return translated angle, from -180~180 degrees
     */
    public static double translateAngle(double sourceAngle) {
        double angle = sourceAngle - (360 * (int) (sourceAngle / 360));
        if (angle < 0) {
            angle = 360 + angle;
        }
        return angle;
    }

    public double getKp() {
        return pidController.getP();
    }

    public double getKi() {
        return pidController.getI();
    }

    public double getKd() {
        return pidController.getD();
    }

    // calculate process

    /**
     * Set the maximum variation value.
     *
     * @param power max variation value
     */
    public void setMaxPower(double power) {
        maxPower = power;
    }

    public double getCurrentSourceAngle() {
        return currentSourceAngle;
    }

    public double getCurrentAngle() {
        return currentAngle;
    }

    public double getErrorAngle() {
        return pidController.getPositionError();
    }

    public double getLeftPower() {
        return leftPower;
    }

    public double getRightPower() {
        return rightPower;
    }

    public double getTargetAngle() {
        return targetAngle;
    }

    // final result

    public void setUseTranslateAngle(boolean useTranslateAngle) {
        this.useTranslateAngle = useTranslateAngle;
    }

    public boolean getUseTranslateAngle() {
        return useTranslateAngle;
    }
}
