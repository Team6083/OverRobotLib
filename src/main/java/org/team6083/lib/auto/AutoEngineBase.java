package org.team6083.lib.auto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team6083.lib.dashboard.DashBoard;

/**
 * Extend this class to use for auto robot code.
 *
 * @author KennHuang
 * @since 0.1.0-alpha-4
 */
public abstract class AutoEngineBase {
    private static boolean init = false;

    protected SendableChooser<String> m_chooser = new SendableChooser<>();
    protected final String kDoNothing = "Do nothing";
    protected String m_autoSelected;

    protected SendableChooser<String> a_chooser = new SendableChooser<>();
    protected final String kA1 = "A1";
    protected final String kA2 = "A2";
    protected final String kA3 = "A3";
    protected String allianceSelected;
    protected int station;

    protected String gameData;

    protected int step;
    protected String currentStep = "";
    protected Timer autoTimer = new Timer();

    protected DashBoard dashBoard = new DashBoard("AutoEngine");

    public void init() {
        if(!init){
            dashBoard.markWarning();
            a_chooser.setDefaultOption("A1", kA1);
            a_chooser.addOption("A2", kA2);
            a_chooser.addOption("A3", kA3);
            SmartDashboard.putData("Auto point choices", a_chooser);

            m_chooser.setDefaultOption("Do nothing", kDoNothing);
            SmartDashboard.putData("Auto choices", m_chooser);

            SmartDashboard.putNumber("autoDelay", 0);
            SmartDashboard.putString("CurrentStep", "wait to start");

            init = true;
        }
    }

    public void start(){
        m_autoSelected = m_chooser.getSelected();
        allianceSelected = a_chooser.getSelected();
        System.out.println("Auto selected: " + m_autoSelected + " on " + allianceSelected);

        gameData = DriverStation.getInstance().getGameSpecificMessage();

        switch (allianceSelected) {
            case kA1:
                station = 1;
                break;
            case kA2:
                station = 2;
                break;
            case kA3:
                station = 3;
                break;
            default:
                station = 1;
                break;
        }

        step = 0;
        Timer.delay(SmartDashboard.getNumber("autoDelay", 0));
    }

    public void loop(){

        SmartDashboard.putString("CurrentStep", currentStep);
        SmartDashboard.putNumber("Timer", autoTimer.get());
    }

    protected void nextStep() {
        step++;
        System.out.println("Finish step:"+currentStep);
        autoTimer.stop();
        autoTimer.reset();
        autoTimer.start();
    }
}
