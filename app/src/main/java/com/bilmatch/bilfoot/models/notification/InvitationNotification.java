package com.bilmatch.bilfoot.models.notification;

import android.graphics.Color;

public class InvitationNotification extends Notification{
    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public Color getBellColor() {
        return null;
    }

    public void acceptInvitation() {
        //TODO: send accepted request
    }

    public void  refuseInvitation() {
        //TODO: send refused request
    }
}
