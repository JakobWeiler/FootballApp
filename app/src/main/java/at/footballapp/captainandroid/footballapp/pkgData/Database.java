package at.footballapp.captainandroid.footballapp.pkgData;

import com.google.gson.Gson;

import java.util.Date;

import at.footballapp.captainandroid.footballapp.pkgController.ControllerStatistic;

/**
 * Auhtor: Pascal
 * Date: 24.03.2017
 */

public class Database {
    private Player currentPlayer = null;
    private static Database singletonDB = null;
    private Gson gson = null;
    private ControllerStatistic controller = null;
    private static final String URL = "";

    public static Database newInstance(){
        if(singletonDB == null){
            singletonDB = new Database();
        }

        return  singletonDB;
    }

    private Database(){
        gson = new Gson();
        controller = new ControllerStatistic();
    }

    //TODO: implement addMatch
    public void addMatch(Match match){

    }

    public void addPlayer(Player player) throws Exception{
        String paras[] = new String[3];
        paras[0] = "POST";
        paras[1] = gson.toJson(player);
        paras[2] = "/player";

        controller.execute(paras);
        //Method, URL, value, ...(parametersQuery)

        if((controller.get()).equals("200")){
            System.out.println("ok");
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

    public static String getUrl(){
        return URL;
    }
}