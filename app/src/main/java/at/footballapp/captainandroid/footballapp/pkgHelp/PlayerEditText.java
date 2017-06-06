package at.footballapp.captainandroid.footballapp.pkgHelp;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;

import at.footballapp.captainandroid.footballapp.pkgData.Player;

/*Weiler*/
public class PlayerEditText extends AppCompatEditText {
    private Player player = null;

    public PlayerEditText(Context context) {
        super(context);
    }

    public void setPlayer(Player _player){player  = _player;}
    public Player getPlayer(){return player;}


}
