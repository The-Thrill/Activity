package Activity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;

import Service.MyService;
import Service.MyService2;
import Service.MyService3;
import Utils.LogUtils;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    //创建线程的三种方式
    //1、继承Thread类
    //2、实现Runnable接口
    //3、实现Callable接口

    //生命周期
    //1）非绑定Service的生命周期
    //调用startService()->onCreate()->onStartCommand()->Service运行->Service被自己或调用者停止->onDestroy()->Service被关
    //2）绑定Service的生命周期
    //调用bindService()->onCreate()->onBind()->客户端绑定到Service->调用onUnbindService取消绑定->onUnbind()->onDestroy()->Service被关
    //1）IntentService的生命周期
    //调用startService()->onCreate()->onStartCommand()->Service运行->Service被自己或调用者停止->onDestroy()->Service被关

    //onCreate()：当Service第一次被创建后立即回调该方法，该方法在整个生命周期 中只会调用一次！
    //onDestroy()：当Service被关闭时会回调该方法，该方法只会回调一次！
    //onStartCommand(intent,flag,startId)：早期版本是onStart(intent,startId), 当客户端调用startService(Intent)方法时会回调，
        //可多次调用StartService方法， 但不会再创建新的Service对象，而是继续复用前面产生的Service对象，但会继续回调 onStartCommand()方法！
    //IBinder onBind(intent)：该方法是Service都必须实现的方法，该方法会返回一个 IBinder对象，app通过该对象与Service组件进行通信！
    //onUnbind(intent)：当该Service上绑定的所有客户端都断开时会回调该方法！

    //如果想要启动一个后台服务长期进行某项任务，那么使用startService
    //如果只是短暂的使用，那么使用bindService。
    //如果想启动一个后台服务长期进行任务，且这个过程中需要与调用者进行交互，那么可以两者同时使用，或者使用startService + BoardCast/ EventBus 等方法
    //Service的终止，需要unbindService和stopService都调用才行

    private static final String TAG = "ServiceActivity";
    private Button btn1, btn2, btn3, btn4, btn5, btn6;
    private Intent it1, it2, it3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);

        it1 = new Intent(this, MyService3.class);
        Bundle b1 = new Bundle();
        b1.putString("param", "s1");
        it1.putExtras(b1);

        it2 = new Intent(this, MyService3.class);
        Bundle b2 = new Bundle();
        b2.putString("param", "s2");
        it2.putExtras(b2);

        it3 = new Intent(this, MyService3.class);
        Bundle b3 = new Bundle();
        b3.putString("param", "s3");
        it3.putExtras(b3);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.btn1:
                    Intent startIntent = new Intent(this, MyService.class);
                    startService(startIntent);
                    break;
                case R.id.btn2:
                    Intent stopIntent = new Intent(this, MyService.class);
                    stopService(stopIntent);
                    break;
                case R.id.btn3:
                    Intent bindIntent = new Intent(this, MyService2.class);
                    bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
                    break;
                case R.id.btn4:
                    unbindService(serviceConnection);
                    break;
                case R.id.btn5:
                    LogUtils.i(TAG, "获取的值为"+binder.getCount());
                    break;
                case R.id.btn6:
                    //接着启动多次IntentService,每次启动,都会新建一个工作线程
                    //但始终只有一个IntentService实例
                    startService(it1);
                    startService(it2);
                    startService(it3);
                    break;
            }
        }
    }

    //保持所启动的Service的IBinder对象,同时定义一个ServiceConnection对象
    MyService2.MyBinder binder;
    private final ServiceConnection serviceConnection = new ServiceConnection() {

        //Activity与Service连接成功时回调该方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.i(TAG, "bindService()------Service Connected-------");
            binder = (MyService2.MyBinder) service;
        }

        //Activity与Service断开连接时回调该方法
        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.i(TAG, "bindService()------Service DisConnected-------");
        }

    };

    //一般写法，当前Activity销毁时，自动解绑服务
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
