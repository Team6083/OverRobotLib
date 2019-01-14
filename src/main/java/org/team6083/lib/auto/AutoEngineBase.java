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

    protected static SendableChooser<String> m_chooser = new SendableChooser<>();
    protected static final String kDoNothing = "Do nothing";
    protected static String m_autoSelected;

    protected static SendableChooser<String> a_chooser = new SendableChooser<>();
    protected static final String kA1 = "A1";
    protected static final String kA2 = "A2";
    protected static final String kA3 = "A3";
    protected static String allianceSelected;
    protected static int station;

    protected static String gameData;

    protected static int step;
    protected static String currentStep = "";
    protected static Timer autoTimer = new Timer();

    protected static DashBoard dashBoard;

    public static void init() {
        if(!init){
            dashBoard.markWarning();
            a_chooser.setDefaultOption("A1", kA1);
            a_chooser.addOption("A2", kA2);
            a_chooser.addOption("A3", kA3);
            SmartDashboard.putData("Auto point choices", a_chooser);

            m_chooser.setDefaultOption("Do nothing", kDoNothing);
            SmartDashboard.putData("Auto choices", m_chooser);

            dashBoard = new DashBoard("AutoEngine");
            SmartDashboard.putNumber("autoDelay", 0);
            SmartDashboard.putString("CurrentStep", "wait to start");

            init = true;
        }
    }

    public static void start(){
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

    public static void loop(){

        SmartDashboard.putString("CurrentStep", currentStep);
        SmartDashboard.putNumber("Timer", autoTimer.get());
    }

    protected static void nextStep() {
        step++;
        System.out.println("Finish step:"+currentStep);
        autoTimer.stop();
        autoTimer.reset();
        autoTimer.start();
    }
}
