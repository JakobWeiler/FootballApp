package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgData.Database;

public class RemoveDialogActivity extends Activity {
    Database db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_dialog);
        db = Database.newInstance();
        TextView view =(TextView) findViewById(R.id.txtRemoveDialog);
        view.setText("Are you sure to remove \"" + this.getIntent().getExtras().getString("selectedItem") + "\" ?");
    }

    public void onBtnYes(View view) {
        try {
            db.removePlayer(this.getIntent().getExtras().getInt("id"), this.getIntent().getExtras().getString("selectedItem"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.finish();
    }

    public void onBtnNo(View view) {
        this.finish();
    }

}
