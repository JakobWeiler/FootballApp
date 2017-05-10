package at.footballapp.captainandroid.footballapp.pkgData;

import android.util.Log;

import java.util.Date;

import at.footballapp.captainandroid.footballapp.pkgController.ControllerPlayer;

/**
 * Auhtor: Pascal
 * Date: 24.03.2017
 */

public class Database {
    private Player_old currentPlayer = null;
    private static Database singletonDB = null;
    private ControllerPlayer controllerPlayer = null;
    private static final String URL = "http://192.168.142.143:8080/Soccer_WebService/resources";

    public static Database newInstance(){
        if(singletonDB == null){
            singletonDB = new Database();
        }

        return  singletonDB;
    }

    private Database(){
        controllerPlayer = new ControllerPlayer();
    }

    //TODO: implement addMatch
    public void addMatch(Match match){

    }

    public void addPlayer(Player player)throws Exception{
        Object paras[] = new Object[3];
        paras[0] = "POST";
        paras[1] = "/player";
        paras[2] = player;

        controllerPlayer.execute(paras);
        //Method, URL, value, ...(parametersQuery)

        if((controllerPlayer.get()).equals("200")){
            Log.d("output", "player added successfully");
        }
    }

    //TODO: implement getMatch
    public Match getMatch(Date date){
        return null;
    }

    //TODO: implement getPlayer
    public Player_old getPlayer(int id){
        return null;
    }

    public static String getUrl(){
        return URL;
    }
}