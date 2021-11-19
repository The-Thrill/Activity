package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent的理解
        //1、显式：操作当前应用自己的组件  Intent(Context c, Class c)
        //2、隐式：操作其他应用自己的组件  Intent(String action) 与Activity的<intent-filter>的action匹配
            //1).创建Intent
                //显式： Intent(Context c, Class c)
                //隐式： Intent(String action) 与Activity的<intent-filter>的action匹配
            //2).携带数据
                //额外：putExtraData(String key, Xxx value); 内部用map容器保存
                //有特定前缀：setData(Uri data); tel:123123
            //3).读取数据
                //额外：Xxx getXxxExtra(String key)
                //有特定前缀：Uri getData()


        //Activity的使用
        //1、定义
            //1).定义一个类 extends Activity，并重写生命周期方法
            //2).在功能清淡文件中使用<activity>注册
        //2、启动
            //1).一般：startActivity(Intent intent)
            //2).带回调启动：startActivityForResult(Intent intent, int requestCode)
               //重写：onActivityResult(int requestCode, int requestCode, Intent data)
        //3、结束
            //1).一般：finish()
            //2).带结果：setResult(int resultCode, Intent data)


        //Activity的生命周期
        //1、Activity的状态
            //运行状态：可见可操作
            //暂停状态：可见不可操作
            //停止状态：不可见，单对象存在
            //死亡状态：对象不存在
        //2、Activity的生命周期
            //onCreate()    加载布局和初始化的工作
            //onStart()
            //onResume()    只有经历此方法，才能进入运行状态
            //onPause()
            //onStop()
            //onRestart()
            //onDestroy()   在对象死亡前，进行一些清理工作


        //TaskStack和LaunchMode
        //1、TaskStack 后进先出，一个应用启动会创建一个对应的TaskStack来存储并管理对应的Activity对象，只有在最上面才能显示
        //2、LaunchMode
            //1).standard       标准模式，每次调用startActivity()方法都会产生一个新的实例
            //2).singleTop      如果已经有一个实例位于栈顶，就不产生新的实例，否则创建一个新的实例
            //3).singeTask      只有一个实例，默认在当前Task中
            //4).singleInstance 只有一个实例，创建时会创建新的栈，且此栈中不能有其他对象


    }
}