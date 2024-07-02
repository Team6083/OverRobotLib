package org.team6083.distanceSensor;

public class SimDistanceSensor implements DistanceSensorInterface {

    public SimDistanceSensor(){
        super();
    }

    @Override
    public double getTargetDistance() {
        return -1;
    }

    @Override
    public boolean isGetTarget() {
       return true;
    }

    @Override
    public void setAutomaticMode(boolean isEnable) {
        return;
    }
    
}
