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

    //SQLiteOpenHelper 工具类
    //2、对外提供函数---单例模式
    private static SQLiteOpenHelper mInstance;
    //同步方法模式的线程安全
    public static synchronized SQLiteOpenHelper getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new MyDBOpenHelper(context,"test.db", null,1);
        }
        return mInstance;
    }

    //同步代码块模式的线程安全
    public static  SQLiteOpenHelper getInstance1(Context context) {
        synchronized(MyDBOpenHelper.class) {
            if(mInstance == null) {
                mInstance = new MyDBOpenHelper(context,"test.db", null,1);
            }
            return mInstance;
        }
    }

    //效率更高的懒汉式单例模式的线程安全
    public static  SQLiteOpenHelper getInstance2(Context context) {
        if(mInstance == null) {
            synchronized(MyDBOpenHelper.class) {
                if(mInstance == null) {
                    mInstance = new MyDBOpenHelper(context,"test.db", null,1);
                }
            }
        }
        return mInstance;
    }

    //1、构造函数私有化
    private MyDBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //数据库第一次创建时调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        LogUtils.i(TAG, "MyDBOpenHelper------onCreate()");
        //主键标准为下划线开头,类型为 integer, 其他类型为 text
        db.execSQL("create table persons(_id integer primary key autoincrement, name text)");
    }

    //软件版本号发生改变时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtils.i(TAG, "MyDBOpenHelper------onUpgrade()");
//        db.execSQL("ALTER TABLE person ADD phone VARCHAR(12)");
    }
}
