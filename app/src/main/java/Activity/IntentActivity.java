package Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.activity.R;

public class IntentActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_intent);


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
