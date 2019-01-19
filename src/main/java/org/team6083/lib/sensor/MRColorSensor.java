package org.team6083.lib.sensor;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import org.team6083.lib.util.TypeConversion;

public class MRColorSensor extends SendableBase {

    public enum Register {
        FIRMWARE_REV(0x00),
        MANUFACTURE_CODE(0x01),
        SENSOR_ID(0x02),
        COMMAND(0x03),
        COLOR_NUMBER(0x04),

        RED(0x05),
        GREEN(0x06),
        BLUE(0x07),
        ALPHA(0x08),

        COLOR_INDEX(0x09),
        RED_INDEX(0x0a),
        GREEN_INDEX(0x0b),
        BLUE_INDEX(0x0c),

        RED_READING(0x0e),
        GREEN_READING(0x10),
        BLUE_READING(0x12),
        ALPHA_READING(0x14),

        NORMALIZED_RED_READING(0x16),
        NORMALIZED_GREEN_READING(0x18),
        NORMALIZED_BLUE_READING(0x1a),
        NORMALIZED_ALPHA_READING(0x1c),

        READ_WINDOW_FIRST(RED.bVal),
        READ_WINDOW_LAST(NORMALIZED_ALPHA_READING.bVal + 1);
        public byte bVal;

        Register(int value) {
            this.bVal = (byte) value;
        }
    }

    public enum Command {
        ACTIVE_LED(0x00),
        PASSIVE_LED(0x01),
        HZ50(0x35),
        HZ60(0x36),
        CALIBRATE_BLACK(0x042),
        CALIBRATE_WHITE(0x43);
        public byte bVal;

        Command(int value) {
            this.bVal = (byte) value;
        }
    }


    private I2C m_i2c;

    public MRColorSensor(I2C.Port port, int deviceAddress) {
        m_i2c = new I2C(port, deviceAddress);
    }

    public int readColorNumber() {
        byte[] read = new byte[1];
        m_i2c.read(Register.COLOR_NUMBER.bVal, 1, read);
        return TypeConversion.unsignedByteToInt(read[0]);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("Color Sensor");
    }
}
