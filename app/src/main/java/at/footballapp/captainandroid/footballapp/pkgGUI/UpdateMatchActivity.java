package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgData.Database;
import at.footballapp.captainandroid.footballapp.pkgData.Match;
import at.footballapp.captainandroid.footballapp.pkgHelp.SqlDateHelper;

public class UpdateMatchActivity extends AppCompatActivity {


    private boolean isAdd = false;
    private DatePicker dpDatePicker = null;
    private Button btnStatistic = null;
    private Button btnEditTeam = null;
    private Button btnSave = null;
    private EditText etScoreTeam1 = null;
    private EditText etScoreTeam2 = null;
    private TextView tvScoreTeam1 = null;
    private TextView tvScoreTeam2 = null;
    private Database db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isAdd = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_match);

    //todo figure out a smart way of doing this
        try{
            if((this.getIntent().getExtras().getString("ADDMATCH")).equals("ADDMATCH"));
            isAdd = true;
        }catch (Exception e){
            //noException
        }
        getViews();
        db = Database.newInstance();
        prepare();
    }

    private void getViews(){
        this.btnEditTeam = (Button)findViewById(R.id.btnEditTeam);
        this.btnSave = (Button)findViewById(R.id.btnSave);
        this.btnStatistic = (Button) findViewById(R.id.btnStatistic);
        this.dpDatePicker = (DatePicker) findViewById(R.id.datePicker);
        this.etScoreTeam1 = (EditText) findViewById(R.id.editText3);
        this.etScoreTeam2 = (EditText) findViewById(R.id.editText4);
        this.tvScoreTeam1 = (TextView) findViewById(R.id.textView);
        this.tvScoreTeam2 = (TextView) findViewById(R.id.textView2);
    }

    private void prepare(){

        if(isAdd){
            btnEditTeam.setVisibility(View.GONE);
            btnStatistic.setVisibility(View.GONE);
            etScoreTeam1.setVisibility(View.GONE);
            etScoreTeam2.setVisibility(View.GONE);
            tvScoreTeam1.setVisibility(View.GONE);
            tvScoreTeam2.setVisibility(View.GONE);
            btnSave.setText("add");
        }
    }

    public void onBtnEditTeam(View view) {
        startActivity(new Intent(UpdateMatchActivity.this, EditTeamActivity.class));
    }

    public void onBtnStatistic(View view) {
        startActivity(new Intent(UpdateMatchActivity.this, StatisticActivity.class));
    }

    public void onBtnSave(View view){
        if(isAdd){

            java.sql.Date dateOfMatch = null;
            try{dateOfMatch = SqlDateHelper.getDate((dpDatePicker.getDayOfMonth()+"-"+dpDatePicker.getMonth()+"-"+dpDatePicker.getYear()));}catch(Exception e){/*there is literally no fucking way we get an exception here*/}


            //Calendar Stuff

            Calendar calendar = Calendar.getInstance();

            Date today = calendar.getTime();

            calendar.set(Calendar.DAY_OF_MONTH, dpDatePicker.getDayOfMonth());
            calendar.set(Calendar.MONTH, dpDatePicker.getMonth());
            calendar.set(Calendar.YEAR, dpDatePicker.getYear());

            //

            if(!calendar.getTime().before(today)){
                try{displayDateIsInTheFutureError(dateOfMatch);}catch(Exception e){/*NO FUCKING WAY I AM TELLING YOU*/}
            }else{
                db.addMatch(new Match(-1,dateOfMatch, 0,0,null,null));



                Intent intent = new Intent(UpdateMatchActivity.this, EditTeamActivity.class);
                try{intent.putExtra("date", SqlDateHelper.dateToString(dateOfMatch));}catch(Exception e){/*no fucking way again*/}
                startActivity(intent);
            }

        }else{
            //todo
        }
    }

    private void displayDateIsInTheFutureError(java.sql.Date dateWhichIsInTheFuture) throws Exception{


        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogButtonTheme);

        builder.setMessage(String.valueOf("The date: " + SqlDateHelper.dateToString(dateWhichIsInTheFuture) + " is in the future. Hence, you have to choose a different one."));

        builder.setPositiveButton(String.valueOf("Ok"), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });



        AlertDialog dialog  = builder.create();
        dialog.show();

    }
}
