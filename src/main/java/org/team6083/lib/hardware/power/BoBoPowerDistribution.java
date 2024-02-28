package org.team6083.lib.hardware.power;

import edu.wpi.first.wpilibj.PowerDistribution;

import java.util.function.Supplier;

public class BoBoPowerDistribution extends PowerDistribution implements ChannelCurrentSupplier {
    public BoBoPowerDistribution() {
        super();
    }

    public BoBoPowerDistribution(int module, ModuleType moduleType) {
        super(module, moduleType);
    }

    @Override
    public Supplier<Double> getChannelCurrentSupplier(int channel) {
        return () -> getCurrent(channel);
    }
}
