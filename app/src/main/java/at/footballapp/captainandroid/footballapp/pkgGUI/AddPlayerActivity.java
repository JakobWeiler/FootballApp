package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgData.Database;
import at.footballapp.captainandroid.footballapp.pkgData.Player;

public class AddPlayerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
    }

    public void onBtnConfirm(View view) {

        //Database.newInstance().addPlayer(new Player());
        EditText txtUsername = (EditText) (findViewById(R.id.username));
        EditText txtPassword = (EditText) (findViewById(R.id.password));
        EditText txtCPassword = (EditText) (findViewById(R.id.confirmPassword));
        CheckBox isAdmin = (CheckBox) (findViewById(R.id.cbIsAdmin));
        String msg = "ok";

        if (txtPassword.length() >= 5 && txtUsername.length() >= 5) {
            if (txtPassword.getText().equals(txtCPassword.getText())) {
                if (!Pattern.matches("\\w", txtUsername.getText())) {
                    msg = "Username contains invalid characters !";
                }
            } else {
                msg = "Passwords do not accord !";
            }
        } else {
            msg = "Username or password is too short !";
        }

        if (msg.equals("ok")) {
            try {
                Database.newInstance().addPlayer(new Player(txtUsername.getText().toString(),
                        txtPassword.getText().toString(),
                        isAdmin.isChecked()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }


    public void onBtnCancel(View view) {
        this.finish();
    }
}
