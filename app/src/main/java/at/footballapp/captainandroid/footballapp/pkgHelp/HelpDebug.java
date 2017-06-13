package at.footballapp.captainandroid.footballapp.pkgHelp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Christopher on 08.06.2017.
 */

public class HelpDebug {

    public static void debugArrayList(Collection<?> col, String tag, String header){
        Log.d(tag, "-------testingObjectOfType: " + header + " Start--------");

        for(Object o : col){
            Log.d(tag, o.toString());
        }
        Log.d(tag, "-------testingObjectOfType: " + header + " End--------");
    }
}
