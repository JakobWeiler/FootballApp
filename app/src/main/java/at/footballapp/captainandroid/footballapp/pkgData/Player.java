/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.footballapp.captainandroid.footballapp.pkgData;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import at.footballapp.captainandroid.footballapp.pkgMisc.EnumPositions;

/**
 *
 * @author schueler
 */
public class Player implements Serializable{
    
    private int id;
    private String name;
    private String password;
    private int isAdmin;
    private int wins = 0;
    private int tieds = 0;
    private int losses = 0;
    private int goalDifference = 0;
    private transient ArrayList<EnumPositions> positions = null;
    private transient ArrayList<Match> attendedMatches = null;
    private transient HashMap<Integer, Statistic> statistics = null;

    public Player(){}
    
    public Player(int id, String name, String pw, int isAdmin, int wins, int tieds, int losses, int goalDifference) {
        this(name, pw, isAdmin);
        this.id = id;
        this.isAdmin = isAdmin;
        this.wins = wins;
        this.tieds = tieds;
        this.losses = losses;
        this.goalDifference = goalDifference;
        this.positions = new ArrayList<EnumPositions>();
        this.attendedMatches = new ArrayList<Match>();
        this.statistics = new HashMap<Integer, Statistic>();
    }

    public Player(String name, String pw, int isAdmin){
        this(name, pw);
        this.isAdmin = isAdmin;
    }

    public Player(String name ){
        this.name = name;
    }

    public Player (String name, String pw){
        this(name);
        this.password = pw;
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<EnumPositions> getPositions(){ return positions;}

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getTieds() {
        return tieds;
    }

    public void setTieds(int tieds) {
        this.tieds = tieds;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    @Override
    public String toString() {
        return name + ", " + goalDifference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
