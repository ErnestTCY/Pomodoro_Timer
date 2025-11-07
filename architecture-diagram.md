# Pomodoro Timer - Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                    POMODORO TIMER APPLICATION                   │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   PomodoroApp   │    │   PomodoroUI    │    │NotificationService│
│   (Main Entry)  │───▶│  (Swing GUI)    │◄───│  (System Tray)  │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                              │                        │
                              ▼                        │
                    ┌─────────────────┐                │
                    │ SessionManager  │◄───────────────┘
                    │  (Core Logic)   │
                    └─────────────────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │  SessionType    │
                    │    (Enum)       │
                    │ - WORK          │
                    │ - SHORT_BREAK   │
                    │ - LONG_BREAK    │
                    └─────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                        COMPONENT DETAILS                       │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────┐
│  PomodoroApp    │
├─────────────────┤
│ + main()        │  ◄── Application Entry Point
└─────────────────┘

┌─────────────────┐
│  PomodoroUI     │
├─────────────────┤
│ - timerLabel    │  ◄── Timer Display (MM:SS)
│ - startButton   │  ◄── Start/Pause Control
│ - statusLabel   │  ◄── Session Status
│ + updateTimer() │  ◄── UI Update Method
└─────────────────┘

┌─────────────────┐
│ SessionManager  │
├─────────────────┤
│ - currentType   │  ◄── Current Session Type
│ - timeRemaining │  ◄── Countdown Timer
│ - pomodoros     │  ◄── Completed Count
│ + startSession()│  ◄── Timer Control
│ + pauseSession()│
│ + nextSession() │  ◄── Session Transitions
└─────────────────┘

┌─────────────────┐
│NotificationService│
├─────────────────┤
│ + showTrayNotif()│ ◄── System Tray Alert
│ + showModal()   │  ◄── Modal Dialog
│ + playSound()   │  ◄── Audio Alert
└─────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                         DATA FLOW                              │
└─────────────────────────────────────────────────────────────────┘

User Action ──┐
              ▼
         PomodoroUI ──────────────┐
              │                  │
              ▼                  ▼
      SessionManager ────▶ NotificationService
              │                  │
              ▼                  ▼
         Timer Thread ────▶ System Tray/Modal
              │
              ▼
         UI Updates

┌─────────────────────────────────────────────────────────────────┐
│                      SESSION FLOW                              │
└─────────────────────────────────────────────────────────────────┘

START ──▶ WORK(25min) ──▶ SHORT_BREAK(5min) ──▶ WORK(25min) ──┐
  ▲                                                           │
  │                                                           ▼
  └── LONG_BREAK(15min) ◄── WORK(25min) ◄── SHORT_BREAK(5min) ──┘
           (After 4 Pomodoros)

┌─────────────────────────────────────────────────────────────────┐
│                   NOTIFICATION STRATEGY                        │
└─────────────────────────────────────────────────────────────────┘

Session End ──┐
              ▼
      System Tray Notification
              │
              ▼ (5 seconds delay)
         Modal Dialog
              │
              ▼ (User interaction required)
         Next Session Start
```