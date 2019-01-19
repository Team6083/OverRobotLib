package org.team6083.lib.dashboard;

public interface DashboardStatus {
    enum Status{
        OK(0),
        WARNING(1),
        ERROR(2);

        private final int value;
        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    Status getStatus();
}
