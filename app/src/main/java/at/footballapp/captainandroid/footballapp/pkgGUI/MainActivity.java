package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import at.footballapp.captainandroid.footballapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spPlayer = (Spinner) findViewById(R.id.spPlayer);
        ArrayList<String> player = new ArrayList<String>();
        player.add("Player");
        player.add("Some players");
        ArrayAdapter<String> adapterPlayer = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                player
        );

        spPlayer.setAdapter(adapterPlayer);

        Spinner spMatch = (Spinner) findViewById(R.id.spMatch);
        ArrayList<String> match = new ArrayList<String>();
        match.add("Match");
        match.add("Some matches");
        ArrayAdapter<String> adapterMatch = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                match
        );

        spMatch.setAdapter(adapterMatch);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_profile: startActivity(new Intent(MainActivity.this, ProfileActivity.class)); break;
            case R.id.navigation_match: startActivity(new Intent(MainActivity.this, MatchActivity.class)); break;
            case R.id.navigation_statistic: startActivity(new Intent(MainActivity.this, StatisticActivity.class)); break;
        }
        return true;
    }

}
