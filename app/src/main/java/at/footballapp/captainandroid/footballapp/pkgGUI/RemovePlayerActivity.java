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

public class RemovePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_player);

        final ListView lvPlayer = (ListView) findViewById(R.id.lvPlayer);
        ArrayList<String> player = new ArrayList<String>();
        player.add("Player");
        player.add("Some players");
        ArrayAdapter<String> adapterPlayer = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                player
        );

        lvPlayer.setAdapter(adapterPlayer);


        lvPlayer.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item value
                String  itemValue    = (String) lvPlayer.getItemAtPosition(position);

                Intent intent = new Intent(RemovePlayerActivity.this, RemoveDialogActivity.class);
                intent.putExtra("selectedItem", itemValue);
                startActivity(intent);


            }

        });
    }
}
