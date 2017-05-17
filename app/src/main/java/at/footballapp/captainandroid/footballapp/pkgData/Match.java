package at.footballapp.captainandroid.footballapp.pkgData;

import java.util.ArrayList;
import java.sql.Date;

/**
 * Authors: P. L. Lagger, C. P. Wutti
 * Date: 23.03.2017
 */

public class Match {
    private int id = -1;
    private Date date = null;
    private int goalsA;
    private int goalsB;
    private ArrayList<Player> teamA = null;
    private ArrayList<Player> teamB = null;

    /**
     * author: C. P. Wutti
     * Main constructor
     * @param _id
     * @param _date
     * @param _goalsA
     * @param _goalsB
     * @param _teamA
     * @param _teamB
     */
    public Match(int _id, Date _date, int _goalsA, int _goalsB, ArrayList<Player> _teamA, ArrayList<Player> _teamB){
        super();
        setId(_id);
        setDate(_date);
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
        this(-1, null, 0, 0, null, null);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    /**
     * Automatically generated equals
     * @param o should be of type Match
     * @return whether the two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (getId() != match.getId()) return false;
        if (getGoalsA() != match.getGoalsA()) return false;
        if (getGoalsB() != match.getGoalsB()) return false;
        if (getDate() != null ? !getDate().equals(match.getDate()) : match.getDate() != null)
            return false;
        if (getTeamA() != null ? !getTeamA().equals(match.getTeamA()) : match.getTeamA() != null)
            return false;
        return getTeamB() != null ? getTeamB().equals(match.getTeamB()) : match.getTeamB() == null;

    }

    /**
     * Automatically generated hashCode
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + getGoalsA();
        result = 31 * result + getGoalsB();
        result = 31 * result + (getTeamA() != null ? getTeamA().hashCode() : 0);
        result = 31 * result + (getTeamB() != null ? getTeamB().hashCode() : 0);
        return result;
    }
}
