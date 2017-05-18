package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgData.Database;
import at.footballapp.captainandroid.footballapp.pkgData.Match;
import at.footballapp.captainandroid.footballapp.pkgHelp.SqlDateHelper;

public class EditTeamActivity extends AppCompatActivity {

    java.sql.Date dateOfCurrentMatch = null;
    Match currentMatch = null;
    Database db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);
        db = Database.newInstance();

        try {
            String dateInString = getIntent().getExtras().getString("date");
            dateOfCurrentMatch = SqlDateHelper.getDate(dateInString);
            currentMatch = db.getMatch(dateOfCurrentMatch);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
