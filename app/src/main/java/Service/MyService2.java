package Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import Utils.LogUtils;


public class MyService2 extends Service {

    //2.bindService绑定方式
    //onCreate() —> onBind() —> onUnbind() –> onDestroy();
    //1）创建服务onCreate()在整个生命周期仅执行一次；
    //2）每次调用服务必须首先bindService/onBind，执行unbindService/onUnbind后不能调用；
    //3）服务的生命周期受限于UI线程。一旦应用（Activity）终止，服务将onDestroy销毁；
    //4）可以在绑定后调用服务里的功能。

    //bindService启动Service
    //①当首次使用bindService绑定一个Service时,系统会实例化一个Service实例,并调用其onCreate()和onBind()方法,
        //然后调用者就可以通过IBinder和Service进行交互了,此后如果再次使用bindService绑定Service,系统不会创建新的Service实例,
        //也不会再调用onBind()方法,只会直接把IBinder对象传递给其他后来增加的客户端!
    //②如果我们解除与服务的绑定,只需调用unbindService(),此时onUnbind和onDestroy方法将会被调用!这是一个客户端的情况,
        //假如是多个客户端绑定同一个Service的话,情况如下 当一个客户完成和service之间的互动后，它调用 unbindService() 方法来解除绑定。
        //当所有的客户端都和service解除绑定后，系统会销毁service。（除非service也被startService()方法开启）
    //③另外,和上面情况不同,bindService模式下的Service是与调用者相互关联的,可以理解为 "一条绳子上的蚂蚱",要死一起死,
        //在bindService后,一旦调用者销毁,那么Service也立即终止!
    //通过BindService调用Service时调用的Context的bindService的解析 bindService(Intent Service,ServiceConnection conn,int flags)
        //service:通过该intent指定要启动的Service
        //conn:ServiceConnection对象,用户监听访问者与Service间的连接情况, 连接成功回调该对象中的onServiceConnected(ComponentName,IBinder)方法;
            //如果Service所在的宿主由于异常终止或者其他原因终止,导致Service与访问者间断开 连接时调用onServiceDisconnected(ComponentName)方法,
            //主动通过unBindService() 方法断开并不会调用上述方法!
        //flags:指定绑定时是否自动创建Service(如果Service还未创建), 参数可以是0(不自动创建),BIND_AUTO_CREATE(自动创建)


    private final String TAG = "MyService2";
    private int count;
    private boolean quit;

    //定义onBinder方法所返回的对象
    private MyBinder binder = new MyBinder();
    public class MyBinder extends Binder
    {
        public int getCount()
        {
            return count;
        }
    }

    //必须实现的方法,绑定改Service时回调该方法
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i(TAG, "bindService()----->onBind被调用!");
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.i(TAG, "bindService()----->onCreate被调用!");
        //创建一个线程动态地修改count的值
        new Thread() {
            public void run() {
                while(!quit) {
                    try {
                        Thread.sleep(1000);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            };
        }.start();
    }

    //Service断开连接时回调
    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.i(TAG, "bindService()----->onUnbind被调用!");
        return true;
    }

    //Service被关闭前回调
    @Override
    public void onDestroy() {
        LogUtils.i(TAG, "bindService()----->onDestroy被调用!");
        this.quit = true;
        super.onDestroy();
    }


    @Override
    public void onRebind(Intent intent) {
        LogUtils.i(TAG, "unbindService()----->onRebind被调用!");
        super.onRebind(intent);
    }
}
