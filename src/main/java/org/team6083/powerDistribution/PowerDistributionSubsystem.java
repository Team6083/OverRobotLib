// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team6083.powerDistribution;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PowerDistributionSubsystem {
  private PowerDistribution powerDistribution;
  String name;
  int channel;
  int MaxCurrent;

  public PowerDistributionSubsystem(String name, int channel, int MaxCurrent) {
    powerDistribution = new PowerDistribution();
    this.name = name;
    this.MaxCurrent = MaxCurrent;
    SmartDashboard.putNumber(name + "Current", 0);
    SmartDashboard.putBoolean("is" + name + "OverCurrent", false);
  }

  public double Current() {
    double current = powerDistribution.getCurrent(channel);
    SmartDashboard.putNumber(name + "Current", current);
    return current;
  }

  public boolean isOverCurrent() {
    boolean isOverCurrent = Current() > MaxCurrent;
    SmartDashboard.putBoolean("is" + name + "OverCurrent", isOverCurrent);
    return isOverCurrent;
  }

}
