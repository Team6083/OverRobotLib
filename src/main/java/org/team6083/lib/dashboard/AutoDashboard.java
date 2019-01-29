package org.team6083.lib.dashboard;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoDashboard implements DashboardStatus {
    private boolean init = false;

    private SendableChooser<String> m_chooser;
    public static final String kDoNothing = "Do nothing";

    private SendableChooser<String> a_chooser;
    public static final String kA1 = "A1";
    public static final String kA2 = "A2";
    public static final String kA3 = "A3";


    public AutoDashboard() {
        m_chooser = new SendableChooser<>();
        a_chooser = new SendableChooser<>();

        m_chooser.setDefaultOption(kDoNothing, kDoNothing);

        a_chooser.setDefaultOption(kA1, kA1);
        a_chooser.addOption(kA2, kA2);
        a_chooser.addOption(kA3, kA3);
    }

    public void init() {
        SmartDashboard.putData("Auto choices", m_chooser);
        SmartDashboard.putData("Auto point choices", a_chooser);
        SmartDashboard.putNumber("autoDelay", 0);
        SmartDashboard.putString("CurrentStep", "wait to start");
        init = true;
    }

    public double getAutoDelay() {
        return SmartDashboard.getNumber("autoDelay", 0);
    }

    public void addMode(String name, String object) {
        m_chooser.addOption(name, object);
    }

    public String getSelectedMode() {
        return m_chooser.getSelected();
    }

    public String getSelectedStation() {
        return a_chooser.getSelected();
    }

    public int getStation() {
        String allianceSelected = a_chooser.getSelected();
        switch (allianceSelected) {
            case kA1:
                return 1;
            case kA2:
                return 2;
            case kA3:
                return 3;
            default:
                System.err.println("Error Selected Station.");
                break;
        }
        return 0;
    }

    @Override
    public Status getStatus() {
        return init ? Status.OK : Status.WARNING;
    }
}
