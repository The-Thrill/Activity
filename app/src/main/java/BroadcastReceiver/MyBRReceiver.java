package BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import Utils.LogUtils;

public class MyBRReceiver extends BroadcastReceiver {

    //动态广播
    // 在onReceive()不能执行耗时操作，onReceive()默认是在主线程中，进行耗时会阻塞主线程，
    // 如果非要执行耗时操作最好开启一个服务在服务中进行耗时操作，不建议开启线程来处理耗时操作，
    // 因为BroadCastReceiver的生命周期很短，可能在子线程结束前BroadCastReceiver已经退出，
    // 如果当BroadCastReceiver所在的进程结束，虽然该进程中可能有用户启动的新线程，但是由于该进程内没有活动的组件，
    // 系统会在内存紧张的时候，优先结束掉该进程，这就会导致BroadCastReceiver启动的子线程不能执行完。

    private static final String TAG = "MyBRReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String s = intent.getStringExtra("msg");
        LogUtils.i(TAG,"动态广播----->"+s);
    }
}
