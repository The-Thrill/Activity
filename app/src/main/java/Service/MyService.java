package Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import Utils.LogUtils;

public class MyService extends Service {

    //1.startService启动方式：
    //onCreate()–> onStartCommand()/onStart() —> onDestroy();
    //1）创建服务onCreate()在整个生命周期仅执行一次，每次调用服务都会执行onStart()或onStartCommand();
    //2）停止服务onDestroy在整个生命周期仅执行一次；
    //3）服务一旦启动，生命周期将不受限于UI线程。应用（Activity）终止，服务仍然在后台运行；
    //4）直接启动的服务，其它应用不能调用其中的功能。

    //startService启动Service
    //①首次启动会创建一个Service实例,依次调用onCreate()和onStartCommand()方法,此时Service 进入运行状态,
        //如果再次调用StartService启动Service,将不会再创建新的Service对象,系统会直接复用前面创建的Service对象,调用它的onStartCommand()方法！
    //②但这样的Service与它的调用者无必然的联系,就是说当调用者结束了自己的生命周期,但是只要不调用stopService,那么Service还是会继续运行的!
    //③无论启动了多少次Service,只需调用一次StopService即可停掉Service


    private static final String TAG = "MyService";

    //必须要实现的方法
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i(TAG, "startService()----->onBind()被调用!");
        return null;
    }

    //Service被创建时调用
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.i(TAG, "startService()----->onCreate()被调用!");
    }

    //Service被启动时调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.i(TAG, "startService()----->onStartCommand()被调用!");
        return super.onStartCommand(intent, flags, startId);
    }

    //Service被关闭之前回调
    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "stopService()----->onDestroy()被调用!");
    }

}
