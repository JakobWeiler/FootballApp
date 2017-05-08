package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import at.footballapp.captainandroid.footballapp.R;

public class UpdateMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_match);
    }

    public void onBtnEditTeam(View view) {
        startActivity(new Intent(UpdateMatchActivity.this, EditTeamActivity.class));
    }

    public void onBtnStatistic(View view) {
        startActivity(new Intent(UpdateMatchActivity.this, StatisticActivity.class));
    }
}
