package org.team6083.lib.util;

import edu.wpi.first.wpilibj.XboxController;
import org.team6083.lib.commands.differential.TankDriveInput;

public class XBoxController extends XboxController implements TankDriveInput {

    public final double axisDeadBandRange;

    /**
     * Construct an instance of a joystick.
     * The joystick index is the USB port on the "Driver Station".
     *
     * @param port The port on the Driver Station that the joystick is plugged into.
     */
    public XBoxController(int port) {
        super(port);
        axisDeadBandRange = 0.05;
    }

    public XBoxController(int port, double axisDeadBandRange) {
        super(port);
        this.axisDeadBandRange = axisDeadBandRange;
    }

    @Override
    public double getLeftX() {
        double v = super.getLeftX();
        if (Math.abs(v) < axisDeadBandRange) {
            v = 0;
        }
        return v;
    }

    @Override
    public double getLeftY() {
        double v = super.getLeftY();
        if (Math.abs(v) < axisDeadBandRange) {
            v = 0;
        }
        return v;
    }

    @Override
    public double getRightX() {
        double v = super.getRightX();
        if (Math.abs(v) < axisDeadBandRange) {
            v = 0;
        }
        return v;
    }

    @Override
    public double getRightY() {
        double v = super.getRightY();
        if (Math.abs(v) < axisDeadBandRange) {
            v = 0;
        }
        return v;
    }

    @Override
    public double leftSpeed() {
        return getLeftY();
    }

    @Override
    public double rightSpeed() {
        return getRightY();
    }

    @Override
    public boolean toggleReverseButton() {
        return getBButton();
    }

    @Override
    public boolean leftBoostButton() {
        return getLeftBumper();
    }

    @Override
    public boolean rightBoostButton() {
        return getRightBumper();
    }
}
