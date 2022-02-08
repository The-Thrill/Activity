package Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//数据库关联之前创建的表
@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDB extends RoomDatabase {

    //用户只需要操作Dao
    public abstract StudentDao getStudentDao();

    //单例模式
    private static StudentDB mInstance;
    public static synchronized StudentDB getInstance(Context context){
        if(mInstance == null) {
            mInstance = Room.databaseBuilder
                    (context.getApplicationContext(),StudentDB.class, "RoomTest.db")
                    //默认为异步线程
                    //慎用：强制主线程,测试环境可以用
//                    .allowMainThreadQueries()
                    .build();
        }
        return mInstance;
    }


}
