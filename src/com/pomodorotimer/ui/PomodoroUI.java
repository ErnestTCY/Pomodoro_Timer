package com.pomodorotimer.ui;

import com.pomodorotimer.core.SessionManager;
import com.pomodorotimer.core.SessionType;
import com.pomodorotimer.notification.NotificationService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PomodoroUI extends JFrame implements SessionManager.SessionListener {
    private SessionManager sessionManager;
    private NotificationService notificationService;
    private JLabel timeLabel;
    private JLabel sessionLabel;
    private JButton startButton;
    private JLabel pomodoroCountLabel;
    
    public PomodoroUI() {
        sessionManager = new SessionManager();
        sessionManager.setListener(this);
        notificationService = new NotificationService();
        
        initUI();
        updateDisplay();
    }
    
    private void initUI() {
        setTitle("Pomodoro Timer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        timeLabel = new JLabel("25:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(20, 20, 10, 20);
        add(timeLabel, gbc);
        
        sessionLabel = new JLabel("WORK SESSION", SwingConstants.CENTER);
        sessionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 1; gbc.insets = new Insets(0, 20, 20, 20);
        add(sessionLabel, gbc);
        
        startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.addActionListener(this::toggleSession);
        gbc.gridy = 2; gbc.insets = new Insets(0, 20, 10, 20);
        add(startButton, gbc);
        
        pomodoroCountLabel = new JLabel("Completed: 0", SwingConstants.CENTER);
        gbc.gridy = 3; gbc.insets = new Insets(0, 20, 20, 20);
        add(pomodoroCountLabel, gbc);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void toggleSession(ActionEvent e) {
        if (sessionManager.isRunning()) {
            sessionManager.stopSession();
            startButton.setText("START");
        } else {
            sessionManager.startSession();
            startButton.setText("STOP");
        }
    }
    
    @Override
    public void onTick(int remainingSeconds) {
        SwingUtilities.invokeLater(() -> {
            int minutes = remainingSeconds / 60;
            int seconds = remainingSeconds % 60;
            timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
        });
    }
    
    @Override
    public void onSessionEnd(SessionType completedType) {
        SwingUtilities.invokeLater(() -> {
            startButton.setText("START");
            updateDisplay();
            notificationService.showSessionEndNotification(completedType);
        });
    }
    
    private void updateDisplay() {
        SessionType current = sessionManager.getCurrentType();
        sessionLabel.setText(current.name().replace("_", " ") + " SESSION");
        pomodoroCountLabel.setText("Completed: " + sessionManager.getCompletedPomodoros());
        
        if (!sessionManager.isRunning()) {
            int minutes = current.getDefaultMinutes();
            timeLabel.setText(String.format("%02d:00", minutes));
        }
    }
}