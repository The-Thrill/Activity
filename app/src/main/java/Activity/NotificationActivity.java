package Activity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;


public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    //Notification：通知信息类，它里面对应了通知栏的各个属性
    //NotificationManager：是状态栏通知的管理类，负责发通知、清除通知等操作。
    //Step 1. 获得NotificationManager对象： NotificationManager mNManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    //Step 2. 创建一个通知栏的Builder构造类： Notification.Builder mBuilder = new Notification.Builder(this);
    //Step 3. 对Builder进行相关的设置，比如标题，内容，图标，动作等！
    //Step 4.调用Builder的build()方法为notification赋值
    //Step 5.调用NotificationManager的notify()方法发送通知！
    //PS:另外我们还可以调用NotificationManager的cancel()方法取消通知

    private static final String TAG = "NotificationActivity";
    private static final int NOTIFICYID = 1;
    private Button btn1,btn2;

    private Context mContext;
    private NotificationManager manager;
    private Notification notification;
    private Bitmap bitmap = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mContext = NotificationActivity.this;
        //创建大图标
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        //NotificationManager 是一个系统Service
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        bindView();
    }

    private void bindView() {
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                //定义一个PendingIntent点击Notification后启动一个Activity
                Intent intent = new Intent(mContext,NotificationDetailActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(mContext,0,intent,0);
                //创建一个通知栏的Builder构造类
                Notification.Builder builder = new Notification.Builder(mContext);
                ////设置图片，通知标题，发送时间，提示方式等属性
                builder.setContentTitle("标题")                          //标题
                        .setContentText("内容")                          //内容
                        .setSubText("内容下面的一小段文字")                 //内容下面的一小段文字
                        .setTicker("收到信息后状态栏显示的文字信息")          //收到信息后状态栏显示的文字信息
                        .setWhen(System.currentTimeMillis())            //设置通知时间
                        .setSmallIcon(R.mipmap.ic_launcher)             //设置小图标
                        .setLargeIcon(bitmap)                           //设置大图标
                        .setPriority(Notification.PRIORITY_DEFAULT)     //设置优先级
                        .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)    //设置默认的三色灯与振动器
//                        .setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.biaobiao))  //设置自定义的提示音
                        .setAutoCancel(true)                           //设置点击后取消Notification
                        .setContentIntent(pendingIntent);              //设置PendingIntent
                notification = builder.build();
                manager.notify(NOTIFICYID,notification);
                break;
            case R.id.btn2:
                //除了可以根据ID来取消Notification外,还可以调用cancelAll();关闭该应用产生的所有通知
                manager.cancel(NOTIFICYID);                             //取消Notification
                break;
        }
    }
}
