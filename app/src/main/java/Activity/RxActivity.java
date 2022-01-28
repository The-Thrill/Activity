package Activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import Bean.ResponseResult;
import Bean.SuccessBean;
import Core.CustomObserver;
import Core.LoginEngine;
import Utils.LogUtils;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity implements View.OnClickListener{

    //响应式编程思维 根据上一层的响应 来影响下一层的变化

    private final String TAG = "RxActivity";
    private final static String PATH = "http://n.sinaimg.cn/photo/transform/700/w1000h500/20211002/3c9c-4fecc919c8af637a9f6cc7c82b4cf3bc.jpg";
    private ProgressDialog progressDialog;

    private Button button1, button2, button3;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        imageView = findViewById(R.id.iv);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        progressDialog = new ProgressDialog(RxActivity.this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                DownImage();
                break;
            case R.id.button2:
                RxJavaDownImage();
                break;
            case R.id.button3:
                RetrofitRxJava();
                break;
        }
    }

    //正常普通模式下载
    private void DownImage() {

    }

    //使用RxJava方法下载
    private void RxJavaDownImage() {
        //起点 第二步
        Observable.just(PATH)

                //需求将String转为Bitmap,流向终点的类型就转为了Bitmap 第三步
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String path) throws Throwable {
                        try {
                            //模拟缓慢加载
                            Thread.sleep(2000);
                            URL url = new URL(path);
                            //建立http连接
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            //设置超时时间
                            httpURLConnection.setConnectTimeout(5000);
                            //获取服务器响应
                            int responseCode = httpURLConnection.getResponseCode();
                            //获取服务器返回成功
                            if(responseCode == HttpURLConnection.HTTP_OK) {
                                //获取服务器返回的流
                                InputStream inputStream = httpURLConnection.getInputStream();
                                //转换为Bitmap
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                //将bitmap流向下一层
                                return bitmap;
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        return null;
                    }

                })

                //需求将图片加上水印
                .map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(Bitmap bitmap) throws Throwable {
                        Paint paint = new Paint();
                        paint.setColor(Color.RED);
                        paint.setTextSize(44);
                        Bitmap syBitmap = drawTextToBitmap(bitmap, "这是水印", paint, 100, 100);
                        return syBitmap;
                    }
                })

                //给上面耗时操作分配异步线程(图片下载)
                .subscribeOn(Schedulers.io())

                //给终点分配UI线程
                .observeOn(AndroidSchedulers.mainThread())

                //订阅终点：观察者设计模式
                .subscribe(new Observer<Bitmap>() {

                    //订阅成功 第一步
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        progressDialog.setTitle("正在加载中。。。");
                        progressDialog.show();
                    }

                    //上一层给到的响应  第四步
                    @Override
                    public void onNext(@NonNull Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }

                    //链条思维发生了异常
                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    //整个链条传递全部结束    第五步
                    @Override
                    public void onComplete() {
                        if(progressDialog != null) {
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    private void RetrofitRxJava() {
        LoginEngine.login("Derry","123456")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomObserver() {
                    @Override
                    public void success(SuccessBean successBean) {
                        LogUtils.i(TAG, "RetrofitRxJava successBean：" + successBean.toString());
                    }

                    @Override
                    public void error(String message) {
                        LogUtils.i(TAG, "RetrofitRxJava message：" + message);
                    }
                });

    }

    //给图片加水印
    private final Bitmap drawTextToBitmap(Bitmap bitmap, String text, Paint paint, int paddingLeft, int paddingTop) {
        Bitmap.Config config = bitmap.getConfig();

        //获取更清晰的图像采样
        paint.setDither(true);
        //过滤一下
        paint.setFakeBoldText(true);
        if(config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(config, true);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(text, paddingLeft, paddingTop, paint);
        return bitmap;
    }
}
