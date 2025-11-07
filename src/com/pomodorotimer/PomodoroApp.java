package com.pomodorotimer;

import com.pomodorotimer.ui.PomodoroUI;
import javax.swing.SwingUtilities;

public class PomodoroApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PomodoroUI().setVisible(true);
        });
    }
}