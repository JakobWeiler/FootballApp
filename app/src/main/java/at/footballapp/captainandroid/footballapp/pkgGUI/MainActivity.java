package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgController.ControllerPlayer;
import at.footballapp.captainandroid.footballapp.pkgData.Database;
import at.footballapp.captainandroid.footballapp.pkgData.Match;
import at.footballapp.captainandroid.footballapp.pkgData.Player;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RemoveDialogActivity.OnPlayerRemovedListener, AddPlayerActivity.OnPlayerAddedListener {

    private ControllerPlayer controller = null;
    private Database db = null;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("FootballApp");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = Database.newInstance();
        sp = getSharedPreferences("footballApp", MODE_PRIVATE);
        String uname = sp.getString("uname", null);
        String passwd = sp.getString("passwd", null);

        if(uname != null){
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("uname", uname);
            editor.putString("passwd", passwd);
            Log.d("output","SET");
        } else {
            Log.d("output", "logged in");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Spinner spMatch = (Spinner) findViewById(R.id.spMatch);
        ArrayList<String> match = new ArrayList<>();
        match.add("Match");
        match.add("Some matches");
        ArrayAdapter<String> adapterMatch = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                match
        );

        spMatch.setAdapter(adapterMatch);

        try {
            db.loadAllPlayers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setAdapterSpinnerPlayer();

        RemoveDialogActivity.addOnPlayerRemovedListener(this);
        AddPlayerActivity.addOnPlayerAddedListener(this);
    }

    private void setAdapterSpinnerPlayer() {
        Spinner spPlayer = (Spinner) findViewById(R.id.spPlayer);
        ArrayAdapter<Player> adapterPlayer = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                db.getAllPlayers()
        );

        spPlayer.setAdapter(adapterPlayer);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_addPlayer) {
            startActivity(new Intent(MainActivity.this, AddPlayerActivity.class));
        } else if (id == R.id.nav_removePlayer) {
            startActivity(new Intent(MainActivity.this, RemovePlayerActivity.class));
        } else if (id == R.id.nav_Profile) {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            Spinner spPlayer = (Spinner) findViewById(R.id.spPlayer);
            intent.putExtra("playerName", ((Player)spPlayer.getSelectedItem()).getName());
            startActivity(intent);
        } else if (id == R.id.nav_addMatch) {
            //Database.newInstance().addMatch(new Match(todaysDate));   -->Select in spinner
            /*Intent intent = new Intent(MainActivity.this, UpdateMatchActivity.class);
            Spinner spMatch = (Spinner) findViewById(R.id.spMatch);
            intent.putExtra("intentMatchId", ((Match)spMatch.getSelectedItem()).getId());
            startActivity(intent);*/
            Intent intent = new Intent(MainActivity.this, UpdateMatchActivity.class);
            intent.putExtra("ADDMATCH", "ADDMATCH");
            startActivity(intent);
        } else if (id == R.id.nav_removeMatch) {
            startActivity(new Intent(MainActivity.this, RemoveMatchActivity.class));
        } else if (id == R.id.nav_updateMatch) {
            Intent intent = new Intent(MainActivity.this, UpdateMatchActivity.class);
            Spinner spMatch = (Spinner) findViewById(R.id.spMatch);
            //intent.putExtra("intentMatchId", ((Match)spMatch.getSelectedItem()).getId());
            startActivity(intent);
        } else if (id == R.id.nav_showMatch) {
            startActivity(new Intent(MainActivity.this, MatchActivity.class));
        } else if (id == R.id.nav_showStatistic) {
            startActivity(new Intent(MainActivity.this, StatisticActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void handlePlayerRemoved() {
        setAdapterSpinnerPlayer();
    }

    @Override
    public void handlePlayerAdded() {
        setAdapterSpinnerPlayer();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_menu = navigationView.getMenu();

        if(db.getCurrentPlayer().getIsAdmin() == 0){
            nav_menu.findItem(R.id.nav_addPlayer).setVisible(false);
            nav_menu.findItem(R.id.nav_removePlayer).setVisible(false);
            nav_menu.findItem(R.id.nav_addMatch).setVisible(false);
            nav_menu.findItem(R.id.nav_removeMatch).setVisible(false);
            nav_menu.findItem(R.id.nav_updateMatch).setVisible(false);
        }
        else {
            nav_menu.findItem(R.id.nav_showStatistic).setVisible(false);
        }
        return true;
    }
}
