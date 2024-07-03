package org.team6083.lib.distanceSensor;

public interface DistanceSensorInterface {
    public double getTargetDistance();
    public boolean isGetTarget();
    public void setAutomaticMode(boolean isEnable);
}