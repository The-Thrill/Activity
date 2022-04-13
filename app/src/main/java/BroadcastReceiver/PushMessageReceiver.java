package BroadcastReceiver;

import android.content.Context;

import App.MyApplication;
import Utils.LogUtils;
import cn.jpush.android.api.CmdMessage;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

public class PushMessageReceiver extends JPushMessageReceiver {
    private static final String TAG = "PushMessageReceiver";


    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        LogUtils.e(TAG, "[onMessage] " + customMessage);
        processCustomMessage(context, customMessage);
    }


    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage message) {
        LogUtils.e(TAG, "[onNotifyMessageArrived] " + message);
    }

    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage message) {
        LogUtils.e(TAG, "[onNotifyMessageDismiss] " + message);
    }

    @Override
    public void onRegister(Context context, String registrationId) {
        LogUtils.e(TAG, "[onRegister] " + registrationId);
    }

    @Override
    public void onConnected(Context context, boolean isConnected) {
        LogUtils.e(TAG, "[onConnected] " + isConnected);
    }

    @Override
    public void onCommandResult(Context context, CmdMessage cmdMessage) {
        LogUtils.e(TAG, "[onCommandResult] " + cmdMessage);
    }

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
//    TagAliasOperatorHelper.getInstance().onTagOperatorResult(context,jPushMessage);
        super.onTagOperatorResult(context, jPushMessage);
        LogUtils.e("xxx", "onTagOperatorResult");
    }


    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onCheckTagOperatorResult(context, jPushMessage);
        LogUtils.e("xxx", "onCheckTagOperatorResult");
    }

    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
        LogUtils.e("xxx", "onAliasOperatorResult+--->"+jPushMessage);
        if (jPushMessage.getErrorCode()==6002){
            MyApplication initApplication = (MyApplication) context.getApplicationContext();
            LogUtils.e("xxx", "errorCode=6002,重连");
//            initApplication.initJgAlias();
        }

    }

    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onMobileNumberOperatorResult(context, jPushMessage);
        LogUtils.e("xxx", "onMobileNumberOperatorResult");
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, CustomMessage customMessage) {
   /* if (MainActivity.isForeground) {
        String message = customMessage.message;
        String extras = customMessage.extra;
        Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
        msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
        if (!ExampleUtil.isEmpty(extras)) {
            try {
                JSONObject extraJson = new JSONObject(extras);
                if (extraJson.length() > 0) {
                    msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
                }
            } catch (JSONException e) {

            }

        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);*/
//    }
        LogUtils.e("xxx", "processCustomMessage");
    }

    @Override
    public void onNotificationSettingsCheck(Context context, boolean isOn, int source) {
        super.onNotificationSettingsCheck(context, isOn, source);
        LogUtils.e(TAG, "[onNotificationSettingsCheck] isOn:" + isOn + ",source:" + source);
    }
}