package at.footballapp.captainandroid.footballapp.pkgHelp;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Christopher on 09.05.2017.
 */

public class SqlDateHelper {
    public static Date getDate(String dateString, String format) throws Exception{

        Date date = null;
        java.util.Date utilDate = new SimpleDateFormat(format).parse(dateString) ;
        date = new Date(utilDate.getTime());

        return date;
    }

    public static Date getDate(String date) throws Exception{
        return getDate(date, "dd-mm-yyyy");
    }

    public static String dateToString(Date date, String format) throws Exception{
        DateFormat dateFormater = new SimpleDateFormat(format);
        return dateFormater.format(date);
    }

    public static String dateToString(Date date) throws Exception{
        return dateToString(date, "dd-mm-yyyy");
    }

}
