package at.footballapp.captainandroid.footballapp.pkgController;

import android.os.AsyncTask;

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

public class ControllerMatch extends AsyncTask<String, Void, String> {
    private static final String URL = Database.getUrl();

    @Override
    protected String doInBackground(String... command) {
        BufferedReader reader = null;
        String content = null;
        java.net.URL url = null;
        BufferedWriter writer = null;
        String retVal = null;

        try{
            //TODO:Add match methods
        } catch(Exception ex){
            ex.printStackTrace();
        }

        return retVal;
    }
}
