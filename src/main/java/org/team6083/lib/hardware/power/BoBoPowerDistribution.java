package org.team6083.lib.hardware.power;

import edu.wpi.first.wpilibj.PowerDistribution;

import java.util.function.Supplier;

public class BoBoPowerDistribution extends PowerDistribution implements ChannelCurrentSupplier {
    @Override
    public Supplier<Double> getChannelCurrentSupplier(int channel) {
        return () -> getCurrent(channel);
    }
}
