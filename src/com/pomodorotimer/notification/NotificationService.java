package com.pomodorotimer.notification;

import com.pomodorotimer.core.SessionType;
import javax.swing.JOptionPane;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.Image;
import java.awt.Toolkit;

public class NotificationService {
    private TrayIcon trayIcon;
    
    public NotificationService() {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
            trayIcon = new TrayIcon(image, "Pomodoro Timer");
            trayIcon.setImageAutoSize(true);
            try {
                tray.add(trayIcon);
            } catch (Exception e) {
                System.out.println("TrayIcon could not be added.");
            }
        }
    }
    
    public void showSessionEndNotification(SessionType completedType) {
        String message = getSessionMessage(completedType);
        
        if (trayIcon != null) {
            trayIcon.displayMessage("Pomodoro Timer", message, TrayIcon.MessageType.INFO);
        }
        
        // Show modal dialog after 5 seconds if no response
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                showModalInterruption(message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
    
    private void showModalInterruption(String message) {
        JOptionPane.showMessageDialog(null, message, "Pomodoro Timer", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private String getSessionMessage(SessionType completedType) {
        switch (completedType) {
            case WORK:
                return "Work session completed! Time for a break.";
            case SHORT_BREAK:
                return "Short break finished! Ready to work?";
            case LONG_BREAK:
                return "Long break finished! Ready for the next pomodoro?";
            default:
                return "Session completed!";
        }
    }
}