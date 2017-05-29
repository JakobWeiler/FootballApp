package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgData.Database;
import at.footballapp.captainandroid.footballapp.pkgData.Player;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText txtUser;
    private EditText txtPasswd;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        try {
            Database.newInstance().authUser(new Player(txtUser.getText().toString(), txtPasswd.getText().toString()));
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPasswd = (EditText) findViewById(R.id.txtPassword);
    }

    public void onBtnLogin(View view) {
        Toast t = null;

        if(txtUser.getText().length() != 0 && txtPasswd.getText().length() != 0){
            try {
                if(Database.newInstance().authUser(new Player(txtUser.getText().toString(), txtPasswd.getText().toString()))){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    t = Toast.makeText(this, "Wrong username and/or password !", Toast.LENGTH_LONG);
                }
            } catch (Exception e) {
                t = Toast.makeText(this, "Could not establish DB connection", Toast.LENGTH_LONG);
            }
        } else {
            t = Toast.makeText(this, "Input has to be longer than four characters", Toast.LENGTH_SHORT);
        }

        if(t != null){
            t.setGravity(Gravity.CENTER, 0, 100);
            t.show();
        }
    }
}
