package org.team6083.lib.hardware.power;

import java.util.function.Supplier;

public interface ChannelCurrentSupplier {
    public Supplier<Double> getChannelCurrentSupplier(int channel);
}
