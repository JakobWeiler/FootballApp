package at.footballapp.captainandroid.footballapp.pkgGUI;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.regex.Pattern;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgData.Database;
import at.footballapp.captainandroid.footballapp.pkgData.Player;

public class AddPlayerDialogFragment extends DialogFragment {

    public AddPlayerDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_add_player, null))
                // Add action buttons
                .setPositiveButton(R.string.Insert, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText txtUsername = ((EditText) ((Dialog)dialog).findViewById(R.id.username));
                        EditText txtPassword = (EditText) ((Dialog)dialog).findViewById(R.id.password);
                        EditText txtCPassword= (EditText) ((Dialog)dialog).findViewById(R.id.confirmPassword);
                        CheckBox isAdmin = (CheckBox)  ((Dialog)dialog).findViewById(R.id.cbIsAdmin);
                        String msg = "ok";

                        if(txtPassword.length() >= 5 && txtUsername.length() >= 5){
                            if(txtPassword.getText().equals(txtCPassword.getText())){
                                if(!Pattern.matches("\\w", txtUsername.getText())){
                                    msg = "Username contains invalid characters !";
                                }
                            } else {
                                msg = "Passwords do not accord !";
                            }
                        } else {
                            msg = "Username or password is too short !";
                        }

                        if(msg.equals("ok")){
                            try {
                                Database.newInstance().addPlayer(new Player(txtUsername.getText().toString(),
                                        txtPassword.getText().toString(),
                                        isAdmin.isChecked()));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
                        }
                    }
                })

                .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddPlayerDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
