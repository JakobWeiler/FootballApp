package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import at.footballapp.captainandroid.footballapp.R;

public class MainActivity extends AppCompatActivity {
    private Calendar calendar = null;
    private Date datePicked = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar.getInstance();

        Spinner spPlayer = (Spinner) findViewById(R.id.spPlayer);
        ArrayList<String> player = new ArrayList<String>();
        player.add("Player");
        player.add("Some players");
        ArrayAdapter<String> adapterPlayer = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                player
        );

        spPlayer.setAdapter(adapterPlayer);

        Spinner spMatch = (Spinner) findViewById(R.id.spMatch);
        ArrayList<String> match = new ArrayList<String>();
        match.add("Match");
        match.add("Some matches");
        ArrayAdapter<String> adapterMatch = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                match
        );

        spMatch.setAdapter(adapterMatch);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_profile: startActivity(new Intent(MainActivity.this, ProfileActivity.class)); break;
            case R.id.navigation_match: startActivity(new Intent(MainActivity.this, MatchActivity.class)); break;
            case R.id.navigation_statistic: startActivity(new Intent(MainActivity.this, StatisticActivity.class)); break;
            case R.id.navigation_addMatch: openDatePicker(); break;
        }
        return true;
    }

    private void openDatePicker(){
        //talk about the version problem



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            new DatePickerDialog(MainActivity.this, datePickerListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
       }else{
            dummyMethod("fuck this version");
        }


    }

    DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            datePicked = new Date(year, month, dayOfMonth);
            afterDatePicked();
        }
    };

    private void afterDatePicked(){
        dummyMethod(datePicked.getYear() + ", " + datePicked.getMonth() + ", " + datePicked.getDay());
    }

    private void dummyMethod(String s){
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage(s);
        dlgAlert.setTitle("App Title");
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
}
