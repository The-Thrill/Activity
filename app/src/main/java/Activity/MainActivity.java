package Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;


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
            //onCreate()    当Activity第一次被创建时调用，加载布局和初始化的工作
            //onStart()     当用户可以看到这个Activity时调用
            //onResume()    当获得用户的焦点时，就是用户点击了屏幕，只有经历此方法，才能进入运行状态
            //onPause()     当系统准备启动或者恢复另一个活动时调用
            //onStop()      当活动完全不可见时调用，若新启动的活动是对话框形式，则该方法不会被调用
            //onRestart()   当活动有停止状态变为运行状态时调用
            //onDestroy()   活动被销毁时调用，在对象死亡前，进行一些清理工作


        //TaskStack和LaunchMode
        //1、TaskStack 后进先出，一个应用启动会创建一个对应的TaskStack来存储并管理对应的Activity对象，只有在最上面才能显示
        //2、LaunchMode
            //1).standard       标准模式，每次调用startActivity()方法都会产生一个新的实例
            //2).singleTop      如果已经有一个实例位于栈顶，就不产生新的实例，否则创建一个新的实例
            //3).singeTask      只有一个实例，默认在当前Task中
            //4).singleInstance 只有一个实例，创建时会创建新的栈，且此栈中不能有其他对象


        //跳转PopupWindow、DialogAlert的使用界面
        Button btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示Intent
                startActivity(new Intent(MainActivity.this, DialogActivity.class));
            }
        });

        //Intent的使用
        Button btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示Intent
                startActivity(new Intent(MainActivity.this, IntentActivity.class));
            }
        });

        //ListView的使用
        Button btn3 = (Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListviewActivity.class));
            }
        });

        //RecyclerView相对于ListView的优点
        //1、可以使用布局管理器LayoutManager来管理RecyclerView的显示方式，水平、垂直、网络、网格交错
        //2、自定义item的分割条
        //3、可以控制item的添加和删除动画，可以自定义动画，配合具体场景
        //4、可以动态的在指定位置添加和删除某一项，而列表不会回到顶部，动态的updata列表数据
        //5、缺点就是没有OnItemClickListener(),需要自己在RecyclerView内部自定义列表项的点击事件或者长按事件
        //6、在Material Design中和CardView配合使用，显示效果非常突出

        //RecyclerView的使用
        Button btn4 = (Button)findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RecyclerViewActivity.class));
            }
        });

    }
}