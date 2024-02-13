package org.team6083.lib.auto;

import edu.wpi.first.wpilibj.Encoder;

public class EncoderWalker {
    private Encoder leftEnc, rightEnc;
    private Mode controlMode;
    private double leftSpeed, rightSpeed;
    private boolean finished = false;

    private double walkSpeed = 0.4;

    public enum Mode {
        LeftOnly,
        RightOnly,
        Both,
        Average
    }

    /**
     * Constructor of EncoderWalker class.
     *
     * @param left  left side encoder
     * @param right right side encoder
     * @param mode  control mode of EncoderWalker. See {@link Mode}.
     */
    public EncoderWalker(Encoder left, Encoder right, Mode mode) {
        leftEnc = left;
        rightEnc = right;
        controlMode = mode;
    }

    /**
     * Reset the EncoderWalker. <strong> This will not reset the Encoder. You have to reset the Encoder manually.</strong>
     */
    public void reset() {
        leftSpeed = 0;
        rightSpeed = 0;
        finished = false;
    }

    public double getLeftDis() {
        return leftEnc.getDistance();
    }

    public double getRightDis() {
        return rightEnc.getDistance();
    }

    public double getLeftSpeed() {
        return leftSpeed;
    }

    public double getRightSpeed() {
        return rightSpeed;
    }

    public double getWalkSpeed() {
        return walkSpeed;
    }

    public void setWalkSpeed(double walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    /**
     * Use this to walk the robot with EncoderWalker.
     * <strong>You have to setup the distance per plus of the Encoders before using this.</strong>.
     *
     * @param targetDistance the distance of the target
     */
    public void walk(double targetDistance) {
        double leftDistance = getLeftDis();
        double rightDistance = getRightDis();

        if (finished) {
            leftSpeed = 0;
            rightSpeed = 0;
            return;
        }

        double direction;
        if (targetDistance > 0) {
            direction = 1;
        } else {
            direction = -1;
        }

        boolean enableLeft = false;
        if (controlMode == Mode.LeftOnly || controlMode == Mode.Both) {
            enableLeft = true;
        }

        boolean enableRight = false;
        if (controlMode == Mode.RightOnly || controlMode == Mode.Both) {
            enableRight = true;
        }

        if (Math.abs(leftDistance) < Math.abs(targetDistance) && enableLeft) {
            leftSpeed = walkSpeed * direction;
        } else {
            leftSpeed = 0;
        }
        if (Math.abs(rightDistance) < Math.abs(targetDistance) && enableRight) {
            rightSpeed = walkSpeed * direction;
        } else {
            rightSpeed = 0;
        }

        if (controlMode == Mode.LeftOnly) {
            rightSpeed = leftSpeed;
        } else if (controlMode == Mode.RightOnly) {
            leftSpeed = rightSpeed;
        }

        if (controlMode == Mode.Average) {
            double currDis = (leftDistance + rightDistance) / 2;
            if (Math.abs(currDis) < Math.abs(targetDistance)) {
                leftSpeed = walkSpeed * direction;
                rightSpeed = walkSpeed * direction;
            } else {
                leftSpeed = 0;
                rightSpeed = 0;
                finished = true;
            }
        } else {
            boolean lFinish = true, rFinish = true;

            if (enableLeft) {
                if (Math.abs(leftDistance) < Math.abs(targetDistance)) {
                    lFinish = false;
                }
            }

            if (enableRight) {
                if (Math.abs(rightDistance) < Math.abs(targetDistance)) {
                    rFinish = false;
                }
            }

            if (lFinish && rFinish) {
                leftSpeed = 0;
                rightSpeed = 0;
                finished = true;
            }
        }
    }

    /**
     * Check whether the encoder walker is finished.
     *
     * @return if the encoder walker finished
     */
    public boolean isFinished() {
        return finished;
    }

}
