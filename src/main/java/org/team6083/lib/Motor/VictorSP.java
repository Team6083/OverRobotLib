public class VictorSP extends edu.wpi.first.wpilibj.VictorSP implements Motor{
    public VictorSP(int deviceId){
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
