# Pomodoro Timer Demo

A minimal Java implementation of a Pomodoro Timer application.

## Features
- 25-minute work sessions
- 5-minute short breaks  
- 15-minute long breaks (after 4 work sessions)
- System tray notifications
- Modal interruption dialogs
- Simple GUI with timer display

## How to Compile and Run

### Method 1: Using Batch Scripts (Windows)
```cmd
# Compile the application
compile.bat

# Run the application  
run.bat
```

### Method 2: Manual Commands
```cmd
# Create bin directory (if not exists)
mkdir bin

# Compile
javac -d bin -sourcepath src src\com\pomodorotimer\*.java src\com\pomodorotimer\core\*.java src\com\pomodorotimer\ui\*.java src\com\pomodorotimer\notification\*.java

# Run
java -cp bin com.pomodorotimer.PomodoroApp
```

## Usage
1. Click "START" to begin a work session
2. The timer counts down from 25:00
3. When the session ends, you'll get a notification
4. After 5 seconds, a modal dialog will appear requiring interaction
5. The app automatically switches between work and break sessions
6. Every 4th break is a long break (15 minutes)

## Project Structure
```
src/
└── com/pomodorotimer/
    ├── PomodoroApp.java          # Main application entry point
    ├── core/
    │   ├── SessionType.java      # Enum for session types
    │   └── SessionManager.java   # Core timer logic
    ├── ui/
    │   └── PomodoroUI.java      # Swing GUI interface
    └── notification/
        └── NotificationService.java # System notifications
```

This is a minimal demo implementation. You can use this as a reference while practicing your coding skills to rewrite and improve the application.