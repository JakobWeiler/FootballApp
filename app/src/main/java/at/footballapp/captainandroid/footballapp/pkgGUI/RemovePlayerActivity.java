package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgData.Database;
import at.footballapp.captainandroid.footballapp.pkgData.Player;

/*Weiler*/
public class RemovePlayerActivity extends AppCompatActivity {
    private Database db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_player);
        db = Database.newInstance();

        final ListView lvPlayer = (ListView) findViewById(R.id.lvPlayer);

        setAdapterLvPlayer();

        lvPlayer.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item value
                Player item = (Player) lvPlayer.getItemAtPosition(position);

                Intent intent = new Intent(RemovePlayerActivity.this, RemoveDialogActivity.class);
                intent.putExtra("id", item.getId());
                intent.putExtra("name", item.getName());
                intent.putExtra("goalDifference", item.getGoalDifference());
                startActivity(intent);

            }

        });
    }

    private void setAdapterLvPlayer() {
        ListView lvPlayer = (ListView) findViewById(R.id.lvPlayer);
        ArrayAdapter<Player> adapterPlayer = new ArrayAdapter<Player>(
                this,
                android.R.layout.simple_spinner_item,
                db.getAllPlayers()
        );

        lvPlayer.setAdapter(adapterPlayer);
    }

    @Override
    public void onResume() {
        super.onResume();
        setAdapterLvPlayer();
    }
}
