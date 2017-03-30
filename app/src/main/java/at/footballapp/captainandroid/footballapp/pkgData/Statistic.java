package at.footballapp.captainandroid.footballapp.pkgData;

import at.footballapp.captainandroid.footballapp.pkgMisc.EnumPositions;

/**
 * Auhtors: P. L. Lagger, C. P. Wutti
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

    public Statistic(int _gDefault, int _gHead, int _gHeadSnow, int _gNutmeg, int _gOwn, int _gPenalty, EnumPositions _position){
        super();
        setgDefault(_gDefault);
        setgHead(_gHead);
        setgHeadSnow(_gHeadSnow);
        setgNutmeg(_gNutmeg);
        setgOwn(_gOwn);
        setgPenalty(_gPenalty);
        setPosition(_position);
    }

    public Statistic(){
        this(0,0,0,0,0,0,null);
    }

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

    /**
     * Automatically generated equals
     * @param o should be of type Statistic
     * @return whether the two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistic statistic = (Statistic) o;

        if (getgDefault() != statistic.getgDefault()) return false;
        if (getgHead() != statistic.getgHead()) return false;
        if (getgHeadSnow() != statistic.getgHeadSnow()) return false;
        if (getgNutmeg() != statistic.getgNutmeg()) return false;
        if (getgOwn() != statistic.getgOwn()) return false;
        if (getgPenalty() != statistic.getgPenalty()) return false;
        return getPosition() == statistic.getPosition();

    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int result = getgDefault();
        result = 31 * result + getgHead();
        result = 31 * result + getgHeadSnow();
        result = 31 * result + getgNutmeg();
        result = 31 * result + getgOwn();
        result = 31 * result + getgPenalty();
        result = 31 * result + (getPosition() != null ? getPosition().hashCode() : 0);
        return result;
    }


}
