package org.team6083.lib.util;

public class TypeConversion {
    public static int unsignedByteToInt(byte b) {
        return ((int) (b) & 0xff);
    }
}
