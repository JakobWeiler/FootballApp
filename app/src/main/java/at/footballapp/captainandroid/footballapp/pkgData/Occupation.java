package at.footballapp.captainandroid.footballapp.pkgData;

import java.io.Serializable;

/*Weiler*/
public class Occupation implements Serializable {
    private int playerId = 0;
    private String positionName = null;

    public Occupation(int playerId, String positionName) {
        this.playerId = playerId;
        this.positionName = positionName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
