package Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;

import Utils.LogUtils;

public class IntentActivity extends AppCompatActivity {

    //Intent的理解
    //1、显式：操作当前应用自己的组件  Intent(Context c, Class c)
    //2、隐式：操作其他应用自己的组件  Intent(String action) 与Activity的<intent-filter>的action匹配
    //1).创建Intent
    //显式： Intent(Context c, Class c)
    //隐式： Intent(String action) 与Activity的<intent-filter>的action匹配
    //2).携带数据
    //额外：putExtraData(String key, Xxx value); 内部用map容器保存
    //有特定前缀：setData(Uri data); tel:123123
    //3).读取数据
    //额外：Xxx getXxxExtra(String key)
    //有特定前缀：Uri getData()

    //1、ComponentName(组件名称)
    //2、Action(动作)
    //3、Category(类别)
    //4、Data(数据)，Type(MIME类型)
    //5、Extras(额外)
    //6、Flags(标记)

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_intent);
        LogUtils.i("TAG","bbbb"+getTaskId());

        //显示Intent
        Button btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、创建Bundle对象
                Bundle bundle = new Bundle();
                //2、向bundle对象添加数据
                bundle.putBoolean("type",true);
                Intent intent = new Intent(IntentActivity.this, IntentActivity2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //隐示Intent
        Button btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、创建Bundle对象
                Bundle bundle = new Bundle();
                //2、向bundle对象添加数据
                bundle.putBoolean("type",false);
                Intent intent = new Intent(IntentActivity.this,IntentActivity2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
