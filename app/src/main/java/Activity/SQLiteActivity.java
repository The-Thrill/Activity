package Activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
    private MyDBOpenHelper myDBOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button btn1, btn2, btn3, btn4;
    private int i = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_sqlite);
        context = SQLiteActivity.this;
        myDBOpenHelper = new MyDBOpenHelper(context, "my.db", null,1);
        bindViews();

    }

    private void bindViews() {
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                LogUtils.i(TAG, "---insert");
                break;
            case R.id.btn2:
                LogUtils.i(TAG, "---query");
                break;
            case R.id.btn3:
                LogUtils.i(TAG, "---update");
                break;
            case R.id.btn4:
                LogUtils.i(TAG, "---delete");
                break;
        }
    }
}
