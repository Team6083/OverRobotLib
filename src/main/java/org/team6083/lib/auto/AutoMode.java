package org.team6083.lib.auto;

import org.team6083.lib.SensorHub;
import org.team6083.lib.drive.DifferentialDrive;

@Deprecated
public abstract class AutoMode {

    protected DifferentialDrive drive;
    protected SensorHub sensorHub;
    private String name;

    public AutoMode(DifferentialDrive differentialDrive, SensorHub sensors, String name){
        drive = differentialDrive;
        sensorHub = sensors;
        this.name = name;
    }

    public abstract void start();
    public abstract void loop(int step);
    public String getName(){
        return name;
    }
}
