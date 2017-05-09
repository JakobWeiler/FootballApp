package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgData.Player;

public class RemovePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_player);

        ListView lvPlayer = (ListView) findViewById(R.id.lvPlayer);
        ArrayList<String> player = new ArrayList<String>();
        player.add("Player");
        player.add("Some players");
        ArrayAdapter<String> adapterPlayer = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                player
        );

        lvPlayer.setAdapter(adapterPlayer);
    }
}
