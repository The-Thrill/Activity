package Utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    private static String TAG = "DateUtils";
    public static final String CALENDAR_DATA = "MM/dd";
    public static final String CALENDAR_DATA_TIME = "MM/dd-HH:mm";
    public static final String CALENDAR_MONTH_DATA = "MM-dd HH:mm";
    public static final String CALENDAR_TIME = "HH:mm";
    public static final String CALENDAR_YEAR_DATA = "yyyy/MM/dd";
    public static final String CALENDAR_YEAR_MONTH = "yyyy/MM";
    public static final String DAY = "dd";
    public static final String MONTH_DAY_HOUD_MIN = "MM-dd HH:mm";
    public static final String YEAR = "yyyy";
    public static final String YEAR_MONTH_DAY_HOUD_MIN = "yyyyMMdd HH:mm";
    public static final String YMD_HM = "yyyyMMdd HH:mm";
    public static final String YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMD_HMS_S = "yyyy-MM-dd HH:mm:ss.SSS";

    @SuppressLint({"SimpleDateFormat"})
    private static SimpleDateFormat dateFormat1;

    @SuppressLint({"SimpleDateFormat"})
    private static SimpleDateFormat dateFormat2;

    @SuppressLint({"SimpleDateFormat"})
    private static SimpleDateFormat dateFormat3;

    @SuppressLint({"SimpleDateFormat"})
    private static SimpleDateFormat dateFormat4;

    static
    {
        dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateFormat4 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    public static String getCurrentDate()
    {
        return dateFormat2.format(new Date());
    }

    public static String getCurrentDate1()
    {
        return getCurrentDate1("yyyyMMdd");
    }

    public static String getCurrentDate1(String paramString)
    {
        return new SimpleDateFormat(paramString).format(new Date());
    }

    public static String getCurrentDate2()
    {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static int getCurrentMonthDay()
    {
        return Calendar.getInstance().get(5);
    }

    public static String getCurrentTime()
    {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static String getCurrentTime2()
    {
        return new SimpleDateFormat("HH:mm").format(new Date());
    }

    public static int getDate()
    {
        return Calendar.getInstance().get(5);
    }

    public static Calendar getGMT8Calendar()
    {
        return Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    public static String getHm(String paramString)
    {
        if ((paramString.contains(" ")) && (paramString.contains(":")))
            return paramString.substring(paramString.indexOf(" "), paramString.lastIndexOf(":"));
        return "";
    }

    public static int getHour()
    {
        return Calendar.getInstance().get(11);
    }


}
