package org.team6083.lib.auto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team6083.lib.dashboard.AutoDashboard;
import org.team6083.lib.dashboard.DashBoard;
import org.team6083.lib.util.annotation.Unstable;

/**
 * Extend this class to use for auto robot code.
 *
 * @author KennHuang
 * @since 0.1.0-alpha-4
 */
@Unstable
public abstract class AutoEngineBase {
    protected AutoDashboard autoDashboard;

    protected String modeSelected;

    protected String allianceSelected;
    protected int station;

    protected String gameData;

    protected int step;
    protected String currentStep = "";
    protected Timer autoTimer = new Timer();

    protected DashBoard dashBoard;

    public AutoEngineBase() {
        autoDashboard = new AutoDashboard();
        dashBoard = new DashBoard("AutoEngine");
    }

    public void init(){

    }

    public final void start() {
        modeSelected = autoDashboard.getSelectedMode();
        station = autoDashboard.getStation();
        allianceSelected = autoDashboard.getSelectedStation();
        System.out.println("Auto selected: " + modeSelected + " on " + allianceSelected);

        gameData = DriverStation.getInstance().getGameSpecificMessage();

        autoInit();

        step = 0;
        Timer.delay(autoDashboard.getAutoDelay());
        afterStartDelay();
    }

    protected void autoInit(){

    }


    protected void afterStartDelay(){

    }

    public void loop() {
        SmartDashboard.putString("CurrentStep", currentStep);
        SmartDashboard.putNumber("Timer", autoTimer.get());
    }

    protected void nextStep() {
        step++;
        System.out.println("Finish step:" + currentStep);
        autoTimer.stop();
        autoTimer.reset();
        autoTimer.start();
    }
}
