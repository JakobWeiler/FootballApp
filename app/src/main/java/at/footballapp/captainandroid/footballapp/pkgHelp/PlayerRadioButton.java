package at.footballapp.captainandroid.footballapp.pkgHelp;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.RadioButton;

import at.footballapp.captainandroid.footballapp.pkgData.Player;

/**
 * author: Wutti
 */

public class PlayerRadioButton extends android.support.v7.widget.AppCompatRadioButton{
    private Player player = null;

    public void setPlayer(Player _player){player  = _player;}
    public Player getPlayer(){return player;}

    public PlayerRadioButton(Context context) {
        super(context);
    }

    public PlayerRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
