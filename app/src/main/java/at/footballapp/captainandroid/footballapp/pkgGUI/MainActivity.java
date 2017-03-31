package at.footballapp.captainandroid.footballapp.pkgMisc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgGUI.EditTeamActivity;
import at.footballapp.captainandroid.footballapp.pkgGUI.RemoveMatchActivity;
import at.footballapp.captainandroid.footballapp.pkgGUI.RemovePlayerActivity;
import at.footballapp.captainandroid.footballapp.pkgGUI.UpdateMatchActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test comment for first commit
        //what is all the code about ?
        Intent i = new Intent(this, EditTeamActivity.class);
        startActivity(i);
    }
}
