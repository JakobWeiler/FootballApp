package at.footballapp.captainandroid.footballapp.pkgData;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import at.footballapp.captainandroid.footballapp.pkgController.ControllerPlayer;
import at.footballapp.captainandroid.footballapp.pkgGUI.MainActivity;

/**
 * Auhtor: Pascal
 * Date: 24.03.2017
 */

public class Database {
    private Player currentPlayer = null;
    private static Database singletonDB = null;
    private static final String URL = "http://192.168.142.143:8080/Soccer_Webservice/resources";    //intern: 192.168.142.143   extern: 212.152.179.116
    private Gson gson;
    private ArrayList<Player> allPlayers = null;
    private ControllerPlayer controllerPlayer = null;

    public static Database newInstance(){
        if(singletonDB == null){
            singletonDB = new Database();
        }

        return  singletonDB;
    }

    private Database(){
        gson = new Gson();
    }

    //TODO: implement addMatch
    public void addMatch(Match match){

    }

    public void addPlayer(Player player)throws Exception{

        controllerPlayer = new ControllerPlayer();

        Object paras[] = new Object[3];
        paras[0] = "POST";
        paras[1] = "/player";
        paras[2] = player;

        controllerPlayer.execute(paras);
        //Method, URL, value, ...(parametersQuery)

        if(!(controllerPlayer.get()).equals("200")){
            throw new Exception("webservice problem --add");
        }
    }

    //TODO: implement getMatch
    public Match getMatch(Date date){
        return null;
    }

    //TODO: implement getPlayer
    public Player getPlayer(int id){
        return null;
    }

    public ArrayList<Player> getAllPlayers() throws Exception {

        controllerPlayer = new ControllerPlayer();

        String paras[] = new String[2];
        paras[0] = "GET";
        paras[1] = "/player";
        controllerPlayer.execute(paras);
        final String result = controllerPlayer.get();

        if(result == null){
            throw new Exception("webservice problem --getAllPlayers");
        }

        Thread t = new Thread(new Runnable() {
            public void run() {
                Type playerListType = new TypeToken<ArrayList<Player>>(){}.getType();
                allPlayers = gson.fromJson(result, playerListType);
            }
        });

        t.start();
        t.join();

        return allPlayers;
    }

    public boolean nameUsed(String name){
        return allPlayers.contains(new Player(name));
    }

    public static String getUrl(){
        return URL;
    }
}