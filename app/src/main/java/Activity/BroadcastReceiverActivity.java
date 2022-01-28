package Activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;

import BroadcastReceiver.MyBRReceiver;
import Utils.LogUtils;

public class BroadcastReceiverActivity extends AppCompatActivity implements View.OnClickListener {

    //动态广播
    //1、创建一个Receiver继承BroadcastReceiver,并重写onReceive()
    //2、在Activity的OnCreate()中添加广播接收器想要接收的action--->intentFilter.addAction()
    //3、注册广播接收器，调用registerReceiver（Receiver实例，intentFilter）
    //4、新建Intent对象,然后sendBroadcast(intent)
    //5、在OnDestroy()中取消注册,unregisterReceiver（Receiver实例）

    //静态广播
    //1、创建一个Receiver继承BroadcastReceiver，并重写onReceive()
    //2、在AndroidManifest中添加自定义的Receiver，并加上action
    //3、新建Intent对象，并setAction为自定义的action值，然后sendBroadcast(intent)

    //（1）动态注册广播不是常驻型广播，也就是说广播跟随Activity的生命周期。注意在Activity结束前，移除广播接收器。
        //静态注册是常驻型，也就是说当应用程序关闭后，如果有信息广播来，程序也会被系统调用自动运行。
    //（2）当广播为有序广播时：优先级高的先接收（不分静态和动态）。同优先级的广播接收器，动态优先于静态
    //（3）同优先级的同类广播接收器，静态：先扫描的优先于后扫描的，动态：先注册的优先于后注册的。
    //（4）当广播为默认广播时：无视优先级，动态广播接收器优先于静态广播接收器。同优先级的同类广播接收器，静态：先扫描的优先于后扫描的，动态：先注册的优先于后册的。


    private static final String TAG = "BroadcastReceiverActivity";
    private Button btn1, btn2;
    private MyBRReceiver myBRReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        //动态注册
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MY_BROADCAST");
        myBRReceiver = new MyBRReceiver();
        registerReceiver(myBRReceiver,intentFilter);
        LogUtils.i(TAG,"registerReceiver动态广播");
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.btn1:
                    //发送静态广播
                    Intent intent1 = new Intent("com.example.activity.test");
                    intent1.putExtra("msg","sendBroadcast()");
                    sendBroadcast(intent1);
                    break;
                case R.id.btn2:
                    //发送动态广播
                    Intent intent2 = new Intent("android.intent.action.MY_BROADCAST");
                    intent2.putExtra("msg","sendBroadcast()");
                    sendBroadcast(intent2);
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //动态注册需要解除注册
        unregisterReceiver(myBRReceiver);
        LogUtils.i(TAG,"unregisterReceiver动态广播");
    }
}
