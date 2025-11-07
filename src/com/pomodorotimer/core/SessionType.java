package com.pomodorotimer.core;

public enum SessionType {
    WORK(25), SHORT_BREAK(5), LONG_BREAK(15);
    
    private final int defaultMinutes;
    
    SessionType(int defaultMinutes) {
        this.defaultMinutes = defaultMinutes;
    }
    
    public int getDefaultMinutes() {
        return defaultMinutes;
    }
}