package at.footballapp.captainandroid.footballapp.pkgController;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import at.footballapp.captainandroid.footballapp.pkgData.Database;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Jakob on 18.05.2017.
 */

public class ControllerOccupation extends AsyncTask<Object, Void, String> {
    private static final String URL = Database.getUrl();
    private Gson gson;

    @Override
    protected String doInBackground(Object... command) {    //(Method, URL, value)
        BufferedReader reader = null;
        BufferedWriter writer = null;
        URL url = null;
        String response = null;
        gson = new Gson();

        try{
            if(command[0].equals("POST")){
                String newOccupation = gson.toJson(command[2]);

                url = new URL(URL + command[1]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");

                byte[] outputBytes = newOccupation.getBytes("UTF-8");
                urlConnection.setRequestProperty("Content-Length", Integer.toString(outputBytes.length));
                OutputStream os = urlConnection.getOutputStream();
                os.write(outputBytes);

                Log.d("response", urlConnection.getResponseMessage());
                response = Integer.toString(urlConnection.getResponseCode());

                os.flush();
                os.close();
                urlConnection.disconnect();

            } else if (command[0].equals("DELETE")){
                url = new URL(URL + command[1] + "?playerId=" + command[2] + "&positionName=" + command[3]);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("DELETE");

                response = Integer.toString(urlConnection.getResponseCode());

            } else if (command[0].equals("GET")) {
                url = new URL(URL + command[1]);
                URLConnection conn = url.openConnection();

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                response = sb.toString();
                reader.close();
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }

        return response;
    }
}
