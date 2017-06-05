package at.footballapp.captainandroid.footballapp.pkgData;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.sql.Date;
import java.util.concurrent.ExecutionException;

import at.footballapp.captainandroid.footballapp.pkgController.ControllerMatch;
import at.footballapp.captainandroid.footballapp.pkgController.ControllerOccupation;
import at.footballapp.captainandroid.footballapp.pkgController.ControllerParticipation;
import at.footballapp.captainandroid.footballapp.pkgController.ControllerPlayer;
import at.footballapp.captainandroid.footballapp.pkgGUI.MainActivity;

/**
 * Auhtor: Pascal
 * Date: 24.03.2017
 */

public class Database {
    private Player currentPlayer = null;
    private static Database singletonDB = null;
    //private static final String URL = "http://212.152.179.116:8080/Soccer_Webservice/resources";
    private static final String URL = "http://192.168.142.143:8080/Soccer_Webservice/resources";
    private Gson gson;
    private ArrayList<Player> allPlayers = null;
    private ArrayList<Occupation> occupations = null;
    private ControllerPlayer controllerPlayer = null;
    private ControllerOccupation controllerOccupation = null;
    private ArrayList<Match> matches = null;
    private ControllerMatch controllerMatch = null;
    private SharedPreferences sp;
    private Match currentMatch = null;

    public static Database newInstance(){
        if(singletonDB == null){
            singletonDB = new Database();
        }

        return  singletonDB;
    }

    private Database(){
        gson = new Gson();
        matches = new ArrayList<>();
    }

    //TODO: implement addMatch
    /*public void addMatch(Match match){
        matches.add(match);
    }*/

    public void addMatch(Match match) throws Exception{
        controllerMatch = new ControllerMatch();

        Object paras[] = new Object[3];
        paras[0] = "POST";
        paras[1] = "/match";
        paras[2] = match;

        controllerMatch.execute(paras);

        Match m = gson.fromJson((controllerMatch.get().toString()), Match.class);

        matches.add(m);
        setCurrentMatch(m);

        /*if(!(controllerMatch.get()).equals("200")){
            Log.d("ERRORMATCH", controllerMatch.get().toString());
            throw new Exception("Webservice problem --add match");
        }*/
    }

    public void addPlayer(Player player)throws Exception{

        controllerPlayer = new ControllerPlayer();

        Object paras[] = new Object[3];
        paras[0] = "POST";
        paras[1] = "/player";
        paras[2] = player;

        controllerPlayer.execute(paras);
        //Method, URL, value, ...(parametersQuery)

        allPlayers.add(gson.fromJson(controllerPlayer.get(), Player.class));
    }

    public void removePlayer(int id, String name) throws Exception {
        controllerPlayer = new ControllerPlayer();

        String paras[] = new String[3];
        paras[0] = "DELETE";
        paras[1] = "/player";
        paras[2] = Integer.toString(id);

        controllerPlayer.execute(paras);

        if(!(controllerPlayer.get()).equals("200")){
            throw new Exception("webservice problem --removePlayer");
        }

        allPlayers.remove(new Player(name));
    }

    private void addOccupation(int playerId, String positionName)throws Exception{

        controllerOccupation = new ControllerOccupation();

        Object paras[] = new Object[3];
        paras[0] = "POST";
        paras[1] = "/occupation";
        paras[2] = new Occupation(playerId, positionName);

        controllerOccupation.execute(paras);

        if(!(controllerOccupation.get()).equals("200")){
            throw new Exception("webservice problem --addOccupation");
        }
    }

    private void removeOccupation(int playerId, String positionName) throws Exception {
        controllerOccupation = new ControllerOccupation();

        Object paras[] = new Object[4];
        paras[0] = "DELETE";
        paras[1] = "/occupation";
        paras[2] = playerId;
        paras[3] = positionName;

        controllerOccupation.execute(paras);

        if(!(controllerOccupation.get()).equals("200")){
            throw new Exception("webservice problem --removeOccupation");
        }

    }

    public void addOrUpdateParticipation(Match m, boolean firstTime) throws Exception{
        ControllerParticipation controllerPart = new ControllerParticipation();

        Object paras[] = new Object[5];

        if(firstTime){
            paras[0] = "POST";
            paras[1] = "/participation";
            paras[2] = m;
        }else{
            paras[0] = "PUT";
            paras[1] = "/participation";
            paras[2] = m;
        }

        controllerPart.execute(paras);
    }

    //TODO: implement getMatch
    public Match getMatch(Date date){
        //TODO: implement get (depends on the collection type)
        //current implementation for ArrayList
        int i;
        boolean objectFound = false;


        for(i = 0; !objectFound; i++)
            if(date.equals(matches.get(i).getFirmDate()))
                objectFound = true;

        //terrible solution
         if(matches.size() >= 2) return matches.get(i - 1); else return new Match();
    }

    //TODO: implement getPlayer
    public Player getPlayer(int id){
        return null;
    }

    public void loadAllPlayers() throws Exception {

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

        loadAllOccupations();
    }

    private void loadAllOccupations() throws Exception {
        for (Player player : allPlayers) {
            loadOccupations(player);
        }
    }

    private void loadOccupations(Player player) throws Exception {
        controllerOccupation = new ControllerOccupation();

        String paras[] = new String[2];
        paras[0] = "GET";
        paras[1] = "/occupation/" + player.getId();
        controllerOccupation.execute(paras);
        final String result = controllerOccupation.get();

        if(result == null){
            throw new Exception("webservice problem --loadOccupations");
        }

        Thread t = new Thread(new Runnable() {
            public void run() {
                Type occupationsListType = new TypeToken<ArrayList<Occupation>>(){}.getType();
                occupations = gson.fromJson(result, occupationsListType);
            }
        });

        t.start();
        t.join();

        if (occupations.get(0).getPlayerId() != -1) {
            for (Occupation o : occupations) {
                player.addPosition(o.getPositionName(), true);
            }
        }
    }

    public boolean authUser(Player p)throws Exception{
        boolean valid = false;

        controllerPlayer = new ControllerPlayer();
        Log.d("output", p.toString());
        Object paras[] = new Object[3];
        paras[0] = "POST";
        paras[1] = "/player/auth";
        paras[2] = p;

        controllerPlayer.execute(paras);
        currentPlayer = gson.fromJson(controllerPlayer.get(), Player.class);

        return (currentPlayer.getId() != -1);
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public boolean nameUsed(String name){
        return allPlayers.contains(new Player(name));
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public static String getUrl(){
        return URL;
    }

    private void setCurrentMatch(Match match){currentMatch = match;}

    public Match getCurrentMatch(){return currentMatch;}
}