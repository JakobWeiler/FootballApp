package at.footballapp.captainandroid.footballapp.pkgData;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Pascal on 24.03.2017.
 */

public class Database {
    private Player currentPlayer = null;
    private ArrayList<Match> matches = null;
    private ArrayList<Player> players = null;
    private static Database singeltonDB = null;

    public static Database newInstance(){
        if(singeltonDB == null){
            singeltonDB = new Database();
        }

        return  singeltonDB;
    }

    private Database(){
        matches = new ArrayList<Match>();
        players = new ArrayList<Player>();
    }

    public void addMatch(Match match){
        matches.add(match);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public Match getMatch(Date date){
        //TODO: implement get (depends on the collection type)
        return null;
    }

    public Player getPlayer(int id){
        //TODO: implement get (depends on the collection type)
        return null;
    }
}
