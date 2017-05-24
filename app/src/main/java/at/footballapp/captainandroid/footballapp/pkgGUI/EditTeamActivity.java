package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgData.Database;
import at.footballapp.captainandroid.footballapp.pkgData.Match;
import at.footballapp.captainandroid.footballapp.pkgHelp.SqlDateHelper;

public class EditTeamActivity extends AppCompatActivity {

    java.sql.Date dateOfCurrentMatch = null;
    Match currentMatch = null;
    Database db = null;

    private Button btnSave = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);
        db = Database.newInstance();

        initViews();

        try {
            String dateInString = getIntent().getExtras().getString("date");
            dateOfCurrentMatch = SqlDateHelper.getDate(dateInString);
            currentMatch = db.getMatch(dateOfCurrentMatch);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void initViews(){
        btnSave = (Button)findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditTeamActivity.this, UpdateMatchActivity.class);
                startActivity(intent);
            }
        });

    }


}
