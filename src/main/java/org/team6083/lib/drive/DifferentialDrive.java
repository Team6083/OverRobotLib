package org.team6083.lib.drive;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpiutil.math.MathUtil;

import org.team6083.lib.drive.inputs.TankInput;


/**
 * A class for controlling robot drive
 *
 * @author KennHuang
 * @since 0.1.0-alpha
 */
public class DifferentialDrive {
    private SpeedController leftMotor1, leftMotor2, rightMotor1, rightMotor2;
    private boolean reverseDrive = false;
    private double speedDown;
    private boolean lastButton;
    private double boostMultiple;

    private Encoder leftEnc, rightEnc;

    //the following variable can be delete if the newest code dosen't work
    private boolean m_reported;

    public static final double kDefaultDeadband = 0.02;
    public static final double kDefaultMaxOutput = 1.0;
    private static final double kDefaultSafetyExpiration = 0.1;
    private double m_rightSideInvertMultiplier = -1.0;
  
    protected double m_deadband = kDefaultDeadband;
    protected double m_maxOutput = kDefaultMaxOutput;

    private final Object m_thisMutex = new Object();

    private double m_expiration = kDefaultSafetyExpiration;
    private double m_stopTime = Timer.getFPGATimestamp();
  

    /**
     * Construct a DifferentialDrive.
     *
     * @param left1  left motor controller 1
     * @param left2  left motor controller 2
     * @param right1 right motor controller 1
     * @param right2 right motor controller 2
     */
    public DifferentialDrive(SpeedController left1, SpeedController left2, SpeedController right1, SpeedController right2) {
        leftMotor1 = left1;
        leftMotor2 = left2;
        rightMotor1 = right1;
        rightMotor2 = right2;

        speedDown = 2.0;
        dashboard(0, 0);
        lastButton = false;
        boostMultiple = 2.0;
    }

   /** if the following code dosen't work ,delete it
   * Arcade drive method for differential drive platform.
   *
   * @param xSpeed        The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param zRotation     The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is
   *                      positive.
   * @param squareInputs If set, decreases the input sensitivity at low speeds.
   */
    public void arcadeDrive(double xSpeed, double zRotation, boolean squareInputs) {
    if (!m_reported) {
        HAL.report(tResourceType.kResourceType_RobotDrive,
                   tInstances.kRobotDrive2_DifferentialArcade, 2);
        m_reported = true;
      }
  
      xSpeed = MathUtil.clamp(xSpeed, -1.0, 1.0);
      xSpeed = applyDeadband(xSpeed, m_deadband);
  
      zRotation = MathUtil.clamp(zRotation, -1.0, 1.0);
      zRotation = applyDeadband(zRotation, m_deadband);
  
      // Square the inputs (while preserving the sign) to increase fine control
      // while permitting full power.
      if (squareInputs) {
        xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
        zRotation = Math.copySign(zRotation * zRotation, zRotation);
      }
  
      double leftMotorOutput;
      double rightMotorOutput;
  
      double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);
  
      if (xSpeed >= 0.0) {
        // First quadrant, else second quadrant
        if (zRotation >= 0.0) {
          leftMotorOutput = maxInput;
          rightMotorOutput = xSpeed - zRotation;
        } else {
          leftMotorOutput = xSpeed + zRotation;
          rightMotorOutput = maxInput;
        }
      } else {
        // Third quadrant, else fourth quadrant
        if (zRotation >= 0.0) {
          leftMotorOutput = xSpeed + zRotation;
          rightMotorOutput = maxInput;
        } else {
          leftMotorOutput = maxInput;
          rightMotorOutput = xSpeed - zRotation;
        }
      }
  
      leftMotor1.set(MathUtil.clamp(leftMotorOutput, -1.0, 1.0) * m_maxOutput);
      double maxOutput = m_maxOutput * m_rightSideInvertMultiplier;
      rightMotor1.set(MathUtil.clamp(rightMotorOutput, -1.0, 1.0) * maxOutput);
  
