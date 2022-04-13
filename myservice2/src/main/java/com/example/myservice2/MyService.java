package com.example.myservice2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


public class MyService extends Service {
   private final String TAG = "MyService2";
   private int count;
   private boolean quit;


   //必须实现的方法,绑定改Service时回调该方法
   @Override
   public IBinder onBind(Intent intent) {
      Log.i(TAG, "AIDL远程----->onBind被调用!");
      return new IMyAidlInterface.Stub(){
         @Override
         public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            //AIDL自定义文件内可以删除，不删除可以不用理会此方法
         }

         @Override
         public void getProgress() throws RemoteException {
            Log.i(TAG, "AIDL远程getProgress()进度值----->" + count);
         }
      };
   }

   @Override
   public void onCreate() {
      super.onCreate();
      Log.i(TAG, "AIDL远程----->onCreate被调用!");
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

   @Override
   public int onStartCommand(Intent intent, int flags, int startId) {
      Log.i(TAG, "AIDL远程---->onStartCommand被调用!");
      return super.onStartCommand(intent, flags, startId);

   }


   //Service断开连接时回调
   @Override
   public boolean onUnbind(Intent intent) {
      Log.i(TAG, "AIDL远程----->onUnbind被调用!");
      return true;
   }

   //Service被关闭前回调
   @Override
   public void onDestroy() {
      Log.i(TAG, "AIDL远程----->onDestroy被调用!");
      this.quit = true;
      super.onDestroy();
   }


   @Override
   public void onRebind(Intent intent) {
      Log.i(TAG, "AIDL远程----->onRebind被调用!");
      super.onRebind(intent);
   }
}
