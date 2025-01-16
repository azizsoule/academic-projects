package controllers;

import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Notification {
    public static void showNotification(NotificationType type, String title,String msg){
        TrayNotification tray = new TrayNotification();
        tray.setAnimationType(AnimationType.POPUP);
        tray.setTitle(title);
        tray.setMessage(msg);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.millis(500));
    }
}
