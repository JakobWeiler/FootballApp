package at.footballapp.captainandroid.footballapp.pkgData;

import at.footballapp.captainandroid.footballapp.pkgMisc.EnumPositions;

/**
 * Auhtor: Pascal
 * Date: 24.03.2017
 */

public class Statistic {
    private int gDefault;
    private int gHead;
    private int gHeadSnow;
    private int gNutmeg;
    private int gOwn;
    private int gPenalty;
    private EnumPositions position = null;

    public int getgDefault() {
        return gDefault;
    }

    public void setgDefault(int gDefault) {
        this.gDefault = gDefault;
    }

    public int getgHead() {
        return gHead;
    }

    public void setgHead(int gHead) {
        this.gHead = gHead;
    }

    public int getgHeadSnow() {
        return gHeadSnow;
    }

    public void setgHeadSnow(int gHeadSnow) {
        this.gHeadSnow = gHeadSnow;
    }

    public int getgNutmeg() {
        return gNutmeg;
    }

    public void setgNutmeg(int gNutmeg) {
        this.gNutmeg = gNutmeg;
    }

    public int getgOwn() {
        return gOwn;
    }

    public void setgOwn(int gOwn) {
        this.gOwn = gOwn;
    }

    public int getgPenalty() {
        return gPenalty;
    }

    public void setgPenalty(int gPenalty) {
        this.gPenalty = gPenalty;
    }

    public EnumPositions getPosition() {
        return position;
    }

    public void setPosition(EnumPositions position) {
        this.position = position;
    }
}
