package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import at.footballapp.captainandroid.footballapp.R;

public class RemoveDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_dialog);
        TextView view =(TextView) findViewById(R.id.txtRemoveDialog);
        view.setText("Are you sure to remove \"" + this.getIntent().getExtras().getString("selectedItem") + "\" ?");
    }

}
