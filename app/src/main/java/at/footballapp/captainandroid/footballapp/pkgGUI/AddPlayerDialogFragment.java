package at.footballapp.captainandroid.footballapp.pkgGUI;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgData.Database;
import at.footballapp.captainandroid.footballapp.pkgData.Player;
import at.footballapp.captainandroid.footballapp.pkgData.Player_old;


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
                        EditText txtUsername = (EditText) ((Dialog)dialog).findViewById(R.id.username);     // how to get access to the field values
                        EditText txtPasssword = (EditText) ((Dialog)dialog).findViewById(R.id.password);
                        CheckBox isAdmin = (CheckBox)  ((Dialog)dialog).findViewById(R.id.cbIsAdmin);

                        try {
                            Database.newInstance().addPlayer(new Player(txtUsername.getText().toString(),
                                                                        txtPasssword.getText().toString(),
                                                                        isAdmin.isChecked()));
                        } catch (Exception e) {
                            e.printStackTrace();
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
