package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgData.Database;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Intent i = new Intent(this, LoginActivity);
        //startActivity(i);
        setContentView(R.layout.activity_login);

    }

    public void onBtnLogin(View view) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}
