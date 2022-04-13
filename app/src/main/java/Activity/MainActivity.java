package Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.activity.R;

import Utils.LogUtils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10,
            btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20,
            btn21, btn22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();

        //Activity的使用
        //1、定义
        //1).定义一个类 extends Activity，并重写生命周期方法
        //2).在功能清淡文件中使用<activity>注册
        //2、启动
        //1).一般：startActivity(Intent intent)
        //2).带回调启动：startActivityForResult(Intent intent, int requestCode)
        //重写：onActivityResult(int requestCode, int requestCode, Intent data)
        //3、结束
        //1).一般：finish()
        //2).带结果：setResult(int resultCode, Intent data)


        //Activity的生命周期
        //1、Activity的状态
        //运行状态：可见可操作
        //暂停状态：可见不可操作
        //停止状态：不可见，单对象存在
        //死亡状态：对象不存在
        //2、Activity的生命周期
        //onCreate()    当Activity第一次被创建时调用，加载布局和初始化的工作
        //onStart()     当用户可以看到这个Activity时调用
        //onResume()    当获得用户的焦点时，就是用户点击了屏幕，只有经历此方法，才能进入运行状态
        //onPause()     当系统准备启动或者恢复另一个活动时调用
        //onStop()      当活动完全不可见时调用，若新启动的活动是对话框形式，则该方法不会被调用
        //onRestart()   当活动有停止状态变为运行状态时调用
        //onDestroy()   活动被销毁时调用，在对象死亡前，进行一些清理工作


        //TaskStack和LaunchMode
        //1、TaskStack 后进先出，一个应用启动会创建一个对应的TaskStack来存储并管理对应的Activity对象，只有在最上面才能显示
        //2、LaunchMode
        //1).standard       标准模式，每次调用startActivity()方法都会产生一个新的实例
        //2).singleTop      如果已经有一个实例位于栈顶，就不产生新的实例，否则创建一个新的实例
        //3).singleTask      只有一个实例，默认在当前Task中
        //4).singleInstance 只有一个实例，创建时会创建新的栈，且此栈中不能有其他对象

    }

    private void bindViews() {
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btn10 = (Button)findViewById(R.id.btn10);
        btn11 = (Button)findViewById(R.id.btn11);
        btn12 = (Button)findViewById(R.id.btn12);
        btn13 = (Button)findViewById(R.id.btn13);
        btn14 = (Button)findViewById(R.id.btn14);
        btn15 = (Button)findViewById(R.id.btn15);
        btn16 = (Button)findViewById(R.id.btn16);
        btn17 = (Button)findViewById(R.id.btn17);
        btn18 = (Button)findViewById(R.id.btn18);
        btn19 = (Button)findViewById(R.id.btn19);
        btn20 = (Button)findViewById(R.id.btn20);
        btn21 = (Button)findViewById(R.id.btn21);
        btn22 = (Button)findViewById(R.id.btn22);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn13.setOnClickListener(this);
        btn14.setOnClickListener(this);
        btn15.setOnClickListener(this);
        btn16.setOnClickListener(this);
        btn17.setOnClickListener(this);
        btn18.setOnClickListener(this);
        btn19.setOnClickListener(this);
        btn20.setOnClickListener(this);
        btn21.setOnClickListener(this);
        btn22.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                ////跳转PopupWindow、DialogAlert的使用界面
                startActivity(new Intent(MainActivity.this, DialogActivity.class));
                break;
            case R.id.btn2:
                //Intent的使用
                LogUtils.i("TAG","ccccc"+getTaskId());
                Intent intent = new Intent(getApplicationContext(), IntentActivity.class);
                //若想用getApplicationContext启动，则需要指定Flags类型，新开启的Activity仍在同一个栈内
                //若要开启新栈，则需要在AndroidManiFest中设置开启的Activity的 taskAffinity
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.btn3:
                //ListView的使用
                startActivity(new Intent(MainActivity.this, ListviewActivity.class));
                break;
            case R.id.btn4:
                //RecyclerView的使用
                startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                break;
            case R.id.btn5:
                //Notification的使用
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                break;
            case R.id.btn6:
                //handler的使用
                startActivity(new Intent(MainActivity.this, HandlerActivity.class));
                break;
            case R.id.btn7:
                //OnTouchEvent的使用
                startActivity(new Intent(MainActivity.this, TouchEventActivity.class));
                break;
            case R.id.btn8:
                //Configuration的使用
                startActivity(new Intent(MainActivity.this, ConfigurationActivity.class));
                break;
            case R.id.btn9:
                //AsyncTask的使用
                startActivity(new Intent(MainActivity.this, AsyncTaskActivity.class));
                break;
            case R.id.btn10:
                //Service的使用
                startActivity(new Intent(MainActivity.this, ServiceActivity.class));
                break;
            case R.id.btn11:
                //BroadcastReceiver的使用
                startActivity(new Intent(MainActivity.this, BroadcastReceiverActivity.class));
                break;
            case R.id.btn12:
                //ContentProvider的使用
                startActivity(new Intent(MainActivity.this, ContentProviderActivity.class));
                break;
            case R.id.btn13:
                //Fragment的使用
                startActivity(new Intent(MainActivity.this, FragmentActivity.class));
                break;
            case R.id.btn14:
                //SQLite的使用
                startActivity(new Intent(MainActivity.this, SQLiteActivity.class));
                break;
            case R.id.btn15:
                //HTTP的使用
                startActivity(new Intent(MainActivity.this, HttpActivity.class));
                break;
            case R.id.btn16:
                //Retrofit的使用
                startActivity(new Intent(MainActivity.this, RetrofitActivity.class));
                break;
            case R.id.btn17:
                //Rx的使用
                startActivity(new Intent(MainActivity.this, RxActivity.class));
                break;
            case R.id.btn18:
                //SharedPreference使用
                startActivity(new Intent(MainActivity.this, SharedPreferenceActivity.class));
                break;
            case R.id.btn19:
                //Room使用
                startActivity(new Intent(MainActivity.this, RoomActivity.class));
                break;
            case R.id.btn20:
                //Glide使用
                startActivity(new Intent(MainActivity.this, GlideActivity.class));
                break;
            case R.id.btn21:
                //Animation使用
                startActivity(new Intent(MainActivity.this, AnimationActivity.class));
                break;
            case R.id.btn22:
                //自定义View使用
                startActivity(new Intent(MainActivity.this, MyViewActivity.class));
                break;
        }
    }
}