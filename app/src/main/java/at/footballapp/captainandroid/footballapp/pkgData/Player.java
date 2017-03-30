package at.footballapp.captainandroid.footballapp.pkgData;

import java.util.ArrayList;
import java.util.HashMap;

import at.footballapp.captainandroid.footballapp.pkgMisc.EnumPositions;

/**
 * Auhtors: P. L. Lagger, C. P. Wutti
 * Date: 23.03.2017
 */

public class Player {
    private int id;
    private boolean isAdmin = false;
    private ArrayList<EnumPositions> positions = null;
    private String name;
    private ArrayList<Match> attendedMatches = null;
    private HashMap<Integer, Statistic> statistics = null;

    /**
     * author: C. P. Wutti
     * Main Constructor
     * @param _id
     * @param _isAdmin
     * @param _name
     * @param _attendedMatches
     * @param _statistics
     */

    public Player (int _id, boolean _isAdmin, ArrayList<EnumPositions> _positions, String _name, ArrayList<Match> _attendedMatches, HashMap<Integer, Statistic> _statistics){
        super();
        setId(_id);
        setIsAdmin(_isAdmin);
        setPositions(_positions);
        setName(_name);
        setAttendedMatches(_attendedMatches);
        setStatistics(_statistics);
    }

    /**
     * author: C. P. Wutti
     * Default constructor
     */
    public Player(){
        this(-1, false, null, "", null, null);
    }

    /**
     * author: C. P. Wutti
     * Constructor without id
     * @param _isAdmin
     * @param _name
     * @param _positions
     * @param _attendedMatches
     * @param _statistics
     */
    public Player(boolean _isAdmin, String _name, ArrayList<EnumPositions> _positions,ArrayList<Match> _attendedMatches, HashMap<Integer, Statistic> _statistics){
        this(-1, false, _positions, _name, _attendedMatches, _statistics);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    public ArrayList<EnumPositions> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<EnumPositions> positions) {
        this.positions = positions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Match> getAttendedMatches() {
        return attendedMatches;
    }

    public void setAttendedMatches(ArrayList<Match> attendedMatches) {
        this.attendedMatches = attendedMatches;
    }

    public HashMap<Integer, Statistic> getStatistics() {
        return statistics;
    }

    public void setStatistics(HashMap<Integer, Statistic> statistics) {
        this.statistics = statistics;
    }

    /**
     * Automatically generated equals
     * @param o should be of type Player
     * @return whether the two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (getId() != player.getId()) return false;
        if (isAdmin() != player.isAdmin()) return false;
        if (getPositions() != null ? !getPositions().equals(player.getPositions()) : player.getPositions() != null)
            return false;
        if (getName() != null ? !getName().equals(player.getName()) : player.getName() != null)
            return false;
        if (getAttendedMatches() != null ? !getAttendedMatches().equals(player.getAttendedMatches()) : player.getAttendedMatches() != null)
            return false;
        return getStatistics() != null ? getStatistics().equals(player.getStatistics()) : player.getStatistics() == null;

    }

    /**
     * Automatically generated hashCode
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (isAdmin() ? 1 : 0);
        result = 31 * result + (getPositions() != null ? getPositions().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAttendedMatches() != null ? getAttendedMatches().hashCode() : 0);
        result = 31 * result + (getStatistics() != null ? getStatistics().hashCode() : 0);
        return result;
    }
}
