package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.content.Intent;
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

    Button btnLogin;
    EditText txtUser;
    EditText txtPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Intent i = new Intent(this, LoginActivity);
        //startActivity(i);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPasswd = (EditText) findViewById(R.id.txtPassword);

        //faster login during development
       // txtUser.setText("-------");
       // txtPasswd.setText("-------");

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
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
