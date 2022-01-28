package Activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;

import Utils.LogUtils;

public class ConfigurationActivity extends AppCompatActivity {

    private static final String TAG = "ConfigurationActivity";

    //1.启动Activity时的生命周期
    //onCreate—>onStart—>onResume
    //2.将手机屏幕切换成横屏执行的生命周期
    //onPause—>onSaveInstanceState—>onStop—>onDestroy—>onCreate—>
    //onStart—>onRestoreInstanceState—>onResume
    //3.将手机屏幕再次切换成竖屏
    //onPause—>onSaveInstanceState—>onStop—>onDestroy—>onCreate—>
    //onStart—>onRestoreInstanceState—>onResume
    //4.在Activity清单中添加属性android:configChanges=”orientation”,再次执行步骤2,步骤3
    //步骤2:
    //onConfigurationChanged—>onPause—>onSaveInstanceState—>onStop
    //—>onDestroy—>onCreate—>onStart—>onRestoreInstanceState
    //—>onResume
    //步骤3:
    //onConfigurationChanged

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        LogUtils.i(TAG, "onCreate()");

        Button bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuration config = getResources().getConfiguration();
                //如果是横屏的话切换成竖屏
                if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    ConfigurationActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                //如果竖屏的话切换成横屏
                if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    ConfigurationActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i(TAG, "onStart()");
    }


    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i(TAG, "onResume()");
    }


    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.i(TAG, "onPause()");
    }


    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i(TAG, "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i(TAG, "onRestart()");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "onDestroy()");
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        String screen = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横屏" : "竖屏";
        LogUtils.i(TAG, "屏幕方向" + screen);
    }
}
