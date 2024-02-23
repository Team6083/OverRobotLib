public class VictorSPX extends edu.wpi.first.wpilibj.WPI_VictorSPX implements Motor{
    public VictorSPX(int deviceId){
        super(deviceId);
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
