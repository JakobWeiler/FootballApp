/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.footballapp.captainandroid.footballapp.pkgData;

import java.io.Serializable;

/**
 *
 * @author Wutti
 */
public class Participation implements Serializable{
    private int playerId;
    private int matchID;
    private int statisticId;
    private String team;
    
    public Participation(){}

    public Participation(int playerId, int matchID, int statisticId, String team) {
        this.playerId = playerId;
        this.matchID = matchID;
        this.statisticId = statisticId;
        this.team = team;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchId) {
        this.matchID = matchId;
    }

    public int getStatisticId() {
        return statisticId;
    }

    public void setStatisticId(int statisticId) {
        this.statisticId = statisticId;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Participation{" + "playerId=" + playerId + ", matchId=" + matchID + ", statisticId=" + statisticId + ", team=" + team + '}';
    }
}
