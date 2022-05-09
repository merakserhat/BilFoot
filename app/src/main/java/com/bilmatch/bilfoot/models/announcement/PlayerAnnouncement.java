package com.bilmatch.bilfoot.models.announcement;

import android.media.Image;

import com.bilmatch.bilfoot.models.Position;
import com.bilmatch.bilfoot.view.main_fragments.Player;

import java.util.List;

public class PlayerAnnouncement  extends Announcement{

    private List<String> positions;

    public PlayerAnnouncement() {
        announcementType = PLAYER;
    }

    public void setPositions(List<String> positions) {
        this.positions = positions;
    }

    public List<String> getPositions() {
        return positions;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public Image getImage() {
        return null;
    }

}
