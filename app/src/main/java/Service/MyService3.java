package Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import Utils.LogUtils;

public class MyService3 extends IntentService {

    //Service 是长期运行在后台的应用程序组件。
    //Service 不是一个单独的进程，它和应用程序在同一个进程中，Service 也不是一个线程,它和线程没有任何关系，所以它不能直接处理耗时操作。
    //如果直接把耗时操作放在 Service 的 onStartCommand() 中，很容易引起 ANR .如果有耗时操作就必须开启一个单独的线程来处理

    //IntentService 是继承于 Service 并处理异步请求的一个类，在 IntentService 内有一个工作线程来处理耗时操作，
    // 启动 IntentService 的方式和启动传统 Service 一样，同时，当任务执行完后，IntentService 会自动停止，而不需要我们去手动控制。
    // 另外，可以启动 IntentService 多次，而每一个耗时操作会以工作队列的方式在IntentService 的 onHandleIntent 回调方法中执行，
    // 并且，每次只会执行一个工作线程，执行完第一个再执行第二个，以此类推。
    //而且，所有请求都在一个单线程中，不会阻塞应用程序的主线程（UI Thread），同一时间只处理一个请求。
    // 那么，用 IntentService 有什么好处呢？
    // 首先，我们省去了在 Service 中手动开线程的麻烦，第二，当操作完成时，我们不用手动停止 Service


    private final String TAG = "MyService3";

    //必须实现父类的构造方法
    public MyService3() {
        super("MyService3");
    }

    //必须重写的核心方法
    @Override
    protected void onHandleIntent(Intent intent) {
        //Intent是从Activity发过来的，携带识别参数，根据参数不同执行不同的任务
        String action = intent.getExtras().getString("param");
        if (action.equals("s1"))
            LogUtils.i(TAG, "IntentService()----->启动service1");
        else if (action.equals("s2"))
            LogUtils.i(TAG, "IntentService()----->启动service2");
        else if (action.equals("s3"))
            LogUtils.i(TAG, "IntentService()----->启动service3");

        //让服务休眠2秒
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i(TAG, "IntentService()----->onBind()被调用!");
        return null;
    }

    //开启IntentService只执行一次
    @Override
    public void onCreate() {
        LogUtils.i(TAG, "IntentService()----->onCreate()被调用");
        super.onCreate();
    }

    //启动几个IntentService就执行几次
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.i(TAG, "IntentService()----->onStartCommand()被调用");
        return super.onStartCommand(intent, flags, startId);
    }

    //启动几个IntentService就执行几次
    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        LogUtils.i(TAG, "IntentService()----->onStart()被调用");
        super.onStart(intent, startId);
    }


    //待研究
    @Override
    public void setIntentRedelivery(boolean enabled) {
        super.setIntentRedelivery(enabled);
        LogUtils.i(TAG, "IntentService()----->setIntentRedelivery()被调用");
    }

    //关闭IntentService只停止一次
    @Override
    public void onDestroy() {
        LogUtils.i(TAG, "IntentService()----->onDestroy()被调用");
        super.onDestroy();
    }

}
