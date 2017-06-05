package at.footballapp.captainandroid.footballapp.pkgController;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import at.footballapp.captainandroid.footballapp.pkgData.Database;
import at.footballapp.captainandroid.footballapp.pkgData.Match;
import at.footballapp.captainandroid.footballapp.pkgData.Participation;
import at.footballapp.captainandroid.footballapp.pkgData.Player;

/**
 * Created by Christopher on 01.06.2017.
 */

public class ControllerParticipation extends AsyncTask<Object, Void, String>{
    private static final String URL = Database.getUrl();
    private Gson gson;

    @Override
    protected String doInBackground(Object... command) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        java.net.URL url = null;
        String response = null;
        gson = new Gson();

        ArrayList<Participation> participations = prepeareParticipations((Match)command[2]);

        try {
            if(command[0].equals("POST")){
                for(Participation p : participations){
                    String newParticipation = gson.toJson(p);

                    url = new URL(URL + command[1]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/json");

                    byte[] outputBytes = newParticipation.getBytes("UTF-8");
                    urlConnection.setRequestProperty("Content-Length", Integer.toString(outputBytes.length));
                    OutputStream os = urlConnection.getOutputStream();
                    os.write(outputBytes);

                    Log.d("response", urlConnection.getResponseMessage());
                    response = Integer.toString(urlConnection.getResponseCode());

                    os.flush();
                    os.close();
                    urlConnection.disconnect();

                }
            }else if(command[0].equals("PUT")){
                for(Participation p : participations){
                    String newParticipation = gson.toJson(p);

                    url = new URL(URL + command[1]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/json");

                    byte[] outputBytes = newParticipation.getBytes("UTF-8");
                    urlConnection.setRequestProperty("Content-Length", Integer.toString(outputBytes.length));
                    OutputStream os = urlConnection.getOutputStream();
                    os.write(outputBytes);

                    Log.d("response", urlConnection.getResponseMessage());
                    response = Integer.toString(urlConnection.getResponseCode());

                    os.flush();
                    os.close();
                    urlConnection.disconnect();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    private ArrayList<Participation> prepeareParticipations(Match m){
        ArrayList<Participation> prepedParticipations = new ArrayList<Participation>();

        for(Player p : m.getTeamA()){
            prepedParticipations.add(new Participation(p.getId(), m.getId(), -1, String.valueOf("A")));
        }

        for(Player p: m.getTeamB()){
            prepedParticipations.add(new Participation(p.getId(), m.getId(), -1, String.valueOf("B")));
        }

        return prepedParticipations;
    }

    private void testPrepdParticipations(ArrayList<Participation> part){
        Log.d("TESTPART", String.valueOf("------------NPART-----------"));

        for(Participation p: part){
            Log.d("TESTPART", p.toString());
        }

        Log.d("TESTPART", String.valueOf("------------EPART-----------"));
    }
}
