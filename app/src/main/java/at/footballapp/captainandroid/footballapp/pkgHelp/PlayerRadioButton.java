package at.footballapp.captainandroid.footballapp.pkgHelp;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.RadioButton;

import at.footballapp.captainandroid.footballapp.pkgData.Player;

/**
 * author: Wutti
 */

public class PlayerRadioButton extends android.support.v7.widget.AppCompatRadioButton implements Comparable{
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerRadioButton that = (PlayerRadioButton) o;

        return getPlayer() != null ? getPlayer().equals(that.getPlayer()) : that.getPlayer() == null;

    }

    @Override
    public int hashCode() {
        return getPlayer() != null ? getPlayer().hashCode() : 0;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return player.compareTo(((PlayerRadioButton) o).getPlayer());
    }
}
