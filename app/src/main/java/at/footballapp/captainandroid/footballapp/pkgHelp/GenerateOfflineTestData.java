package at.footballapp.captainandroid.footballapp.pkgHelp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Date;
import at.footballapp.captainandroid.footballapp.pkgData.Database;
import at.footballapp.captainandroid.footballapp.pkgData.Match;
import at.footballapp.captainandroid.footballapp.pkgData.Player;
import at.footballapp.captainandroid.footballapp.pkgData.Statistic;
import at.footballapp.captainandroid.footballapp.pkgMisc.EnumPositions;

/**
 * author: C. P. Wutti
 */

public class GenerateOfflineTestData {

    private static Database dbInstance = null ;

    /**
     * calls all methods concerning the generation of the players, matches and statistics
     */
    public static void initGenerateOfflineTestData(){
        dbInstance = Database.newInstance();
        ArrayList<Player> players = initPlayer();
        initMatches(players);
    }

    /**
     * creates 22 Players
     */
    private static ArrayList<Player> initPlayer(){
        ArrayList<Player> players = new ArrayList<Player>();

        for(int i = 0; i < 22; i++){
            HashMap<Integer, Statistic> statistics = initStatistics();
            Player p = new Player(1,false, new ArrayList<EnumPositions>(Arrays.asList(EnumPositions.values())), "Player " + i, null, statistics);

            dbInstance.addPlayer(p);
            players.add(p);
        }
        HashMap<Integer, Statistic> statistics = initStatistics();
        Player p = new Player(1,true, new ArrayList<EnumPositions>(Arrays.asList(EnumPositions.values())), "Admin", null, statistics);

            players.add(p);
        return players;
    }

    /**
     * creates 6 Matches
     */
    private static void initMatches(ArrayList<Player> players){
        ArrayList<Player> listA = new ArrayList<Player>();
        ArrayList<Player> listB = new ArrayList<Player>();

        for(int i = 0; i < 11; i++){
            listA.add(players.get(i));
            listB.add(players.get(11-i));
        }

        Match m = null;

        m = new Match(1, (java.sql.Date) new Date(2017,10,9,10,4), 11,11, listA, listB);
        dbInstance.addMatch(m);

        m = new Match(1,(java.sql.Date)new Date(2017,9,9,10,4), 11,11, listA, listB);
        dbInstance.addMatch(m);

        m = new Match(1, (java.sql.Date)new Date(2016,10,9,10,4), 11,11, listA, listB);
        dbInstance.addMatch(m);

        m = new Match(1, (java.sql.Date)new Date(2017,04,20,10,4), 11,11, listA, listB);
        dbInstance.addMatch(m);

    }

    private static HashMap<Integer, Statistic> initStatistics(){
        HashMap<Integer, Statistic> helpHashMap = new HashMap<Integer, Statistic>();

        helpHashMap.put(1, new Statistic(1,1,1,1,1,1, EnumPositions.Striker));
        helpHashMap.put(2, new Statistic(2,2,2,2,2,2, EnumPositions.Defender));
        helpHashMap.put(3, new Statistic(3,3,3,3,3,3, EnumPositions.Midfielder));
        helpHashMap.put(4, new Statistic(4,4,4,4,4,4, EnumPositions.Goalkeeper));

        return helpHashMap;
    }
}
