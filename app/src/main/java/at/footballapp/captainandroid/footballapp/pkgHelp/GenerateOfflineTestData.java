package at.footballapp.captainandroid.footballapp.pkgHelp;

import at.footballapp.captainandroid.footballapp.pkgData.Database;
import at.footballapp.captainandroid.footballapp.pkgData.Match;
import at.footballapp.captainandroid.footballapp.pkgData.Player;

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
        initPlayer();
        initMatches();
    }

    /**
     * creates 22 Players
     */
    private static void initPlayer(){
        for(int i = 0; i < 22; i++){
            Player p = new Player();

            dbInstance.addPlayer(p);
        }
    }

    /**
     * creates 6 Matches
     */
    private static void initMatches(){
        dbInstance.addMatch(new Match());
        dbInstance.addMatch(new Match());
        dbInstance.addMatch(new Match());
        dbInstance.addMatch(new Match());
        dbInstance.addMatch(new Match());
        dbInstance.addMatch(new Match());
    }

    private static void initStatistics(Player p){
        //Todo add statistics when class Statistic is implemented
    }
}
