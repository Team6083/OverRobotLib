package org.team6083.lib.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Gyro;


/**
 * Correcting the heading of the drive with gyroscope
 * @author Alex-Lai, Kenn Huang
 * @since 0.1.0-alpha
 */
public class GyroWalker {
    Gyro gyro;

    private double currentSourceAngle, currentAngle;
    private double errorAngle;
    private double targetAngle;

    private double leftPower, rightPower;
    private double kP;
    private double kI;
    private double maxPower;
    private double maxEdit;

    private Timer calculateTimer;
    private double kI_result = 0;

    private double smallAngleAdd;
    private double smallAngle;

    /**
     * Construct a GyroWalker
     * @param gyro the gyroscope that used to correction the heading
     */
    public GyroWalker(Gyro gyro) {
        this.gyro = gyro;
        leftPower = 0;
        rightPower = 0;
        targetAngle = 0;

        kP = 0.025;
        kI = 0.01;

        maxPower = 0.7;
        maxEdit = 0.5;
        calculateTimer = new Timer();

        smallAngle = 0;
        smallAngleAdd = 10;

        resetTimer();
    }

    /**
     *
     * @param leftSetPower original power of left
     * @param rightSetPower original power of right
     */
    public void calculate(double leftSetPower, double rightSetPower) {
        currentSourceAngle = gyro.getAngle();
        currentAngle = translateAngle(currentSourceAngle);

        double angle = currentAngle;
        if(angle > 180) {
            angle = angle - 360;
        }

        if(Math.abs(targetAngle)>140) {
            angle = currentAngle;
        }
        //translate angle to -180~180

        errorAngle = targetAngle - angle;
        //calculate the difference between target angle and current angle

        double nowtime = calculateTimer.get();
        kI_result += nowtime * errorAngle;

        double editPower = 0;

        if(Math.abs(errorAngle) < smallAngle) {
            //make robot still move if the errorAngle is too small
            editPower = errorAngle * kP * (smallAngle - errorAngle)/smallAngle * smallAngleAdd;
        }
        else {
            editPower =  errorAngle * kP;
        }
        //calculate output diff with kP

        editPower += kI* kI_result;
        //calculate output diff with kI

        if(editPower>maxEdit) {
            editPower = (editPower > 0)?maxEdit:-maxEdit;
        }
        //limit the max output diff

        leftPower = leftSetPower + editPower;
        rightPower = rightSetPower - editPower;
        //calculate final output

        if(Math.abs(leftPower)>maxPower) {
            if (leftPower >= 0) {
                leftPower = maxPower;
            } else {
                leftPower = -maxPower;
            }
        }


        if(Math.abs(rightPower)>maxPower) {
            if (rightPower >= 0) {
                rightPower = maxPower;
            } else {
                rightPower = -maxPower;
            }
        }
        //limit max output
    }

    public void setTargetAngle(double angle) {
        targetAngle = angle;
        resetTimer();
    }

    private void resetTimer(){
        kI_result = 0;
        calculateTimer.stop();
        calculateTimer.reset();
        calculateTimer.start();
    }

    public static double translateAngle(double sourceAngle) {
        double angle = sourceAngle - (360 * (int)(sourceAngle/360));
        if(angle < 0) {
            angle = 360 + angle;
        }
        return angle;
    }

    public void setkP(double kP) {
        this.kP = kP;
    }

    public void setkI(double kI) {
        this.kI = kI;
    }

    public double getkP(){
        return kP;
    }

    public double getkI(){
        return kI;
    }

    public double getkI_result(){
        return kI_result;
    }

    // calculate process

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
        return errorAngle;
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

    public double getSmallAngleAdd(){
        return smallAngleAdd;
    }

    public double getSmallAngle(){
        return smallAngle;
    }

    public void setSmallAngleAdd(double smallAngleAdd){
        this.smallAngleAdd = smallAngleAdd;
    }

    public void setSmallAngle(double smallAngle){
        this.smallAngle = smallAngle;
    }

    // doing with small angle fix

}
