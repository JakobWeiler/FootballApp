package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.app.Activity;
import android.app.Dialog;
import android.os.PatternMatcher;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
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
        Pattern p = Pattern.compile("[a-zA-Z_0-9]+");
        Matcher m = p.matcher(txtUsername.getText().toString());
        String msg = "ok";

        if (txtPassword.length() >= 5 && txtUsername.length() >= 5) {
            if(!Database.newInstance().nameUsed(txtUsername.getText().toString())){
                if ((txtPassword.getText().toString()).compareTo(txtCPassword.getText().toString()) == 0) {
                    if (!m.matches()) {
                        msg = "Username contains invalid characters !";
                    }
                } else {
                    msg = "Passwords do not accord !";
                }
            } else {
                msg = "Username is already used !";
            }
        } else {
            msg = "Username or password is too short ! (min. 5 chars)";
        }

        if (msg.compareTo("ok") == 0) {
            try {
                Database.newInstance().addPlayer(new Player(txtUsername.getText().toString(),
                        txtPassword.getText().toString(),
                        isAdmin.isChecked()));
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                this.finish();
            }
        } else {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }

    public void onBtnCancel(View view) {
        this.finish();
    }
}

