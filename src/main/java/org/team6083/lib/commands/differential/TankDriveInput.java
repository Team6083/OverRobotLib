package org.team6083.lib.commands.differential;

/**
 * An interface to control the {@link org.team6083.lib.drive.DifferentialDrive} with TankDrive mode
 */
public interface TankDriveInput {
    /**
     * Get left speed from stick.
     *
     * @return speed that will feed to the left drive
     */
    double leftSpeed();

    /**
     * Get right speed from disk.
     *
     * @return speed that will feed to the right drive
     */
    double rightSpeed();

    /**
     * Get toggle reverse button from stick.
     *
     * @return current statue of the button that will toggle the reverse feature
     */
    boolean toggleReverseButton();

    /**
     * Get left boost button from stick.
     *
     * @return current statue of the button that will boost the left drive
     */
    boolean leftBoostButton();

    /**
     * Get right boost button from stick.
     *
     * @return current statue of the button that will boost the right drive
     */
    boolean rightBoostButton();
}
