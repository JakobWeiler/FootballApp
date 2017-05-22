package at.footballapp.captainandroid.footballapp.pkgController;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import at.footballapp.captainandroid.footballapp.pkgData.Database;

/**
 * Auhtor: Pascal
 * Date: 03.05.2017
 */

public class ControllerMatch extends AsyncTask<Object, Void, Object> {
    private static final String URL = Database.getUrl();
    private Gson gson = null;

    @Override
    protected String doInBackground(Object... command) {
        BufferedReader reader = null;
        String response = null;
        java.net.URL url = null;
        BufferedWriter writer = null;
        String retVal = null;
        gson = new Gson();

        try{
            if(command[0].equals("POST")){
                if(command[1].equals("/match")){
                    String newMatch = gson.toJson(command[2]);

                    Log.d("matchAdding", newMatch);

                    url = new URL(URL + command[1]);

                    HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/json");

                    byte[] outputBytes = newMatch.getBytes("UTF-8");
                    urlConnection.setRequestProperty("Content-Length", Integer.toString(outputBytes.length));
                    OutputStream os = urlConnection.getOutputStream();
                    os.write(outputBytes);

                    response = Integer.toString(urlConnection.getResponseCode());

                    os.flush();
                    os.close();
                    urlConnection.disconnect();
           }else{

                }
            }else{

            }
        } catch(Exception ex){
            ex.printStackTrace();
        }

        return response;
    }
}
