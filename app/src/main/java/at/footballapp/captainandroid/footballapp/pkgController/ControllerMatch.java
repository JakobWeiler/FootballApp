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

public class ControllerMatch extends AsyncTask<Object, Void, String> {
    private static final String URL = Database.getUrl();
    private static final String contentType = Database.getContentType();
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
            /*Wutti*/
            if(command[0].equals("POST")){
                if(command[1].equals("/match")){
                    String newMatch = gson.toJson(command[2]);

                    url = new URL(URL + command[1]);

                    HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestMethod(command[0].toString());
                    urlConnection.setRequestProperty("Content-Type", contentType);

                    byte[] outputBytes = newMatch.getBytes("UTF-8");
                    urlConnection.setRequestProperty("Content-Length", Integer.toString(outputBytes.length));
                    OutputStream os = urlConnection.getOutputStream();
                    os.write(outputBytes);

                    reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    response = sb.toString();

                    os.flush();
                    os.close();
                    urlConnection.disconnect();
                }else{

                }
            /*Lagger*/
            }else if(command[0].equals("PUT")){
                if(command[1].equals("/match")){
                    String updatedMatch = gson.toJson(command[2]);
                    url = new URL(URL + command[1]);

                    HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestMethod(command[0].toString());
                    urlConnection.setRequestProperty("Content-Type", contentType);

                    byte[] outputBytes = updatedMatch.getBytes("UTF-8");
                    urlConnection.setRequestProperty("Content-Length", Integer.toString(outputBytes.length));
                    OutputStream os = urlConnection.getOutputStream();
                    os.write(outputBytes);

                    response = Integer.toString(urlConnection.getResponseCode());
                }
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }

        return response;
    }
}
