package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import Utils.LogUtils;

public class MyDBOpenHelper extends SQLiteOpenHelper {

    //Step 1：自定义一个类继承SQLiteOpenHelper类
    //Step 2：在该类的构造方法的super中设置好要创建的数据库名,版本号
    //Step 3：重写onCreate( )方法创建表结构
    //Step 4：重写onUpgrade( )方法定义版本号发生改变后执行的操作

    private static final String TAG = "MyDBOpenHelper";

    public MyDBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //数据库第一次创建时调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        LogUtils.i(TAG, "MyDBOpenHelper------onCreate()");
        db.execSQL("CREATE TABLE person(personid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20))");
    }

    //软件版本号发生改变时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtils.i(TAG, "MyDBOpenHelper------onUpgrade()");
        db.execSQL("ALTER TABLE person ADD phone VARCHAR(12)");
    }
}
