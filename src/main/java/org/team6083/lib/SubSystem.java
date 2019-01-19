package org.team6083.lib;

import org.team6083.lib.dashboard.DashboardStatus;

public class SubSystem implements DashboardStatus {
    private Status status;
    private String name;

    public SubSystem(String name) {
        this.name = name;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
}
