package com.pomodorotimer.core;

import java.util.Timer;
import java.util.TimerTask;

public class SessionManager {
    private SessionType currentType = SessionType.WORK;
    private int remainingSeconds;
    private int completedPomodoros = 0;
    private boolean isRunning = false;
    private Timer timer;
    private SessionListener listener;
    
    public interface SessionListener {
        void onSessionEnd(SessionType completedType);
        void onTick(int remainingSeconds);
    }
    
    public void setListener(SessionListener listener) {
        this.listener = listener;
    }
    
    public void startSession() {
        if (isRunning) return;
        
        remainingSeconds = currentType.getDefaultMinutes() * 60;
        isRunning = true;
        timer = new Timer();
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                remainingSeconds--;
                if (listener != null) listener.onTick(remainingSeconds);
                
                if (remainingSeconds <= 0) {
                    endSession();
                }
            }
        }, 1000, 1000);
    }
    
    public void stopSession() {
        if (timer != null) {
            timer.cancel();
            isRunning = false;
        }
    }
    
    private void endSession() {
        SessionType completedType = currentType;
        stopSession();
        
        if (completedType == SessionType.WORK) {
            completedPomodoros++;
            currentType = (completedPomodoros % 4 == 0) ? SessionType.LONG_BREAK : SessionType.SHORT_BREAK;
        } else {
            currentType = SessionType.WORK;
        }
        
        if (listener != null) listener.onSessionEnd(completedType);
    }
    
    public SessionType getCurrentType() { return currentType; }
    public int getRemainingSeconds() { return remainingSeconds; }
    public boolean isRunning() { return isRunning; }
    public int getCompletedPomodoros() { return completedPomodoros; }
}