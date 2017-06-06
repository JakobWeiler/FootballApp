package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgData.Database;
import at.footballapp.captainandroid.footballapp.pkgData.Player;
import at.footballapp.captainandroid.footballapp.pkgMisc.EnumPositions;

/*Weiler*/
public class ProfileActivity extends AppCompatActivity {
    private Database db = null;
    private Player player = null;
    private CheckBox cbGoalkeeper = null;
    private CheckBox cbDefender = null;
    private CheckBox cbMidfielder = null;
    private CheckBox cbStriker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        db = Database.newInstance();

        if (db.getCurrentPlayer().getIsAdmin() == 1) {
            int index = db.getAllPlayers().indexOf(new Player(this.getIntent().getExtras().getString("playerName")));
            player = db.getAllPlayers().get(index);
        }
        else {
            player = db.getCurrentPlayer();
        }

        TextView txtView = (TextView) findViewById(R.id.txtProfileUser);
        txtView.setText(player.getName());

        getViews();

        ArrayList<EnumPositions> positions = player.getPositions();
        if (positions.contains(EnumPositions.valueOf("Goalkeeper")))
            cbGoalkeeper.setChecked(true);
        if (positions.contains(EnumPositions.valueOf("Defender")))
            cbDefender.setChecked(true);
        if (positions.contains(EnumPositions.valueOf("Midfielder")))
            cbMidfielder.setChecked(true);
        if (positions.contains(EnumPositions.valueOf("Striker")))
            cbStriker.setChecked(true);
    }

    private void getViews() {
        this.cbGoalkeeper = (CheckBox) findViewById(R.id.cbGoalkeeper);
        this.cbDefender = (CheckBox) findViewById(R.id.cbDefender);
        this.cbMidfielder = (CheckBox) findViewById(R.id.cbMidfielder);
        this.cbStriker = (CheckBox) findViewById(R.id.cbStriker);
    }

    public void onBtnSave(View view) {

        try {
            if (cbGoalkeeper.isChecked())
                player.addPosition("Goalkeeper", false);
            else
                player.removePosition("Goalkeeper");
            if (cbDefender.isChecked())
                player.addPosition("Defender", false);
            else
                player.removePosition("Defender");
            if (cbMidfielder.isChecked())
                player.addPosition("Midfielder", false);
            else
                player.removePosition("Midfielder");
            if (cbStriker.isChecked())
                player.addPosition("Striker", false);
            else
                player.removePosition("Striker");

            this.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBtnReset(View view) {
        try {
            player.resetPositions();
            cbGoalkeeper.setChecked(false);
            cbDefender.setChecked(false);
            cbMidfielder.setChecked(false);
            cbStriker.setChecked(false);
            this.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
