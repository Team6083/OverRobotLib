package org.team6083.lib.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import org.team6083.lib.drive.inputs.TankInput;

public class XBoxController extends XboxController implements TankInput {
    /**
     * Construct an instance of a joystick. The joystick index is the USB port on the drivers
     * station.
     *
     * @param port The port on the Driver Station that the joystick is plugged into.
     */
    public XBoxController(int port) {
        super(port);
    }

    @Override
    public double leftSpeed() {
        return getY(GenericHID.Hand.kLeft);
    }

    @Override
    public double rightSpeed() {
        return getY(GenericHID.Hand.kRight);
    }

    @Override
    public boolean toggleReverseButton() {
        return getBButton();
    }

    @Override
    public boolean leftBoostButton() {
        return getBumper(GenericHID.Hand.kLeft);
    }

    @Override
    public boolean rightBoostButton() {
        return getBumper(GenericHID.Hand.kRight);
    }
}
