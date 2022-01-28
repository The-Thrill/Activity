package BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import Utils.LogUtils;

public class MyBRReceiver2 extends BroadcastReceiver {

    //静态广播

    private static final String TAG = "MyBRReceiver2";

    @Override
    public void onReceive(Context context, Intent intent) {
        String s = intent.getStringExtra("msg");
        LogUtils.i(TAG,"静态广播----->"+s);
    }
}