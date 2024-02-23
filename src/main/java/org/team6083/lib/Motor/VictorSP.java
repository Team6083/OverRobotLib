public class VictorSP extends VictorSP implements Motor{
    public VictorSP(deviceId){
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
