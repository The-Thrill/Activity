package App;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tencent.mmkv.MMKV;

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    private static MyApplication myApplication;
    private Context context;
    private static MMKV mmkv;

    public static MyApplication getInstance() {
        return myApplication;
    }

    //(1).getContext():获取到当前对象的上下文。
    //(2).getApplication():获得Application的对象
    //(3).getApplicationContext():获得应用程序的上下文。有且仅有一个相同的对象。生命周期随着应用程序的摧毁而销毁。
    //(4)getActivity():获得Fragment依附的Activity对象。
        // Fragment里边的getActivity()不推荐使用原因如下：这个方法会返回当前Fragment所附加的Activity，
        // 当Fragment生命周期结束并销毁时，getActivity()返回的是null，所以在使用时要注意判断null或者捕获空指针异常。
        // 所以只要判断getActivity()为空，就可以不再执行下面的代码，这完全不影响业务的使用。

    //避免Context泄漏应该注意的问题：
        //1.使用Application这种Context类型
        //2.注意对Context的引用不要超过它本身的生命周期
        //3.谨慎使用static关键字
        //4.Context里如果有线程，一定要在onDestory()里及时停掉。


    //在Application创建的时候调用，一般用于初始化一些东西，如全局的对象，环境的配置等。
    @Override
    public void onCreate() {
        super.onCreate();

        //MMKV初始化
        //MMKV 的默认根目录（files/mmkv/）/data/user/0/项目包名/files/mmkv
        String rootDir = MMKV.initialize(this);
        //修改目录
//        String dir = getFilesDir().getAbsolutePath() + "/mmkv_1";
//        String rootDir = MMKV.initialize(dir);
        //使用默认的实例
//        mmkv = MMKV.defaultMMKV();
        //创建自己的实例  参数1：库的key， 参数2：库的模式（多进程或单进程）
//        MMKV mmkv2 = MMKV.mmkvWithID("user", MMKV.MULTI_PROCESS_MODE);

    }


    //这两个方法用于注册或者注销对APP内所有Activity的生命周期监听，当APP内Activity的生命周期发生变化的时候就会调用ActivityLifecycleCallbacks里面的方法
    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                Log.e(TAG,"onActivityCreated: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.e(TAG,"onActivityStarted: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.e(TAG,"onActivityResumed: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.e(TAG,"onActivityPaused: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.e(TAG, "onActivityStopped: " + activity.getLocalClassName());
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.e(TAG,"onActivityDestroyed: " + activity.getLocalClassName());
            }
        });

    }

    @Override
    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.unregisterActivityLifecycleCallbacks(callback);
    }


    //重写此方法可以监听APP一些配置信息的改变事件（如屏幕旋转等），当配置信息改变的时候会调用这个方法
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
