package org.team6083.lib.joysticks;

import edu.wpi.first.wpilibj.XboxController;
import org.team6083.lib.drive.inputs.TankInput;

public class XBox extends XboxController implements TankInput {
    /**
     * Construct an instance of a joystick. The joystick index is the USB port on the drivers
     * station.
     *
     * @param port The port on the Driver Station that the joystick is plugged into.
     */
    public XBox(int port) {
        super(port);
    }

    @Override
    public double leftSpeed() {
        return getY(Hand.kLeft);
    }

    @Override
    public double rightSpeed() {
        return getY(Hand.kRight);
    }

    @Override
    public boolean toggleReverseButton() {
        return getBButton();
    }

    @Override
    public boolean leftBoostButton() {
        return getBumper(Hand.kLeft);
    }

    @Override
    public boolean rightBoostButton() {
        return getBumper(Hand.kRight);
    }
}
