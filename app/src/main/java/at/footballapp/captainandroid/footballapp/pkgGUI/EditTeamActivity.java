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
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgData.Database;
import at.footballapp.captainandroid.footballapp.pkgData.Match;
import at.footballapp.captainandroid.footballapp.pkgData.Player;
import at.footballapp.captainandroid.footballapp.pkgHelp.HelpDebug;
import at.footballapp.captainandroid.footballapp.pkgHelp.PlayerRadioButton;
import at.footballapp.captainandroid.footballapp.pkgHelp.RandomMatchEnum;
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
    private Button btnRandomTeams = null;
    private TreeSet<PlayerRadioButton> radioButtonsTA = null;
    private TreeSet<PlayerRadioButton> radioButtonsTB = null;

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
            radioButtonsTA = new TreeSet<PlayerRadioButton>();
            radioButtonsTB = new TreeSet<PlayerRadioButton>();
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
                    if(db.getCurrentMatch().getTeamA().size() > 0 && db.getCurrentMatch().getTeamB().size() > 0){
                        db.addOrUpdateParticipation(db.getCurrentMatch(), firstTime);
                        Intent intent = new Intent(EditTeamActivity.this, UpdateMatchActivity.class);
                        startActivity(intent);
                    }else{
                         Toast.makeText(getApplicationContext() ,String.valueOf("Every team must consist of at least one player") , Toast.LENGTH_SHORT).show();
                     }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        tblLayout = (TableLayout)findViewById(R.id.tablePlayers2);
        tblLayout2 = (TableLayout)findViewById(R.id.tablePlayers);
        tvNameHeader = (TextView)findViewById(R.id.tvNameHeader);
        tvPositionHeader = (TextView)findViewById(R.id.tvPositionHeader);
        tvTeam1 = (TextView)findViewById(R.id.tvTeam1);
        tvTeam2 = (TextView)findViewById(R.id.tvTeam2);
        btnRandomTeams = (Button)findViewById(R.id.btnRandomTeams);

        btnRandomTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickRandomTeams();
            }
        });
    }

    private void pickRandomTeams(){
        ArrayList<Player> goalkeepers = new ArrayList<Player>();
        ArrayList<Player> defenders = new ArrayList<Player>();
        ArrayList<Player> striker = new ArrayList<Player>();
        ArrayList<Player> midfielder = new ArrayList<Player>();
        ArrayList<Player> teamA = new ArrayList<Player>();
        ArrayList<Player> teamB = new ArrayList<Player>();
        ArrayList<RandomMatchEnum> problems = new ArrayList<RandomMatchEnum>();
        Player p2;
        boolean noMorePlayersToPick = false;
        Random randomPick = new Random();


        //preperations
        //pick goalkeeper
        for(Player p : db.getAllPlayers()){
            if(p.getPositions().contains(EnumPositions.Goalkeeper))
                goalkeepers.add(p);
        }



        //pick defenders
        for(Player p : db.getAllPlayers()){
            if(p.getPositions().contains(EnumPositions.Defender))
                defenders.add(p);
        }

        //pick midfielder
        for(Player p : db.getAllPlayers()){
            if(p.getPositions().contains(EnumPositions.Midfielder))
                midfielder.add(p);
        }

        //pick Stricker
        for(Player p : db.getAllPlayers()){
            if(p.getPositions().contains(EnumPositions.Striker))
                striker.add(p);
        }

        //actual algorithm

        //pick goalies
        if(goalkeepers.size() >= 1){
            p2 = goalkeepers.remove(randomPick.nextInt(goalkeepers.size()));
            teamA.add(p2);
            deletePlayerFromOtherArrayLists(p2, goalkeepers, defenders, midfielder, striker);

            if(goalkeepers.size() >= 1){
                p2 = goalkeepers.remove(randomPick.nextInt(goalkeepers.size()));
                teamB.add(p2);
                deletePlayerFromOtherArrayLists(p2, goalkeepers, defenders, midfielder, striker);

            }else{
                problems.add(RandomMatchEnum.GOALIETEAMB);
            }
        }else{
            problems.add(RandomMatchEnum.GOALIETEAMA);
            problems.add(RandomMatchEnum.GOALIETEAMB);
        }

        //pick defenders
        for(int i = 0; i < 4; i++){
            if(defenders.size() >= 1){
                p2 = defenders.remove(randomPick.nextInt(defenders.size()));
                teamA.add(p2);
                deletePlayerFromOtherArrayLists(p2, goalkeepers, defenders, midfielder, striker);
                if(defenders.size() >= 1){
                    p2 = defenders.remove(randomPick.nextInt(defenders.size()));
                    teamB.add(p2);
                    deletePlayerFromOtherArrayLists(p2, goalkeepers, defenders, midfielder, striker);
                }else{
                    problems.add(RandomMatchEnum.DEFENDERTEAMB);
                }
            }else{
                problems.add(RandomMatchEnum.DEFENDERTEAMA);
                problems.add(RandomMatchEnum.DEFENDERTEAMB);
            }
        }

        //pick midfielders
        for(int i = 0; i < 3; i++){
            if(midfielder.size() >= 1){
                p2 = midfielder.remove(randomPick.nextInt(midfielder.size()));
                teamA.add(p2);
                deletePlayerFromOtherArrayLists(p2, goalkeepers, defenders, midfielder, striker);

                if (midfielder.size() >= 1) {
                    p2 = midfielder.remove(randomPick.nextInt(midfielder.size()));
                    teamB.add(p2);
                    deletePlayerFromOtherArrayLists(p2, goalkeepers, defenders, midfielder, striker);

                }else{
                    problems.add(RandomMatchEnum.MIDFIELDERTEAMB);
                }
            }else{
                problems.add(RandomMatchEnum.MIDFIELDERTEAMA);
                problems.add(RandomMatchEnum.MIDFIELDERTEAMB);
            }
        }

        //pick striker
        for(int i = 0; i < 3; i++){
            if(striker.size() >= 1){
                p2 = striker.remove(randomPick.nextInt(striker.size()));
                teamA.add(p2);
                deletePlayerFromOtherArrayLists(p2, goalkeepers, defenders, midfielder, striker);

                if(striker.size() >= 1){
                    p2 = striker.remove(randomPick.nextInt(striker.size()));
                    teamB.add(p2);
                    deletePlayerFromOtherArrayLists(p2, goalkeepers, defenders, midfielder, striker);
                }else{
                    problems.add(RandomMatchEnum.STRIKERTEAMB);
                }
            }else{
                problems.add(RandomMatchEnum.STRIKERTEAMA);
                problems.add(RandomMatchEnum.STRIKERTEAMB);
            }
        }

        //tryToResolveProblem

        for(int i = 0; i < problems.size() && !noMorePlayersToPick; i++){
            switch (problems.get(i)){
                case GOALIETEAMA:
                    teamA.add(pickPlayer(goalkeepers, defenders, midfielder, striker));
                    break;
                case GOALIETEAMB:
                    teamB.add(pickPlayer(goalkeepers, defenders, midfielder, striker));
                    break;
                case DEFENDERTEAMA:
                    teamA.add(pickPlayer(goalkeepers, defenders, midfielder, striker));
                    break;
                case DEFENDERTEAMB:
                    teamB.add(pickPlayer(goalkeepers, defenders, midfielder, striker));
                    break;
                case MIDFIELDERTEAMA:
                    teamA.add(pickPlayer(goalkeepers, defenders, midfielder, striker));
                    break;
                case MIDFIELDERTEAMB:
                    teamB.add(pickPlayer(goalkeepers, defenders, midfielder, striker));
                    break;
                case STRIKERTEAMA:
                    teamA.add(pickPlayer(goalkeepers, defenders, midfielder, striker));
                    break;
                case STRIKERTEAMB:
                    teamB.add(pickPlayer(goalkeepers, defenders, midfielder, striker));
                    break;
            }

            //problems can't be solved when there are no players left to pick.
            if(goalkeepers.size() == 0 && defenders.size() == 0 && midfielder.size() == 0 && striker.size() == 0){
                noMorePlayersToPick = true;
            }
       }

        db.getCurrentMatch().setTeamA(teamA);
        db.getCurrentMatch().setTeamB(teamB);

        tickRadioButtonsAccordingToRandomTeamSelection();
        HelpDebug.debugArrayList(teamA,"DEBUGRANDOM", "TeamA");
        HelpDebug.debugArrayList(teamB,"DEBUGRANDOM", "TeamB");
    }

    private void deletePlayerFromOtherArrayLists(Player p, ArrayList<Player> goalkeepers, ArrayList<Player> defenders, ArrayList<Player> midfielders, ArrayList<Player> strikers){
        if(goalkeepers.contains(p))
            goalkeepers.remove(p);
        if(defenders.contains(p))
            defenders.remove(p);
        if(midfielders.contains(p))
            midfielders.remove(p);
        if(strikers.contains(p))
            strikers.remove(p);
    }

    private void tickRadioButtonsAccordingToRandomTeamSelection(){
        for(Player p : db.getCurrentMatch().getTeamA()){
            PlayerRadioButton b1 = new PlayerRadioButton(this);
            b1.setPlayer(p);

            PlayerRadioButton b2 = radioButtonsTA.floor(b1);
            b2.setChecked(true);
        }

        for (Player p: db.getCurrentMatch().getTeamB()){
            PlayerRadioButton b1 = new PlayerRadioButton(this);
            b1.setPlayer(p);

            PlayerRadioButton b2 = radioButtonsTB.floor(b1);
            b2.setChecked(true);
        }
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

                radioButtonsTA.add(rbT1);

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

                radioButtonsTB.add(rbT2);

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

    private Player pickPlayer(ArrayList<Player> goalkeeper, ArrayList<Player> defenders, ArrayList<Player> midfielder, ArrayList<Player> striker){
        Player picked = null;
        Random r = new Random();

        if(goalkeeper.size() > 0){
            picked = goalkeeper.remove(r.nextInt(goalkeeper.size()));
        }else if(defenders.size() > 0){
            picked = defenders.remove(r.nextInt(goalkeeper.size()));
        }else if(midfielder.size() > 0){
            picked = midfielder.remove(r.nextInt(midfielder.size()));
        }else if(striker.size() > 0){
            picked = striker.remove(r.nextInt(striker.size()));
        }

        return picked;
    }
}
