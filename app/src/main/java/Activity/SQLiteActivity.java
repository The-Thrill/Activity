package Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;

import DB.MyDBOpenHelper;
import Utils.LogUtils;

public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener {

    //http://www.sqliteexpert.com/v5/SQLiteExpertProSetup64.exe
    //可视化工具 SQLiteExpertProSetup.exe

    private static final String TAG = "SQLiteActivity";
    private Context context;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private Button btn0, btn1, btn2, btn3, btn4;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_sqlite);
        context = SQLiteActivity.this;
        sqLiteOpenHelper = MyDBOpenHelper.getInstance(context);
        bindViews();

    }

    private void bindViews() {
        btn0 = (Button)findViewById(R.id.btn0);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }


    @SuppressLint("Range")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                //getReadDatabase()和getWriteDatabase()才能初始化数据库
                SQLiteDatabase readableDatabase = sqLiteOpenHelper.getReadableDatabase();
                LogUtils.i(TAG, "createDB");
                break;
            case R.id.btn1:
                //查
                SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
                if(db.isOpen()) {   //数据库打开成功
                    //返回游标Cursor
                    Cursor cursor = db.rawQuery("select * from persons", null);
                    //迭代游标
                    while (cursor.moveToNext()){
                        //根据数据类型获取数据 如果为数字则可以用下标来定义(不建议)
//                        int _id = cursor.getInt(0);
//                        int name = cursor.getInt(1);
                        int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                        int name = cursor.getInt(cursor.getColumnIndex("name"));
                        LogUtils.i(TAG, "query---_id:"+_id+" name:"+name);
                    }
                    //关闭游标。或者耗费性能
                    cursor.close();
                    //数据库关闭
                    db.close();
                }
                break;
            case R.id.btn2:
                //增
                SQLiteDatabase writeDB = sqLiteOpenHelper.getWritableDatabase();
                if(writeDB.isOpen()) {   //数据库打开成功
                    writeDB.execSQL("insert into persons(name) values('5555')");
                    LogUtils.i(TAG, "insert---");

                    //数据库关闭
                    writeDB.close();
                }

                break;
            case R.id.btn3:
                //改
                SQLiteDatabase writeDB1 = sqLiteOpenHelper.getWritableDatabase();
                if(writeDB1.isOpen()) {   //数据库打开成功
                    writeDB1.execSQL("update persons set name = ? where _id = ?", new Object[]{"666",2});
                    LogUtils.i(TAG, "update---");

                    //数据库关闭
                    writeDB1.close();
                }
                break;
            case R.id.btn4:
                //删
                SQLiteDatabase writeDB2 = sqLiteOpenHelper.getWritableDatabase();
                if(writeDB2.isOpen()) {     //数据库打开成功
                    writeDB2.execSQL("delete from persons where _id = ?", new Object[]{2});
                    LogUtils.i(TAG, "delete---");

                    //数据库关闭
                    writeDB2.close();
                }

                break;
        }
    }
}