      feed();
    }

    public void feed() {
        synchronized (m_thisMutex) {
          m_stopTime = Timer.getFPGATimestamp() + m_expiration;
        }
      }
    // delete to here
    

    protected double applyDeadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
          if (value > 0.0) {
            return (value - deadband) / (1.0 - deadband);
          } else {
            return (value + deadband) / (1.0 - deadband);
          }
        } else {
          return 0.0;
        }
      }

    /**
     * Attach encoder to the drive base.
     *
     * @param left  left side encoder
     * @param right right side encoder
     */
    public void attachEncoder(Encoder left, Encoder right) {
        leftEnc = left;
        rightEnc = right;
    }

    /**
     * Stop the drive
     */
    public void stop() {
        leftMotor1.set(0);
        rightMotor1.set(0);
        leftMotor2.set(0);
        rightMotor2.set(0);

        dashboard(0, 0);
    }

    /**
     * Use this to control the drive directly without any limit.
     *
     * @param leftSpeed  the left side speed of the robot
     * @param rightSpeed the right side speed of the robot
     */
    public void directControl(double leftSpeed, double rightSpeed) {
        leftMotor1.set(leftSpeed);
        leftMotor2.set(leftSpeed);
        rightMotor1.set(rightSpeed);
        rightMotor2.set(rightSpeed);

        dashboard(leftSpeed, rightSpeed);
    }

    public enum Side {
        kLeft,
        kRight
    }

    /**
     * Control the drive with TankDrive mode.
     *
     * @param input A joystick to control the drive.
     */
    public void tankDrive(TankInput input) {
        double left = calculateTankSpeed(input, Side.kLeft);
        double right = calculateTankSpeed(input, Side.kRight);

        if (input.toggleReverseButton() && (input.toggleReverseButton() != lastButton)) {
            reverseDrive = !reverseDrive;
        }

        if (reverseDrive) {
            double t = left;
            left = right;
            right = t;
            SmartDashboard.putBoolean("drive/reverse", true);
        } else {
            SmartDashboard.putBoolean("drive/reverse", false);
        }
        reverseDrive = SmartDashboard.getBoolean("drive/reverse", reverseDrive);

        directControl(left, right);
    }

    public double calculateTankSpeed(TankInput input, Side side) {
        double speed = 0;
        double boost = 1;
        if (side == Side.kLeft) {
            speed = -input.leftSpeed();
            if (input.leftBoostButton()) {
                boost = boostMultiple;
            }
        } else if (side == Side.kRight) {
            speed = input.rightSpeed();
            if (input.rightBoostButton()) {
                boost = boostMultiple;
            }
        }

        return speed / speedDown * boost;
    }

    /**
     * Get speed of left drive.
     *
     * @return The set value of left drive.
     */
    public double getLeftPower() {
        return leftMotor1.get();
    }

    /**
     * Get speed of right speed.
     *
     * @return The set value of right drive.
     */
    public double getRightPower() {
        return rightMotor1.get();
    }

    /**
     * Get the left side encoder distance.
     *
     * @return left side walked distance
     */
    public double getLeftDis() {
        return leftEnc.getDistance();
    }

    /**
     * Get the right side encoder distance.
     *
     * @return right side walked distance
     */
    public double getRightDis() {
        return rightEnc.getDistance();
    }

    /**
     * Get speedDown of the drive.
     *
     * @return current speedDown
     */
    public double getSpeedDown() {
        return speedDown;
    }

    /**
     * Set new speedDown.
     *
     * @param speedDown new speedDown
     */
    public void setSpeedDown(double speedDown) {
        this.speedDown = speedDown;
    }

    /**
     * Get boostMultiple of the drive.
     *
     * @return current boostMultiple
     */
    public double getBoostMultiple() {
        return this.boostMultiple;
    }

    /**
     * Set new boostMultiple.
     *
     * @param boostMultiple new boostMultiple
     */
    public void setBoostMultiple(double boostMultiple) {
        this.boostMultiple = boostMultiple;
    }

    public boolean getReverseDrive() {
        return reverseDrive;
    }

    public void setReverseDrive(boolean reverse) {
        reverseDrive = reverse;
    }

    private void dashboard(double l_speed, double r_speed) {
        SmartDashboard.putBoolean("drive/reverse", reverseDrive);
        SmartDashboard.putNumber("drive/leftSpeed", l_speed);
        SmartDashboard.putNumber("drive/rightSpeed", r_speed);
        SmartDashboard.putNumber("drive/speedDown", speedDown);
        SmartDashboard.putNumber("drive/boostMultiple", boostMultiple);
    }
}
