public class SparkMax extends edu.wpi.first.wpilibj.CANSparkMax implements Motor{
    public SparkMax(int deviceId,String motortype){
        super(deviceId,motortype);
    }
    @Override 
    public void set(double speed){
        super.set(speed);
    }
    @Override
    public void stop(){
        super.set(0);
    }
}
