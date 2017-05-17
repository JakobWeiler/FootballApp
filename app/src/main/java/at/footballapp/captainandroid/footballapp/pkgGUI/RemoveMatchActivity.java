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

public class RemoveMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_match);

        final ListView lvMatches = (ListView) findViewById(R.id.lvMatches);
        ArrayList<String> matches = new ArrayList<String>();
        matches.add("Match");
        matches.add("Some Matches");
        ArrayAdapter<String> adapterMatches = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                matches
        );

        lvMatches.setAdapter(adapterMatches);


        lvMatches.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item value
                String  itemValue    = (String) lvMatches.getItemAtPosition(position);

                Intent intent = new Intent(RemoveMatchActivity.this, RemoveDialogActivity.class);
                intent.putExtra("selectedItem", itemValue);
                startActivity(intent);

            }

        });
    }
}
