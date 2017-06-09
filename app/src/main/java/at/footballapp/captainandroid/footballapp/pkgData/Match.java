package at.footballapp.captainandroid.footballapp.pkgData;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;

import at.footballapp.captainandroid.footballapp.pkgHelp.SqlDateHelper;

/**
 * Authors: P. L. Lagger, C. P. Wutti
 * Date: 23.03.2017
 */

public class Match implements Serializable{
    private int id = -1;
    private String firmDate = null;
    private int goalsA;
    private int goalsB;
    private transient ArrayList<Player> teamA = null;
    private transient ArrayList<Player> teamB = null;

    /**
     * author: C. P. Wutti
     * Main constructor
     * @param _id
     * @param _firmDate
     * @param _goalsA
     * @param _goalsB
     * @param _teamA
     * @param _teamB
     */
    public Match(int _id, String _firmDate, int _goalsA, int _goalsB, ArrayList<Player> _teamA, ArrayList<Player> _teamB){
        super();
        setId(_id);
        setFirmDate(_firmDate);
        setGoalsA(_goalsA);
        setGoalsB(_goalsB);
        setTeamA(_teamA);
        setTeamB(_teamB);
    }

    /**
     * author: C. P. Wutti
     * defualt constructor
     */
    public Match(){
        this(-1, null, 0, 0, new ArrayList<Player>(), new ArrayList<Player>());
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirmDate() {
        return firmDate;
    }

    public void setFirmDate(String firmDate) {
        try{this.firmDate = SqlDateHelper.dateToString(SqlDateHelper.getDate(firmDate), "yyyy-mm-dd");}catch (Exception e){/*no way*/}
    }

    public int getGoalsA() {
        return goalsA;
    }

    public void setGoalsA(int goalsA) {
        this.goalsA = goalsA;
    }

    public int getGoalsB() {
        return goalsB;
    }

    public void setGoalsB(int goalsB) {
        this.goalsB = goalsB;
    }

    public ArrayList<Player> getTeamA() {
        return teamA;
    }

    public void setTeamA(ArrayList<Player> teamA) {
        this.teamA = teamA;
    }

    public ArrayList<Player> getTeamB() {
        return teamB;
    }

    public void setTeamB(ArrayList<Player> teamB) {
        this.teamB = teamB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        return id == match.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", firmDate='" + firmDate + '\'' +
                ", goalsA=" + goalsA +
                ", goalsB=" + goalsB +
                ", teamA=" + teamA +
                ", teamB=" + teamB +
                '}';
    }

    public void addToTeamA(Player p) {
        if(!teamA.contains(p)) teamA.add(p);
    }

    public void removeFromTeamB(Player p) {
        if(teamB.contains(p)) teamB.remove(p);
    }

    public void addToTeamB(Player p) {
        if(!teamB.contains(p))  teamB.add(p);
    }

    public void removeFromTeamA(Player p) {
        if(teamA.contains(p)) teamA.remove(p);
    }
}
