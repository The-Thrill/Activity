package Utils;

import android.util.Log;

public class LogUtils {

    public static void v(String paramString, Object paramObject)
    {
        Log.v(paramString,""+paramObject);
    }

    public static void i(String paramString, Object paramObject)
    {
        Log.i(paramString,""+paramObject);
    }

    public static void d(String paramString, Object paramObject)
    {
        Log.d(paramString,""+paramObject);
    }

    public static void e(String paramString, Object paramObject)
    {
        Log.e(paramString,""+paramObject);
    }

    public static void w(String paramString, Object paramObject)
    {
        Log.w(paramString,""+paramObject);
    }
}
