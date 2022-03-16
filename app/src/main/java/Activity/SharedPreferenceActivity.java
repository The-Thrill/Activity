package Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;

import Utils.LogUtils;

public class SharedPreferenceActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "SharedPreferenceActivity";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Button btn1, btn2, btn3, btn4;
    private EditText username, password;
    private CheckBox cb1, cb2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        //保存在 /data/data/包名/shared_prefs/Config.xml
        //参数1为保存的文件名称
        //参数2为保存的模式（常规Context.MODE_PRIVATE-每次保存都会更新，追加Context.MODE_APPEND--每次保存都会追加更新到后面）
        sharedPreferences = getSharedPreferences("Config", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        boolean remeberPwd = sharedPreferences.getBoolean("isRemeber", false);
        boolean autoLogin = sharedPreferences.getBoolean("isAuto", false);

        //记住密码
        if(remeberPwd) {
            String name = sharedPreferences.getString("name", "");
            String pwd = sharedPreferences.getString("pwd", "");
            username.setText(name);
            password.setText(pwd);
            cb1.setChecked(true);
        }

        //自动登录
        if(autoLogin) {
            cb2.setChecked(true);
            LogUtils.i(TAG, "自动登录成功++++");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                //保存
                editor.putString("武功", "九阳神功");
                //apply()才会写入
                //apply没有返回值而commit返回boolean表明修改是否提交成功
                editor.apply();
                break;
            case R.id.btn2:
                //读取
                String s = sharedPreferences.getString("武功", "默认值");
                LogUtils.i(TAG,"获取的值为：" + s);
                break;
            case R.id.btn3:
                //注册
                //保存
                editor.putString("name",username.getText().toString().trim());
                editor.putString("pwd", password.getText().toString().trim());
                //apply()才会写入
                editor.apply();
                break;
            case R.id.btn4:
                //登录
                String name = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                    LogUtils.i(TAG, "用户名或密码为空++++");
                }else {
                    //记住密码
                    if(cb1.isChecked()) {
                        editor.putString("name", name);
                        editor.putString("pwd", pwd);
                        editor.putBoolean("isRemeber", true);
                        editor.apply();
                    }
                    //自动登录
                    if(cb2.isChecked()) {
                        editor.putBoolean("isAuto", true);
                        editor.apply();
                    }
                }

                break;
        }
    }
}
