package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.EventListener;

import at.footballapp.captainandroid.footballapp.R;
import at.footballapp.captainandroid.footballapp.pkgData.Database;

/*Weiler*/
public class RemoveDialogActivity extends Activity {
    Database db = null;

    public interface OnPlayerRemovedListener extends EventListener {
        void handlePlayerRemoved();
    }
    private static OnPlayerRemovedListener listener = null;   // MainActivity

    public static void addOnPlayerRemovedListener(OnPlayerRemovedListener onPlayerRemovedListener) {
        listener = onPlayerRemovedListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_dialog);
        db = Database.newInstance();
        TextView view =(TextView) findViewById(R.id.txtRemoveDialog);
        view.setText("Are you sure to remove \"" + this.getIntent().getExtras().getString("name") + ", " + this.getIntent().getExtras().getInt("goalDifference") + "\" ?");
    }

    public void onBtnYes(View view) {
        try {
            db.removePlayer(this.getIntent().getExtras().getInt("id"), this.getIntent().getExtras().getString("name"));
            if (listener != null)
                listener.handlePlayerRemoved();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.finish();
    }

    public void onBtnNo(View view) {
        this.finish();
    }

}
