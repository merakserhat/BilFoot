package com.bilmatch.bilfoot.models.notification;

import android.graphics.Color;

enum NotificationType {
    INVITATION,
    INFORMATION

        }

public abstract class Notification {
    private int id;
    private NotificationType notificationType;

    public abstract String getMessage();

    public abstract Color getBellColor();


}
