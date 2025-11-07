# Technical Specifications Memory Bank

## Data Models

### Session Types
```java
enum SessionType {
    WORK(25), SHORT_BREAK(5), LONG_BREAK(15);
    private final int defaultMinutes;
}
```

### Session State
```java
class SessionState {
    SessionType currentType;
    int remainingSeconds;
    int completedPomodoros;
    boolean isPaused;
    LocalDateTime startTime;
}
```

## Process Flows

### Main Timer Flow
1. Start work session (25 minutes)
2. Show break reminder when session ends
3. If no response in 5 minutes → force interruption
4. Start break session (5 or 15 minutes based on completed pomodoros)
5. Repeat cycle

### Notification Flow
1. Session ends → Show gentle notification
2. Wait 5 minutes for user response
3. If no response → Show modal interruption (requires user action)
4. User acknowledges → Start next session

## Key Implementation Patterns

### Timer Management
- Use `ScheduledExecutorService` for precise timing
- Implement pause/resume functionality
- Handle system sleep/wake events

### Notification Strategy
- Level 1: System tray notification (dismissible)
- Level 2: Desktop notification with sound
- Level 3: Modal dialog (requires interaction)

### Configuration Management
- Store user preferences in properties file
- Support customizable session lengths
- Remember last session state for recovery