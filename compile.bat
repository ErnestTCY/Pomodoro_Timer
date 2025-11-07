@echo off
echo Compiling Pomodoro Timer...
javac -d bin -sourcepath src src\com\pomodorotimer\*.java src\com\pomodorotimer\core\*.java src\com\pomodorotimer\ui\*.java src\com\pomodorotimer\notification\*.java
if %errorlevel% == 0 (
    echo Compilation successful!
    echo To run: java -cp bin com.pomodorotimer.PomodoroApp
) else (
    echo Compilation failed!
)
pause