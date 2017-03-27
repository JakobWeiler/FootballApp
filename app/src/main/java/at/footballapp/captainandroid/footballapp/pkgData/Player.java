package at.footballapp.captainandroid.footballapp.pkgData;

import java.util.ArrayList;

import at.footballapp.captainandroid.footballapp.pkgMisc.EnumPositions;

/**
 * Created by Pascal on 23.03.2017.
 */

public class Player {
    private ArrayList<Match> attendedMatches = null;
    private int id;
    private String name;
    private boolean isAdmin = false;
    private ArrayList<EnumPositions> positions = null;


}
