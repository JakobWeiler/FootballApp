package at.footballapp.captainandroid.footballapp.pkgController;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import at.footballapp.captainandroid.footballapp.pkgData.Database;


/**
 * Auhtor: Pascal
 * Date: 03.05.2017
 */

public class ControllerPlayer extends AsyncTask<String, Void, String> {
    private static final String URL = Database.getUrl();

    @Override
    protected String doInBackground(String... command) {    //(Method, URL, value)
        BufferedReader reader = null;
        BufferedWriter writer = null;
        URL url = null;
        String response = null;

        try{
            if(command[0].equals("POST")){
                if (command[1].equals("/player")) { //add player
                    String newPlayer = command[2];

                    url = new URL(URL + command[1]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type","application/json");

                    OutputStream os = urlConnection.getOutputStream();
                    writer = new BufferedWriter( new OutputStreamWriter(os, "UTF-8"));
                    writer.write(newPlayer);

                    writer.flush();
                    writer.close();
                    os.close();

                    response = Integer.toString(urlConnection.getResponseCode());
                } else {
                    //get player /player/auth
                }
            } else if (command[0].equals("DELETE")){

            } else if (command[0].equals("GET")){
                //get all players
            } else if (command[0].equals("PUT")){
                //update player
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }

        return response;
    }
}
