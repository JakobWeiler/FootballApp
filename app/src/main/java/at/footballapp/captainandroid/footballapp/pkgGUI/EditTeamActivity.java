package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgData.Database;
import at.footballapp.captainandroid.footballapp.pkgData.Match;
import at.footballapp.captainandroid.footballapp.pkgData.Player;
import at.footballapp.captainandroid.footballapp.pkgHelp.PlayerRadioButton;
import at.footballapp.captainandroid.footballapp.pkgHelp.SqlDateHelper;
import at.footballapp.captainandroid.footballapp.pkgHelp.TeamEnum;
import at.footballapp.captainandroid.footballapp.pkgMisc.EnumPositions;

public class EditTeamActivity extends AppCompatActivity {

    java.sql.Date dateOfCurrentMatch = null;
    Match currentMatch = null;
    Database db = null;

    private Button btnSave = null;
    private TableLayout tblLayout = null;
    private TableLayout tblLayout2 = null;
    private TextView tvNameHeader = null;
    private TextView tvPositionHeader = null;
    private TextView tvTeam1 = null;
    private TextView tvTeam2 = null;
    private boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);
        db = Database.newInstance();
        firstTime = true;


        initViews();
        initOthers();
        doTableStuff();

        try {
            String dateInString = getIntent().getExtras().getString("date");
            dateOfCurrentMatch = SqlDateHelper.getDate(dateInString);
            currentMatch = db.getCurrentMatch();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void initOthers(){
        try {
            db.loadAllPlayers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*Wutti*/
    private void initViews(){
        btnSave = (Button)findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.addOrUpdateParticipation(db.getCurrentMatch(), firstTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(EditTeamActivity.this, UpdateMatchActivity.class);
                startActivity(intent);
            }
        });

        tblLayout = (TableLayout)findViewById(R.id.tablePlayers2);
        tblLayout2 = (TableLayout)findViewById(R.id.tablePlayers);
        tvNameHeader = (TextView)findViewById(R.id.tvNameHeader);
        tvPositionHeader = (TextView)findViewById(R.id.tvPositionHeader);
        tvTeam1 = (TextView)findViewById(R.id.tvTeam1);
        tvTeam2 = (TextView)findViewById(R.id.tvTeam2);
    }

    /*Wutti*/
    private void doTableStuff(){
        ArrayList<EnumPositions> positions = null;

        try{
            Iterator<Player> it = db.getAllPlayers().iterator();

            while(it.hasNext()){
                TableRow row = new TableRow(this);
                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));  //Set layout for the row
                final Player player = it.next();

                positions = player.getPositions();


                TextView tv = new TextView(this);
                tv.setLayoutParams(tvNameHeader.getLayoutParams());
                tv.setText(player.getName());

                Spinner sp = new Spinner(this);
                sp.setLayoutParams(tvPositionHeader.getLayoutParams());

                ArrayAdapter<EnumPositions> spinnerAdapter = new ArrayAdapter<EnumPositions>(this, android.R.layout.simple_spinner_item, positions);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp.setAdapter(spinnerAdapter);

                PlayerRadioButton rbT1 = new PlayerRadioButton(this);
                rbT1.setLayoutParams(tvTeam1.getLayoutParams());
                rbT1.setGravity(Gravity.CENTER);
                rbT1.setPlayer(player);
                rbT1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setTeamsAccordingToUsersChoice(((PlayerRadioButton)findViewById(v.getId())).getPlayer(), TeamEnum.TEAM1);
                    }
                });

                PlayerRadioButton rbT2 = new PlayerRadioButton(this);
                rbT2.setLayoutParams(tvTeam2.getLayoutParams());
                rbT2.setGravity(Gravity.CENTER);
                rbT2.setPlayer(player);
                rbT2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setTeamsAccordingToUsersChoice(((PlayerRadioButton)findViewById(v.getId())).getPlayer(), TeamEnum.TEAM2);
                    }
                });

                RadioGroup rg = new RadioGroup(this);
                rg.setOrientation(RadioGroup.HORIZONTAL);
                rg.addView(rbT1);
                rg.addView(rbT2);

                row.addView(tv);
                row.addView(sp);
                row.addView(rg);
                tblLayout.addView(row);
            }
       }catch(Exception e){
            e.printStackTrace();
        }

    }

    /*Wutti*/
    private void setTeamsAccordingToUsersChoice(Player p, TeamEnum team){
        Match currentMatch = db.getCurrentMatch();

        switch (team){
            case TEAM1:
                currentMatch.addToTeamA(p);
                currentMatch.removeFromTeamB(p);
                break;
            case TEAM2:
                currentMatch.addToTeamB(p);
                currentMatch.removeFromTeamA(p);
                break;
        }
    }
/*
    private void testTeams(){
        Log.d("TESTTEAM", String.valueOf("-------------NEW TEST------------------"));


        Log.d("TESTTEAM", String.valueOf("team 1"));
        Log.d("TESTTEAM", String.valueOf("-------------------------------"));


        for (Player p : db.getCurrentMatch().getTeamA()){
            Log.d("TESTTEAM", p.getName());
        }

        Log.d("TESTTEAM", String.valueOf("team 2"));
        Log.d("TESTTEAM", String.valueOf("-------------------------------"));


        for (Player p : db.getCurrentMatch().getTeamB()){
            Log.d("TESTTEAM", p.getName());
        }
    }
*/
}
