package Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class HandlerActivity extends AppCompatActivity {

    //当app第一次启动时，Android会同时启动一条UI线程（主线程）,负责处理与UI相关的事件，比如触发事件，修改UI组件等
    //UI线程:就是我们的主线程,系统在创建UI线程的时候会初始化一个Looper对象,同时也会创建一个与其关联的MessageQueue;
    //Handler:作用就是发送与处理信息,如果希望Handler正常工作,在当前线程中要有一个Looper对象
    //Message:Handler接收与处理的消息对象
    //MessageQueue:消息队列,先进先出管理Message,在初始化Looper对象时会创建一个与之关联的MessageQueue;
    //Looper:每个线程只能够有一个Looper,管理MessageQueue,不断地从中取出Message分发给对应的Handler处理！

    //当我们的子线程想修改Activity中的UI组件时,我们可以新建一个Handler对象,通过这个对象向主线程发送信息;
    //而我们发送的信息会先到主线程的MessageQueue进行等待,由Looper按先入先出顺序取出,
    // 再根据message对象的 what 属性分发给对应的Handler进行处理！

    //1、首先Looper.prepare()在本线程中保存一个Looper实例，然后该实例中保存一个MessageQueue对象；
        //因为Looper.prepare()在一个线程中只能调用一次，所以MessageQueue在一个线程中只会存在一个。
    //2、Looper.loop()会让当前线程进入一个无限循环，不端从MessageQueue的实例中读取消息，然后回调msg.target.dispatchMessage(msg)方法。
    //3、Handler的构造方法，会首先得到当前线程中保存的Looper实例，进而与Looper实例中的MessageQueue相关联。
    //4、Handler的sendMessage方法，会给msg的target赋值为handler自身，然后加入MessageQueue中。
    //5、在构造Handler实例时，我们会重写handleMessage方法，也就是msg.target.dispatchMessage(msg)最终调用的方法。

    private TextView textView;
    private MyHandler myHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_handler);

        textView = (TextView)findViewById(R.id.tv);

        //使用定时器,每隔1000毫秒让handler发送一个信息
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                //创建Message
                Message msg = Message.obtain();
                msg.what = 0;
                //创建Bundle
                Bundle bundle = new Bundle();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateStr = dateFormat.format(System.currentTimeMillis());
                bundle.putString("time","当前系统时间为"+dateStr);
                //为Message设置Bundle数据
                msg.setData(bundle);
                //发送消息
                myHandler.sendMessage(msg);

            }
        }, 0,1000);
    }

//
    private static class MyHandler extends Handler {

        //静态内部类+弱引用，避免内存溢出
        private WeakReference<HandlerActivity> weakReference;

        public MyHandler(HandlerActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        String s = msg.getData().getString("time");
            if(msg.what == 0) {
                weakReference.get().textView.setText(s);
            }
    }
}
}
